package com.pluu.pluubasiclibrary.pluu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.pluu.pluubasiclibrary.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ResourceDetailActivity extends ActionBarActivity {

	@InjectView(R.id.main)
	TextView mMain;
	@InjectView(R.id.detail)
	TextView mDetail;
	@InjectView(R.id.toolbar)
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resource_detail);
		ButterKnife.inject(this);

		initToolBar();
		initValue();
	}

	private void initToolBar() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void initValue() {
		Intent intent = getIntent();
		String title = intent.getStringExtra("title");
		String detail = intent.getStringExtra("detail");

		mMain.setText(title);
		mDetail.setText(detail);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
