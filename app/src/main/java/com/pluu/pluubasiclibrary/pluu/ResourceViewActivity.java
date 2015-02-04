package com.pluu.pluubasiclibrary.pluu;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.pluu.adapter.ArrayStringAdapter;
import com.pluu.pluubasiclibrary.pluu.adapter.BooleanAdapter;
import com.pluu.pluubasiclibrary.pluu.adapter.ColorAdapter;
import com.pluu.pluubasiclibrary.pluu.adapter.DimenAdapter;
import com.pluu.pluubasiclibrary.pluu.adapter.DrawableAdapter;
import com.pluu.pluubasiclibrary.pluu.adapter.IntegerAdapter;
import com.pluu.pluubasiclibrary.pluu.adapter.NormalAdapter;
import com.pluu.pluubasiclibrary.pluu.adapter.StringAdapter;
import com.pluu.pluubasiclibrary.pluu.base.BaseActionBarActivity;
import com.pluu.pluubasiclibrary.pluu.item.RES_TYPE;
import com.pluu.pluubasiclibrary.pluu.item.ResourceInfo;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;

public class ResourceViewActivity extends BaseActionBarActivity {
	private final String TAG = ResourceViewActivity.class.getSimpleName();

	@InjectView(R.id.spinner)
	Spinner mSpinner;
	@InjectView(R.id.spinner2)
	Spinner mSpinner2;
	@InjectView(R.id.listView)
	ListView mListView;
	@InjectView(R.id.emptyView)
	TextView mEmptyView;
	@InjectView(R.id.toolbar)
	Toolbar toolbar;

	private ResourceReflection reflection;
	private XmlResourceConverter xmlConverter;
	private Multimap<RES_TYPE, String> resTypeMap = ArrayListMultimap.create();
	private Multimap<String, RES_TYPE> resInvertMap = ArrayListMultimap.create();
	private RES_TYPE currentType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resource_view);
		ButterKnife.inject(this);

		initToolbar(toolbar);
		initResTypeMap();
		initValue();
	}

	private void initValue() {
		mListView.setEmptyView(mEmptyView);

		reflection = new ResourceReflection();
		xmlConverter = new XmlResourceConverter();

		mSpinner.setAdapter(ArrayAdapter.createFromResource(this,
			R.array.resource_location_description_array,
			android.R.layout.simple_spinner_dropdown_item));

		updateSubClass(getString(R.string.resource_class_public));
	}

	@OnItemSelected(R.id.spinner)
	void onBaseItemSelected(AdapterView<?> parent, View view, int position, long id) {
		Log.d(TAG, "position=" + position);

		String path = getBaseClassPath(position);
		updateSubClass(path);
	}

	private String getBaseClassPath(int position) {
		return position == 0
			? getString(R.string.resource_class_public)
			: getString(R.string.resource_class_internal);
	}

	private void updateSubClass(String baseClass) {
		ArrayList<String> list = reflection.getSubClasses(baseClass);
		ArrayAdapter<String> adapter = new ArrayAdapter<>(
			this,
			android.R.layout.simple_spinner_item,
			list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinner2.setAdapter(adapter);
	}

	@OnItemSelected(R.id.spinner2)
	void onSubItemSelected(AdapterView<?> parent, View view, int position, long id) {
		String item = (String) parent.getAdapter().getItem(position);
		Log.d(TAG, "sub select=" + item);

		selectResource(getBaseClassPath(mSpinner.getSelectedItemPosition()), item);
	}

	private void selectResource(String baseClass, String fullClass) {
		Log.d(TAG, "Base=" + baseClass + ", Sub=" + fullClass);

		ArrayList<ResourceInfo> list = reflection.getItemList(baseClass, fullClass);
//		for (ResourceInfo item : list) {
//			Log.d(TAG, item.toString());
//		}
		mListView.setAdapter(getAdapter(fullClass, list));
	}

	private void initResTypeMap() {
		resTypeMap.put(RES_TYPE.DIMEN, "dimen");
		resTypeMap.put(RES_TYPE.INTEGER, "integer");
		resTypeMap.put(RES_TYPE.DRAWABLE, "drawable");
		resTypeMap.put(RES_TYPE.DRAWABLE, "mipmap");
		resTypeMap.put(RES_TYPE.COLOR, "color");
		resTypeMap.put(RES_TYPE.BOOLEAN, "bool");
		resTypeMap.put(RES_TYPE.STRING, "string");
		resTypeMap.put(RES_TYPE.ARRAY, "array");

		Multimaps.invertFrom(resTypeMap, resInvertMap);
	}

	private NormalAdapter getAdapter(String fullClass, ArrayList<ResourceInfo> list) {
		currentType = getResourceType(fullClass);

		switch (currentType) {
			case BOOLEAN:
				return new BooleanAdapter(this, list);
			case DRAWABLE:
				return new DrawableAdapter(this, list);
			case DIMEN:
				return new DimenAdapter(this, list);
			case COLOR:
				return new ColorAdapter(this, list);
			case INTEGER:
				return new IntegerAdapter(this, list);
			case STRING:
				return new StringAdapter(this, list);
			case ARRAY:
				return new ArrayStringAdapter(this, list);
			default:
				return new NormalAdapter(this, list);
		}
	}

	private RES_TYPE getResourceType(String fullClass) {
		Log.d(TAG, "Search=" + fullClass);

		if (TextUtils.isEmpty(fullClass)) {
			return RES_TYPE.NORMAL;
		}

		for (String value : resTypeMap.values()) {
			if (fullClass.endsWith(value)) {
				return resInvertMap.get(value).iterator().next();
			}
		}

		return RES_TYPE.NORMAL;
	}

	@OnItemClick(R.id.listView)
	void onListItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (currentType != RES_TYPE.NORMAL) {
			return;
		}

		String base = (String) mSpinner2.getSelectedItem();
		ResourceInfo item = (ResourceInfo) mListView.getAdapter().getItem(position);

		if (base.endsWith("anim")
			|| base.endsWith("animator")
			|| base.endsWith("interpolator")
			|| base.endsWith("layout")) {
			showDetail(base + "." + item.getName(), getXMLData(item.getId()));
		} else if (base.endsWith("attr")) {
//			getAttrData(item.getId());
		}
	}

	private String getXMLData(int resourceId) {
		XmlResourceParser data = getResources().getXml(resourceId);
		return xmlConverter.convert(data);
	}

	private String getAttrData(int resourceId) {
		// TODO : Attr데이터 parse
		return "";
	}

	private void showDetail(String title, String detail) {
		Intent intent = new Intent(this, ResourceDetailActivity.class);
		intent.putExtra("title", title);
		intent.putExtra("detail", detail);
		startActivity(intent);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (baseOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}


}
