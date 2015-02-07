package com.pluu.pluubasiclibrary.pluu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.extra.ViewHolderHelper;
import com.pluu.pluubasiclibrary.pluu.base.BaseActionBarActivity;
import com.pluu.pluubasiclibrary.pluu.item.AppInfo;
import com.pluu.pluubasiclibrary.pluu.item.AppInfo.AppFilter;
import org.jetbrains.annotations.NotNull;

/**
 * Application greenShow Activity
 * Created by PLUUSYSTEM on 2014-09-02.
 */
public class AppInfoActivity extends BaseActionBarActivity {
	private static final String TAG = AppInfoActivity.class.getSimpleName();

	@InjectView(R.id.loading_container)
	View mLoadingContainer;
	@InjectView(R.id.listView1)
	ListView mListView;
	private IAAdapter mAdapter = null;

	@InjectView(R.id.toolbar)
	Toolbar toolbar;

	private final int MENU_THIRD_PARTY = 0;
	private final int MENU_ALL = 1;
	private int MENU_MODE = MENU_THIRD_PARTY;

	private PackageManager pm = null;

	private final String EXTRA_TAG_FIRST_VISIBLE_POSITION = "extra_tag_first_visible_position";
	private final String EXTRA_TAG_VISIBLE_OFFSET = "extra_tag_visible_offset";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_info);
		ButterKnife.inject(this);

		initToolbar(toolbar);

		mLoadingContainer = findViewById(R.id.loading_container);
		mListView = (ListView) findViewById(R.id.listView1);

		mAdapter = new IAAdapter(this);
		mListView.setAdapter(mAdapter);

		// Task Start
		startTask();
	}

	private void startTask() {
		new AppTask().execute();
	}

	/**
	 * Loading View Setting
	 *
	 * @param isView true/false
	 */
	private void setLoadingView(boolean isView) {
		mLoadingContainer.setVisibility(isView ? View.VISIBLE : View.GONE);
		mListView.setVisibility(isView ? View.GONE : View.VISIBLE);
	}

	/**
	 * List Adapter
	 *
	 * @author PLUUSYSTEM
	 */
	private class IAAdapter extends BaseAdapter {
		private List<ApplicationInfo> mAppList = null;
		private ArrayList<AppInfo> mListData = new ArrayList<>();
		private LayoutInflater inflater;

		public IAAdapter(Context mContext) {
			super();
			inflater = LayoutInflater.from(mContext);
		}

		public int getCount() {
			return mListData.size();
		}

		public AppInfo getItem(int position) {
			return mListData.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.manage_applications_item, null);
			}

			AppInfo data = getItem(position);

			ImageView icon = ViewHolderHelper.get(convertView, R.id.app_icon);
			TextView appName = ViewHolderHelper.get(convertView, R.id.app_name);
			TextView packageName = ViewHolderHelper.get(convertView, R.id.app_package);
			TextView version = ViewHolderHelper.get(convertView, R.id.app_version);

			if (data.mIcon != null) {
				icon.setImageDrawable(data.mIcon);
			}

			appName.setText(data.mAppNaem);
			packageName.setText(data.mAppPackge);
			version.setText("Name=" + data.mAppVersionName + ", Code=" + data.mAppVersionCode);

			return convertView;
		}

		/**
		 * Create Application List
		 */
		public void rebuild() {
			if (mAppList == null) {

				Log.d(TAG, "Is Empty Application List");
				// Package Manager
				pm = AppInfoActivity.this.getPackageManager();

				// Installed Application
				mAppList = pm
					.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES
						| PackageManager.GET_DISABLED_COMPONENTS);
			}

			AppFilter filter;
			switch (MENU_MODE) {
				case MENU_THIRD_PARTY:
					filter = AppInfo.THIRD_PARTY_FILTER;
					break;
				default:
					filter = null;
					break;
			}

			if (filter != null) {
				filter.init();
			}

			// Reset
			mListData.clear();

			AppInfo addInfo;
			ApplicationInfo appInfo;

			PackageInfo packageInfo;

			for (ApplicationInfo app : mAppList) {
				appInfo = app;

				if (filter == null || filter.filterApp(appInfo)) {
					addInfo = new AppInfo();
					// App Icon
					addInfo.mIcon = app.loadIcon(pm);
					// App Name
					addInfo.mAppNaem = app.loadLabel(pm).toString();
					// App Package Name
					addInfo.mAppPackge = app.packageName;

					try {
						packageInfo = pm.getPackageInfo(app.packageName, 0);

						addInfo.mAppVersionName = packageInfo.versionName;
						addInfo.mAppVersionCode = packageInfo.versionCode;
					} catch (NameNotFoundException e) {
						e.printStackTrace();
					}

					mListData.add(addInfo);
				}
			}

			// Name Sort
			Collections.sort(mListData, AppInfo.ALPHA_COMPARATOR);
			mListView.setScrollY(0);
		}
	}

	/**
	 * Task
	 *
	 * @author PLUUSYSTEM
	 */
	private class AppTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			setLoadingView(true);
		}

		@Override
		protected Void doInBackground(Void... params) {
			mAdapter.rebuild();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mAdapter.notifyDataSetChanged();
			setLoadingView(false);
		}
	}

	@Override
	protected void onSaveInstanceState(@NotNull Bundle outState) {
		super.onSaveInstanceState(outState);

		View childAt = mListView.getChildAt(0);
		int top = (childAt == null) ? 0 : childAt.getTop();

		outState.putInt(EXTRA_TAG_FIRST_VISIBLE_POSITION, mListView.getFirstVisiblePosition());
		outState.putInt(EXTRA_TAG_VISIBLE_OFFSET, top);
	}

	@Override
	protected void onRestoreInstanceState(@NotNull Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		int position = savedInstanceState.getInt(EXTRA_TAG_FIRST_VISIBLE_POSITION, 0);
		int top = savedInstanceState.getInt(EXTRA_TAG_VISIBLE_OFFSET, 0);

		mListView.setSelectionFromTop(position, top);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.app_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (baseOptionsItemSelected(item)) {
			return true;
		}

		switch (item.getItemId()) {
			case R.id.app_all:
				MENU_MODE = MENU_ALL;
				break;
			case R.id.third_party:
				MENU_MODE = MENU_THIRD_PARTY;
				break;
			default:
				return true;
		}

		startTask();
		return true;
	}

	@OnItemClick(R.id.listView1)
	void onAppListItemClick(AdapterView<?> parent, View view, int position, long id) {
		AppInfo item = mAdapter.getItem(position);

		Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
		intent.setData(Uri.parse("package:" + item.mAppPackge));
		startActivity(intent);
	}

}
