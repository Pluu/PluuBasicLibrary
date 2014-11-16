package com.pluu.pluubasiclibrary.pluu.builder;

import android.content.Context;
import android.content.res.Resources;

import com.pluu.pluubasiclibrary.R;

/**
 * CardView Attribute
 * Created by PLUUSYSTEM-NEW on 2014-11-10.
 */
public class CardAttribute {
    private int shadowColor;
    private int shadowBgColorWidth;
    private int bgColor;
    private int radius;

    private boolean isSelector = false;
	private int selectorColor;

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

        setShadowColor(res.getColor(R.color.card_shadow_color));
        setShadowBgColorWidth(res.getDimensionPixelSize(R.dimen.card_shadow_width));
        setBgColor(res.getColor(R.color.card_bg_color));
        setRadius(res.getDimensionPixelSize(R.dimen.card_radius));
		setSelectorColor(res.getColor(R.color.card_selector_bg_color));
    }

    public CardAttribute setShadowColor(int color) {
        this.shadowColor = color;
        return this;
    }

    public int getShadowColor() {
        return shadowColor;
    }

    public CardAttribute setShadowBgColorWidth(int width) {
        this.shadowBgColorWidth = width;
        return this;
    }

    public int getShadowBgColorWidth() {
        return shadowBgColorWidth;
    }

    public CardAttribute setBgColor(int color) {
        this.bgColor = color;
        return this;
    }

    public int getBgColor() {
        return bgColor;
    }

    public CardAttribute setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public int getRadius() {
        return radius;
    }

    public CardAttribute setSelector(boolean isSelector) {
        this.isSelector = isSelector;
		return this;
    }

    public boolean isSelector() {
        return isSelector;
    }

	public CardAttribute setSelectorColor(int selectorColor) {
		this.selectorColor = selectorColor;
		return this;
	}

	public int getSelectorColor() {
		return selectorColor;
	}
}
