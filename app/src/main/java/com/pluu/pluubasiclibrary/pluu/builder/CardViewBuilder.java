package com.pluu.pluubasiclibrary.pluu.builder;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;

/**
 * CardView Builder
 * Created by PLUUSYSTEM-NEW on 2014-11-10.
 */
public class CardViewBuilder {
    private CardAttribute attribute;

    private boolean isChange;
    private Drawable drawable;

    public static CardViewBuilder with(Context context) {
        return new CardViewBuilder(context);
    }

    private CardViewBuilder(Context context) {
        attribute = new CardAttribute(context);
        isChange = true;
    }

    public CardViewBuilder setAttribute(CardAttribute attribute) {
        this.attribute = attribute;
        this.isChange = true;
        return this;
    }

    public void into(View view) {
        if (view == null) {
            return;
        }

        if (isChange) {
            drawable = createDrawable();
        }

        view.setBackgroundDrawable(drawable);
    }

    private Drawable createDrawable() {
        int r = attribute.getRadius();
        float[] outerR = new float[]{r, r, r, r, r, r, r, r};
        RoundRectShape rectShape = new RoundRectShape(outerR, null, null);

        Paint p = new Paint();
        p.setColor(attribute.getBgColor());
        ShapeDrawable drawable = new ShapeDrawable(rectShape);
        drawable.getPaint().set(p);
        return drawable;
    }

}
