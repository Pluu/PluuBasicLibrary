package com.pluu.pluubasiclibrary.pluu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.pluu.base.BaseActionBarActivity;
import com.pluu.pluubasiclibrary.pluu.builder.CardAttribute;
import com.pluu.pluubasiclibrary.pluu.builder.CardViewBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Card View Builder Activity
 * Created by PLUUSYSTEM on 2014-11-10.
 */
public class CardViewBuilderActivity extends BaseActionBarActivity {

	@InjectView(R.id.toolbar)
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_view_builder);
		ButterKnife.inject(this);

		initToolbar(toolbar);
		initView();
	}

	private void initView() {
		// Default
		View view = findViewById(R.id.bgLayout);
		CardViewBuilder
			.with(this)
			.into(view);

		// Default Selector
		view = findViewById(R.id.bgLayout2);
		CardAttribute attribute = new CardAttribute(this);
		attribute.setSelector(true);
		CardViewBuilder
			.with(this)
			.setAttribute(attribute)
			.into(view);


		int customBgColor = Color.parseColor("#CC83CC39");
		int customShadowColor = Color.parseColor("#e3f2fd");
		int customSelectColor = Color.parseColor("#83CC39");

		// Custom
		view = findViewById(R.id.bgLayout3);
		attribute = new CardAttribute(this);
		attribute.setBgColor(customBgColor);
		attribute.setShadowColor(customShadowColor);
		attribute.setShadowBgColorWidth(4);
		attribute.setRadius(10);

		CardViewBuilder
			.with(this)
			.setAttribute(attribute)
			.into(view);

		// Custom Selector
		view = findViewById(R.id.bgLayout4);
		attribute = new CardAttribute(this);
		attribute.setBgColor(customBgColor);
		attribute.setShadowColor(customShadowColor);
		attribute.setShadowBgColorWidth(4);
		attribute.setRadius(10);
		attribute.setSelector(true);
		attribute.setSelectorColor(customSelectColor);

		CardViewBuilder
			.with(this)
			.setAttribute(attribute)
			.into(view);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (baseOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
