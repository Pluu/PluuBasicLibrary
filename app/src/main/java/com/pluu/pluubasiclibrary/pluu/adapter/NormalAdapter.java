package com.pluu.pluubasiclibrary.pluu.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.pluu.item.ResourceInfo;

import java.util.List;

/**
 * Created by Administrator on 2015-01-29.
 */
public class NormalAdapter extends BaseAdapter {
	protected final Resources res;
	protected final LayoutInflater inflater;
	private List<ResourceInfo> list;

	public NormalAdapter(Context context, List<ResourceInfo> list) {
		res = context.getResources();
		inflater = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
	public ResourceInfo getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
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
		value.setText(String.valueOf(item.getId()));

		return convertView;
	}

	protected static class ViewHolder {
		@SuppressWarnings("unchecked")
		public static <T extends View> T get(View convertView, int id)
		{
			SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();

			if (viewHolder == null)
			{
				viewHolder = new SparseArray<>();
				convertView.setTag(viewHolder);
			}

			View childView = viewHolder.get(id);
			if (childView == null)
			{
				childView = convertView.findViewById(id);
				viewHolder.put(id, childView);
			}

			return (T) childView;
		}
	}

}
