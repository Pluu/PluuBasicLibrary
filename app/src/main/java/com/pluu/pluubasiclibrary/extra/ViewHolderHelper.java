package com.pluu.pluubasiclibrary.extra;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by PLUUSYSTEM on 2014-09-02.
 *
 * Original : http://www.kmshack.kr/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-ui%EC%B5%9C%EC%A0%81%ED%99%94-%EB%A6%AC%EC%8A%A4%ED%8A%B8%EB%B7%B0-%EC%84%B1%EB%8A%A5%EC%B5%9C%EC%A0%81%ED%99%94
 */
public class ViewHolderHelper {
    public static <T extends View> T get(View convertView, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            convertView.setTag(viewHolder);
        }

        View childView = viewHolder.get(id);

        if (childView == null) {
            childView = convertView.findViewById(id);
            viewHolder.put(id, childView);
        }

        return (T) childView;

    }
}
