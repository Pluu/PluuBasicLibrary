package com.pluu.pluubasiclibrary.pluu.network;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pluu.pluubasiclibrary.AppController;
import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.pluu.base.SimpleToast;
import com.pluu.pluubasiclibrary.pluu.common.Const;
import com.pluu.pluubasiclibrary.pluu.common.LogUtils;

/**
 * Abstract Network Resource
 * @author PLUUSYSTEM
 */
public abstract class AbstractResource implements IParam, IResource {
	private final String TAG = AbstractResource.class.getSimpleName();

	/**
	 * Network Callback Interface
	 * @author PLUUSYSTEM
	 */
	public static interface Callback {
		void onResult(IResource data);
	}

	// Request Callback
	private Callback mCallback;
	// Volley 통신 Request Queue
	private RequestQueue requestQueue;

	// //////////////////////////////////////////////////
	// Network Resource
	// //////////////////////////////////////////////////

	private final int TIME_OUT = 1000 * 30;

	// Request 결과 코드
	private CODE mCode = CODE.NONE;
	/** Response Message */
	private String mMessage;

	private Context mContext;

	public AbstractResource(Context context) {
		this.mContext = context;
		this.requestQueue = ((AppController)
			((Activity) context).getApplication()).getRequestQueue();
	}

	// //////////////////////////////////////////////////
	// Network Resource
	// //////////////////////////////////////////////////

	/** Server URL */
	private String getURL() {
		// TODO : 서버 URL
		// Sample : GitHub
		return "https://api.github.com/repos/pluu/PluuBasicLibrary/contributors";
	}

	/**
	 * Get Request Parameter
	 * @return Request Parameter
	 */
	private Map<String, String> getRequestContent() {
		Map<String, Object> params = new HashMap<>();

		// 추가 파라매터
		makeParam(params);

		// 기본 통신 Parameter
		// TODO : 기본 Parameter

		checkParams(params);

		Map<String, String> ret = new HashMap<>();
		for (Entry<String, Object> entry : params.entrySet()) {
			ret.put(entry.getKey(), String.valueOf(entry.getValue()));
		}

		LogUtils.d(TAG, "Request =" + ret.toString());
		if (isMultiPart()) {
			Map<String, File> data = getMultiPartContent();
			for (Entry<String, File> item : data.entrySet()) {
				LogUtils.d(TAG, "Request Multipart [ " + item.getKey() + "=" + item.getValue().getAbsolutePath() + " ]");
			}
		}

		return ret;
	}

	/**
	 * Set Response Data
	 * @param content Response Data
	 */
	private void setResponseContent(JSONObject content) {
		try {
			if (Const.IS_DEV) {
				LogUtils.d(TAG, "Response =" + content.toString(4));
			}

			if (content.has(RES_HEADER)) {
				JSONObject ret = content.optJSONObject(RES_HEADER);

				// 기본 파라매터 데이터 처리
				mCode = CODE.getCodeGenerator(ret.optInt(RES_CODE, CODE_ERROR));
				mMessage = ret.optString(RES_MSG, mContext.getString(R.string.system_error));

				// 세부 파라매터 처리
				if (mCode == CODE.SUCCESS
					|| isChildParse()) {
					parse(content);
				}
			} else {
				mCode = CODE.SYSTEM_ERROR;
				mMessage = mContext.getString(R.string.system_error);
			}
		} catch (JSONException e) {
			e.printStackTrace();

			mCode = CODE.SYSTEM_ERROR;
			mMessage = mContext.getString(R.string.system_error);
		}

		notifyResult();
	}

	/**
	 * Call SystemError Callback
	 * @param msg Message
	 */
	private void notifySystemErrorResult(String msg) {
		mCode = CODE.SYSTEM_ERROR;
		mMessage = msg;
		notifyResult();
	}

	/**
	 * Call Callback
	 */
	private void notifyResult() {
		if (mCallback != null) {
			mCallback.onResult(this);
		}
	}

	protected boolean isChildParse() {
		return false;
	}

	// //////////////////////////////////////////////////
	// Callback Resource
	// //////////////////////////////////////////////////

	@Override
	public CODE getCode() {
		return mCode;
	}

	@Override
	public String getMessage() {
		return mMessage;
	}

	@Override
	public boolean isSuccessed() {
		return mCode == CODE.SUCCESS;
	}

	@Override
	public List<?> getList() {
		return null;
	}

	@Override
	public int getListCount() {
		return 0;
	}

	// //////////////////////////////////////////////////
	// Request
	// //////////////////////////////////////////////////

	/**
	 * Start Request
	 * <p/>
	 * <HTML>
	 * <BODY>
	 * <font color="#FF0000"><strong>통신 기본 Queue를 인스턴스하지 않은 경우 NullPointerException 발생</strong></font>
	 * </BODY>
	 * </HTML>
	 *
	 * @param callback Callback
	 */
	public void request(Callback callback) {
		request(callback, TAG);
	}

	/**
	 * Start Request
	 * <p/>
	 * <HTML>
	 * <BODY>
	 * <font color="#FF0000"><strong>통신 기본 Queue를 인스턴스하지 않은 경우 NullPointerException 발생</strong></font>
	 * </BODY>
	 * </HTML>
	 *
	 * @param callback Callback
	 * @param tag      Request Tag
	 */
	public void request(Callback callback, String tag) {
		if (requestQueue == null) {
			throw new NullPointerException("RequestQueue object is null");
		}

		Request<?> request;

//		if (isMultiPart()) {
//			request = newInstanceSimpleMultipartRequest();
//		} else {
			request = newInstanceSimpleRequest();
//		}

		this.mCallback = callback;
		request.setTag(TextUtils.isEmpty(tag) ? TAG : tag
			+ String.valueOf(System.currentTimeMillis()));

		// Retry Policy
		request.setRetryPolicy(
			new DefaultRetryPolicy(
				TIME_OUT,
				0,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

		requestQueue.add(request);
	}

	/**
	 * Create Simple Request Instance
	 * @return Simple Request Instance
	 */
	private StringRequest newInstanceSimpleRequest() {
		StringRequest request = new StringRequest(Request.Method.GET, getURL(), listener, errorListener);
		return request;
	}


//	/**
//	 * Create Simple Multipart Request Instance
//	 * @return Multipart Request Instance
//	 */
//	private MultipartResultJsonRequest newInstanceSimpleMultipartRequest() {
//		MultipartResultJsonRequest request = new MultipartResultJsonRequest(getURL(),
//			listener,
//			errorListener,
//			getRequestContent(),
//			getMultiPartContent());
//
//		return request;
//	}

	/**
	 * 파라매터 데이터 Null 포함시 공백으로 전환
	 * @param map Request Parameter
	 * @return Changed Request Parameter
	 */
	private Map<String, Object> checkParams(Map<String, Object> map) {
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> pairs = it.next();
			if (pairs.getValue() == null) {
				map.put(pairs.getKey(), "");
			}
		}
		return map;
	}

	/** Volley Response Listener */
	private Response.Listener<String> listener = new Response.Listener<String>() {

		@Override
		public void onResponse(String s) {
			SimpleToast.blueShow(mContext, s);
		}
	};

	private Response.Listener<JSONObject> jsonListener = new Response.Listener<JSONObject>() {

		@Override
		public void onResponse(JSONObject response) {
			setResponseContent(response);
		}
	};

	/** Volley Error Response Listener */
	private Response.ErrorListener errorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			LogUtils.d(TAG, "Error: " + error.getMessage());
			notifySystemErrorResult(mContext.getString(R.string.system_error));
		}
	};

	// //////////////////////////////////////////////////
	// Abstract Method
	// //////////////////////////////////////////////////

	/**
	 * 통신 파라매터 설정
	 * <br>상위에서 설정하는 파라매터는 아래와 같다
	 * <li>Request API Master</li>
	 * <li>Request API Slave</li>
	 * <li>User ID</li>
	 * <li>Device ID</li>
	 * <li>GCM Token</li>
	 * @param param Parameter
	 */
	protected abstract void makeParam(Map<String, Object> param);

	/**
	 * @param param
	 * @throws org.json.JSONException
	 */
	protected abstract void parse(JSONObject param) throws JSONException;

	/**
	 * Get Is MultiPart Request
	 * @return true / false
	 */
	protected abstract boolean isMultiPart();

	/**
	 * Get Multipart Content
	 * @return Multipart Content
	 */
	protected abstract Map<String, File> getMultiPartContent();

}
