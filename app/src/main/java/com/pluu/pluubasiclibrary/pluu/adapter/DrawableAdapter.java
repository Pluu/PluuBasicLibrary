package com.pluu.pluubasiclibrary.pluu.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.pluu.item.ResourceInfo;

/**
 * Created by PLUUSYSTEM on 2015-01-29.
 */
public class DrawableAdapter extends NormalAdapter {
	public DrawableAdapter(Context context, List<ResourceInfo> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.layout_drawable, null);
		}

		ResourceInfo item = getItem(position);

		ImageView view = ViewHolder.get(convertView, R.id.view);
		TextView value = ViewHolder.get(convertView, R.id.textView2);

		view.setImageResource(item.getId());
		value.setText(item.getName());
		value.setEllipsize(TextUtils.TruncateAt.END);

		return convertView;
	}
}
