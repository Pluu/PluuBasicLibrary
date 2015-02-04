package com.pluu.pluubasiclibrary.pluu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.pluu.item.ResourceInfo;

import java.util.List;

/**
 * Created by Administrator on 2015-01-29.
 */
public class ColorAdapter extends NormalAdapter {
	public ColorAdapter(Context context, List<ResourceInfo> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.layout_color, null);
		}

		ResourceInfo item = getItem(position);

		View colorView = ViewHolder.get(convertView, R.id.view);
		TextView value = ViewHolder.get(convertView, R.id.textView2);

		int color = res.getColor(item.getId());
		colorView.setBackgroundColor(color);
		value.setText("#" + Integer.toHexString(color));

		return convertView;
	}
}
