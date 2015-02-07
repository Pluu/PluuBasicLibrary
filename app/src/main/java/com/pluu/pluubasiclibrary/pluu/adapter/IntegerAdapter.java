package com.pluu.pluubasiclibrary.pluu.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.pluu.item.ResourceInfo;

/**
 * Created by PLUUSYSTEM on 2015-01-29.
 */
public class IntegerAdapter extends NormalAdapter {
	public IntegerAdapter(Context context, List<ResourceInfo> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.layout_normal, null);
		}

		ResourceInfo item = getItem(position);

		TextView title = ViewHolder.get(convertView, R.id.textView);
		TextView value = ViewHolder.get(convertView, R.id.textView2);

		title.setText(item.getName());
		value.setText(String.valueOf(res.getInteger(item.getId())));

		return convertView;
	}
}
