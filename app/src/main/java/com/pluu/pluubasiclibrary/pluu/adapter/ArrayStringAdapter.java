package com.pluu.pluubasiclibrary.pluu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.pluu.item.ResourceInfo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015-01-29.
 */
public class ArrayStringAdapter extends NormalAdapter {

	public ArrayStringAdapter(Context context, List<ResourceInfo> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.layout_array, null);
		}

		ResourceInfo item = getItem(position);

		TextView title = ViewHolder.get(convertView, R.id.textView);
		TextView value = ViewHolder.get(convertView, R.id.textView2);
		TextView detail = ViewHolder.get(convertView, R.id.textView3);

		title.setText(item.getName());
		value.setText(String.valueOf(item.getId()));
		detail.setText(Arrays.toString(res.getStringArray(item.getId())));

		return convertView;
	}

}
