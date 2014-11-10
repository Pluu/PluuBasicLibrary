package com.pluu.pluubasiclibrary.pluu;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.pluu.pluubasiclibrary.R;
import com.pluu.pluubasiclibrary.pluu.builder.CardAttribute;
import com.pluu.pluubasiclibrary.pluu.builder.CardViewBuilder;

/**
 * Card View Builder Activity
 * Created by Administrator on 2014-11-10.
 */
public class CardViewBuilderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_builder);

        initView();
    }

    private void initView() {
        // Default
        View view = findViewById(R.id.bgLayout);
        CardViewBuilder
            .with(this)
            .into(view);

        // Custom
        View custom = findViewById(R.id.bgLayout2);
        CardAttribute attribute = new CardAttribute(this);
        attribute.setBgColor(Color.parseColor("#2196f3"));
        attribute.setShadowBgColor(Color.parseColor("#e3f2fd"));
        attribute.setShadowBgColorWidth(10);
        attribute.setRadius(10);

        CardViewBuilder
            .with(this)
            .setAttribute(attribute)
            .into(custom);
    }

}
