package com.pluu.pluubasiclibrary.android;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.android.adapter.RecyclerViewAdapter;
import com.pluu.pluubasiclibrary.android.adapter.item.DateModel;
import com.pluu.pluubasiclibrary.pluu.base.BaseActionBarActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * RecyclerViewActivity
 * Created by PLUUSYSTEM on 2014-11-04.
 */
public class RecyclerViewActivity extends BaseActionBarActivity {
	@InjectView(R.id.recyclerview1)
	RecyclerView mRecyclerView;
	@InjectView(R.id.toolbar)
	Toolbar toolbar;

	private Random r = new Random(System.currentTimeMillis());
	private RecyclerViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycler_view);
		ButterKnife.inject(this);

		initToolbar(toolbar);

		initValue();
		viewVerticalLinear();
	}

	private void initValue() {
		List<DateModel> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			list.add(getItem(i));
		}

		adapter = new RecyclerViewAdapter(list);
		mRecyclerView.setAdapter(adapter);

		// Item Decoration
		// TODO : ItemDecoration
	}

	private DateModel getItem(int position) {
		StringBuffer strBuffer = new StringBuffer();
		DateModel item = new DateModel();

		item.text1 = "Main_" + position;
		strBuffer.append("Sub=");
		strBuffer.append("Text2_0");
		int size = r.nextInt(10);
		for (int j = 1; j < size; j++) {
			strBuffer.append(",Text2_" + j);
		}
		item.text2 = strBuffer.toString();
		return item;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recylcler_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (baseOptionsItemSelected(item)) {
			return true;
		}

		switch (item.getItemId()) {
			case R.id.menu_vlinear:
				viewVerticalLinear();
				break;
			case R.id.menu_hlinear:
				viewHorizontalLinear();
				break;
			case R.id.menu_grid:
				viewGridLayout();
				break;
			case R.id.menu_staggeredgridlayout:
				viewStaggeredGridLayout();
				break;
		}

		return true;
	}

	@OnClick(R.id.button)
	public void onAddClick() {
		int position = adapter.getItemCount();
		adapter.addItem(position, getItem(position));
		mRecyclerView.scrollToPosition(position);
	}

	@OnClick(R.id.button2)
	public void onRemoveClick() {
		if (adapter.getItemCount() > 0) {
			adapter.remove(adapter.getItemCount() - 1);
		}
	}

	private void viewVerticalLinear() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(layoutManager);
	}

	private void viewHorizontalLinear() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRecyclerView.setLayoutManager(layoutManager);
	}

	private void viewGridLayout() {
		GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
		mRecyclerView.setLayoutManager(layoutManager);
	}

	private void viewStaggeredGridLayout() {
		StaggeredGridLayoutManager layoutManager
			= new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(layoutManager);
	}

}
