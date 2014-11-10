package com.pluu.pluubasiclibrary.pluu.builder;

import android.content.Context;
import android.content.res.Resources;

import com.pluu.pluubasiclibrary.R;

/**
 * CardView Attribute
 * Created by PLUUSYSTEM-NEW on 2014-11-10.
 */
public class CardAttribute {
    private int shadowBgColor;
    private int shadowBgColorWidth;
    private int bgColor;
    private int radius;

    /**
     * Constructor
     * <br>Base Setting
     *
     * @param context
     */
    public CardAttribute(Context context) {
        init(context);
    }

    private void init(Context context) {
        // Default Setting
        Resources res = context.getResources();

        setShadowBgColor(res.getColor(R.color.card_shadow_color));
        setShadowBgColorWidth(res.getDimensionPixelSize(R.dimen.card_shadow_width));
        setBgColor(res.getColor(R.color.card_bg_color));
    }

    public CardAttribute setShadowBgColor(int color) {
        this.shadowBgColor = color;
        return this;
    }

    public CardAttribute setShadowBgColorWidth(int width) {
        this.shadowBgColorWidth = width;
        return this;
    }

    public CardAttribute setBgColor(int color) {
        this.bgColor = color;
        return this;
    }

    public CardAttribute setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public int getShadowBgColor() {
        return shadowBgColor;
    }

    public int getShadowBgColorWidth() {
        return shadowBgColorWidth;
    }

    public int getBgColor() {
        return bgColor;
    }

    public int getRadius() {
        return radius;
    }
}
