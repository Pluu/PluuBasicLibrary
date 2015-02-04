package com.pluu.pluubasiclibrary.pluu.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.pluu.item.ResourceInfo;

import java.util.List;

/**
 * Created by Administrator on 2015-01-29.
 */
public class DimenAdapter extends NormalAdapter {
	private final String currentDP;

	public DimenAdapter(Context context, List<ResourceInfo> list) {
		super(context, list);

		currentDP = getDPName();
	}

	private String getDPName() {
		DisplayMetrics dm = res.getDisplayMetrics();
		float density = dm.density;
		if (density >= 4.0) {
			return "xxxhdpi";
		} else if (density >= 3.0) {
			return "xxhdpi";
		} else if (density >= 2.0) {
			return "xhdpi";
		} else if (density >= 1.5) {
			return "hdpi";
		} else if (density >= 1.0) {
			return "mdpi";
		}

		return "none";
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

		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(res.getString(item.getId()));
		if (!strBuffer.toString().endsWith("%")) {
			strBuffer.append("(")
				.append(currentDP)
				.append("=")
				.append(res.getDimensionPixelSize(item.getId()))
				.append("px)");
		}
		value.setText(strBuffer.toString());

		return convertView;
	}
}
