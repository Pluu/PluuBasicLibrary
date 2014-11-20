package com.pluu.pluubasiclibrary.pluu;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Graphic Common
 * Created by Administrator on 2014-11-17.
 */
public class GraphicCommon {
    /**
     * DP -> Pixel 변환
     * @param res Resource
     * @param dp DP
     * @return Pixel
     */
    public static int dpToPixel(Resources res, float dp)
    {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics()));
    }

    /**
     * Pixel -> DP 변환
     * @param res Resource
     * @param px pixel
     * @return DP
     */
    public static float pxTodp(Resources res,int px) {
        DisplayMetrics metrics = res.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    /**
     * Sp -> Pixel 변환
     * @param res Resource
     * @param sp SP
     * @return Pixel
     */
    public static int spTopx(Resources res,float sp) {
        final float scale = res.getDisplayMetrics().scaledDensity;
        int px = Math.round(sp * scale);
        return px;
    }

    /**
     * Get Display Size
     * @param context Context
     * @return [0] width [1] height
     */
    public static int[] getDisplaySize(Context context)
    {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int[] size = new int[2];
        size[0] = metrics.widthPixels;
        size[1] = metrics.heightPixels;

        return size;
    }

    /**
     * Get Display Width Size
     * @param context Context
     * @return size size
     */
    public static int getDisplayWidthSize(Context context)
    {
        int[] displaySize = getDisplaySize(context);
        return displaySize[0];
    }

    /**
     * Get Display Height Size
     * @param context Context
     * @return size size
     */
    public static int getDisplayHeightSize(Context context)
    {
        int[] displaySize = getDisplaySize(context);
        return displaySize[1];
    }
}
