package com.pluu.pluubasiclibrary;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;
import android.text.TextUtils;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.pluu.pluubasiclibrary.pluu.common.Const;
import com.pluu.pluubasiclibrary.pluu.common.LogUtils;
import com.pluu.pluubasiclibrary.pluu.common.error.ExceptionCommon;

/**
 * Application Volley Request Controller
 * @author PLUUSYSTEM
 */
public class AppController extends Application {
	public static final String TAG = AppController.class.getSimpleName();
	
	// Volley Request Queue
    private RequestQueue mRequestQueue;
 
    private static AppController mInstance;
    
	public enum TrackerName {
		APP_TRACKER, // Tracker used only in this app.
		GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
		ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
	}

	private HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    @Override
    public void onCreate() {
        super.onCreate();
        
        init();
        mInstance = this;
    }
 
    /** Application 초기화 */
    private void init()
	{
		// Exception Handler
		if (Const.IS_DEV)
		{
			ExceptionCommon errorReport = new ExceptionCommon(getApplicationContext());
			Thread.setDefaultUncaughtExceptionHandler(errorReport);
		}
	}

	public static synchronized AppController getInstance() {
        return mInstance;
    }
 
    /**
     * Get Request Queue
     * @return Request Queue
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
 
        return mRequestQueue;
    }

	/**
	 * Cancel Request
	 */
	public void cancelRequestQueueAll() {
		if (mRequestQueue == null) {
			return;
		}

		LogUtils.d(TAG, "cancelRequestQueueAll");
		mRequestQueue.cancelAll(clearFilter);
	}

	/**
	 * Cancel Request
	 * @param tag TAG
	 */
	public void cancelRequestQueue(String tag) {
		if (mRequestQueue == null || TextUtils.isEmpty(tag)) {
			return;
		}

		LogUtils.d(TAG, "cancelRequestQueue=" + tag);
		mRequestQueue.cancelAll(tag);
	}
 
    /**
     * Cancel Request
     * @param tag Tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
    
    /**
     * Get Google Analytics App Tracker
     * @return App Tracker
     */
    public synchronized Tracker getTracker() {
    	return getTracker(TrackerName.APP_TRACKER);
    }

	/**
	 * Get Google Analytics Tracker
	 * @param trackerId {@link com.pluu.pluubasiclibrary.AppController.TrackerName}
	 * @return Select Tracker
	 */
	public synchronized Tracker getTracker(TrackerName trackerId) {
		if (!mTrackers.containsKey(trackerId)) {

			GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
			if (Const.IS_DEV) {
				// 개발모드인 경우 GA 전송 하지않음
				analytics.setDryRun(true);
			}

			Tracker t = (trackerId == TrackerName.APP_TRACKER)
				? analytics.newTracker(R.xml.app_tracker)
				: (trackerId == TrackerName.GLOBAL_TRACKER)
					? analytics.newTracker(R.xml.global_tracker)
					: analytics.newTracker(R.xml.ecommerce_tracker);

			t.enableAdvertisingIdCollection(true);
			mTrackers.put(trackerId, t);
		}
		return mTrackers.get(trackerId);
	}

    /**
     * Send GA Tracker
     * @param category Category Name
     * @param action Action Name
     */
    public void sendTracker(String category, String action) {
    	sendTracker(null, category, action);
    }
    
    /**
     * Send GA Tracker
     * @param screenName Screen Name
     * @param category Category Name
     * @param action Action Name
     */
    public void sendTracker(String screenName, String category, String action) {
		boolean isEmptyScreen = TextUtils.isEmpty(screenName);
		boolean isEmptyCategory = TextUtils.isEmpty(category);
		boolean isEmptyAction = TextUtils.isEmpty(action);

		if (isEmptyScreen && isEmptyCategory && isEmptyAction)
		{
			return;
		}

    	Tracker tracker = getTracker();
    	
    	if (!isEmptyScreen)
		{
			tracker.setScreenName(screenName);
			tracker.send(new HitBuilders.AppViewBuilder().build());
		}
    	
    	HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder();
    	if (!isEmptyCategory)
		{
			builder.setCategory(category);
		}
    	if (!isEmptyAction)
		{
			builder.setAction(action);
		}
    	
    	tracker.send(builder.build());
    	
    	if (Const.IS_DEV)
		{
			showGALog(screenName, category, action);
		}
    }

	/**
	 * Send GA Screen Tracker
	 * @param screenName Screen Name
	 */
	public void sendScreenTracker(String screenName)
	{
		if (TextUtils.isEmpty(screenName))
		{
			return;
		}

		Tracker tracker = getTracker();
		tracker.setScreenName(screenName);
		tracker.send(new HitBuilders.AppViewBuilder().build());

		if (Const.IS_DEV)
		{
			showGALog(screenName, null, null);
		}
	}

	/**
	 * Show GA Log
     * @param screenName Screen Name
     * @param category Category Name
     * @param action Action Name
	 */
	private void showGALog(String screenName, String category, String action)
	{
		JSONObject obj = new JSONObject();

		try
		{
			if (!TextUtils.isEmpty(screenName))
			{
				obj.put("ScreenName", screenName);
			}
			if (!TextUtils.isEmpty(category))
			{
				obj.put("category", category);
			}
			if (!TextUtils.isEmpty(action))
			{
				obj.put("action", action);
			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		LogUtils.d(TAG, "GA=[" + obj.toString() + "]");
	}

	// Request Cancel Filter
	private RequestQueue.RequestFilter clearFilter = new RequestQueue.RequestFilter() {
		@Override
		public boolean apply(Request<?> request)
		{
			LogUtils.d("AbstractResource", "Request Cancel=" + request.toString());
			return true;
		}
	};
    
}
