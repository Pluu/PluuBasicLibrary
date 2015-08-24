package com.pluu.pluubasiclibrary.pluu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.pluu.base.BaseActivity;

public class ResourceDetailActivity extends BaseActivity {

	@Bind(R.id.main)
	TextView mMain;
	@Bind(R.id.detail)
	TextView mDetail;
	@Bind(R.id.toolbar)
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resource_detail);
		ButterKnife.bind(this);

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
