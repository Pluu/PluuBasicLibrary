package com.pluu.pluubasiclibrary.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pluu.pluubasiclibrary.android.adapter.item.DateModel;

import java.util.List;

/**
 * RecyclerViewAdapter
 * Created by PLUUSYSTEM-NEW on 2014-11-04.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderItem> {
    private List<DateModel> list;

    public RecyclerViewAdapter(List<DateModel> list) {
        this.list = list;
    }

    @Override
    public ViewHolderItem onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(android.R.layout.simple_list_item_2, viewGroup, false);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderItem viewHolderItem, int i) {
        DateModel item = list.get(i);
        viewHolderItem.tv1.setText(item.text1);
        viewHolderItem.tv2.setText(item.text2);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        TextView tv1, tv2;

        public ViewHolderItem(android.view.View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(android.R.id.text1);
            tv2 = (TextView) itemView.findViewById(android.R.id.text2);
        }
    }
}
