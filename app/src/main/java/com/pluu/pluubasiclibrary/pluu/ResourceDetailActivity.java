package com.pluu.pluubasiclibrary.pluu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.pluu.base.BaseActionBarActivity;

public class ResourceDetailActivity extends BaseActionBarActivity {

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

		initToolbar(toolbar);
		initValue();
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
		if (baseOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
