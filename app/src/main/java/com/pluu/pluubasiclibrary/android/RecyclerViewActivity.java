package com.pluu.pluubasiclibrary.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.android.adapter.RecyclerViewAdapter;
import com.pluu.pluubasiclibrary.android.adapter.item.DateModel;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerViewActivity
 * Created by PLUUSYSTEM-NEW on 2014-11-04.
 */
public class RecyclerViewActivity extends Activity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initView();
        initValue();
        viewVerticalLinear();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
    }

    private void initValue() {
        List<DateModel> list = new ArrayList<>();
        DateModel item;
        for (int i = 0; i < 20; i++) {
            item = new DateModel();
            item.text1 = "Text1_" + i;
            item.text2 = "Text2_" + i;
            list.add(item);
        }

        mRecyclerView.setAdapter(new RecyclerViewAdapter(list));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recylcler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_vlinear:
                viewVerticalLinear();
                break;
            case R.id.menu_hlinear:
                viewHorizontalLinear();
                break;
            case R.id.menu_grid:
                viewGridLayout();
                break;
            case R.id.menu_staggeredgridlayout:
                viewStaggeredGridLayout();
                break;
        }

        return true;
    }

    private void viewVerticalLinear() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void viewHorizontalLinear() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void viewGridLayout() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void viewStaggeredGridLayout() {
        StaggeredGridLayoutManager layoutManager
                = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
    }

}
