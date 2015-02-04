package com.pluu.pluubasiclibrary;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MaterialMainActivity extends ActionBarActivity
	implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;
	private CharSequence mTitle;

	private Toolbar toolbar;
	private DrawerLayout dlDrawer;
	private ActionBarDrawerToggle dtToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_material_main);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

		setSupportActionBar(toolbar);

		dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
		dlDrawer.setDrawerListener(dtToggle);

		mNavigationDrawerFragment = (NavigationDrawerFragment)
			getFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, dlDrawer);

		onNavigationDrawerItemSelected(PlaceholderFragment.newInstance(0));
	}

	@Override
	public void onNavigationDrawerItemSelected(Fragment fragment) {
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
			.replace(R.id.container, fragment)
			.commit();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		dtToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		dtToggle.onConfigurationChanged(newConfig);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		if (!mNavigationDrawerFragment.isDrawerOpen()) {
//			getMenuInflater().inflate(R.menu.material_main, menu);
//			return true;
//		}
//		return super.onCreateOptionsMenu(menu);
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		if (dtToggle.onOptionsItemSelected(item)) {
//			return true;
//		}
//
//		int id = item.getItemId();
//
//		if (id == R.id.action_settings) {
//			return true;
//		}
//
//		return super.onOptionsItemSelected(item);
//	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section
		 * number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_material_main, container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
//			((MaterialMainActivity) activity).onSectionAttached(
//				getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}

}
