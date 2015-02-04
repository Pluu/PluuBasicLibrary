package com.pluu.pluubasiclibrary.pluu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.pluu.base.BaseActionBarActivity;
import com.pluu.pluubasiclibrary.pluu.base.SimpleToast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class OAuthActivity extends BaseActionBarActivity {

	@InjectView(R.id.toolbar)
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oauth);
		ButterKnife.inject(this);

		initToolbar(toolbar);

		getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content, new MyFragment())
			.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (baseOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private class MyFragment extends ListFragment {
		@Override
		public void onActivityCreated(@Nullable Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);

			String[] list = new String[20];
			for (int i = 0; i < list.length; i++) {
				list[i] = "Sting Tpye" + i;
			}

			setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, list));
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			super.onListItemClick(l, v, position, id);
			SimpleToast.blueShow(getActivity(),
				String.valueOf(getListAdapter().getItem(position)));

		}
	}

}
