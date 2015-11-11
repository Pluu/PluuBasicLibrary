package com.pluu.pluubasiclibrary.pluu;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.pluu.base.BaseActivity;
import com.pluu.pluubasiclibrary.pluu.builder.CommonDialogBuilder;

/**
 * Custom Dialog Builder Activity
 * Created by PLUUSYSTEM on 2015-11-11.
 */
public class CustomDialogActivity extends BaseActivity
	implements AdapterView.OnItemClickListener {

	@Bind(R.id.toolbar)
	Toolbar toolbar;
	@Bind(android.R.id.list)
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_dialog);
		ButterKnife.bind(this);

		initToolbar(toolbar);
		initView();
	}

	private void initView() {
		List<String> list = new ArrayList<String>() {{
			add("Default 2 button");
			add("Default 1 button");
			add("Button Custom");
			add("Head, 1 Button Custom");

		}};
		listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
		listView.setOnItemClickListener(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (baseOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
			case 0:
				new CommonDialogBuilder(this)
					.setTitle("Title")
					.setMessage("Test Message")
					.setPositiveButton(android.R.string.ok, null)
					.setNegativeButton(android.R.string.cancel, null)
					.create().show();
				break;
			case 1:
				new CommonDialogBuilder(this)
					.setTitle("Title")
					.setMessage("Test Message")
					.setPositiveButton(android.R.string.ok, null)
					.create().show();
				break;
			case 2:
				new CommonDialogBuilder(this)
					.setTitle("Title")
					.setMessage("Test Message")
					.setPositiveButton(android.R.string.ok, null)
					.setPositiveBgResId(R.drawable.selector_btn_danger_b)
					.setNegativeButton(android.R.string.cancel, null)
					.setNegativeBgResId(R.drawable.selector_btn_dark_a)
					.create().show();
				break;
			case 3:
				new CommonDialogBuilder(this)
					.setTitle("Title")
					.setTitleTextColorResId(android.R.color.white)
					.setTitleBgResId(R.drawable.btn_danger_header)
					.setMessage("Test Message")
					.setPositiveButton(android.R.string.ok, null)
					.setPositiveTextColorResId(android.R.color.black)
					.setPositiveBgResId(R.drawable.selector_btn_white_c)
					.create().show();
				break;
		}
	}
}
