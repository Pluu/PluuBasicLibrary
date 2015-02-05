package com.pluu.pluubasiclibrary.pluu.common;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Display Common
 * Created by PLUUSYSTEM on 2014-11-17.
 */
public class DisplayCommon {
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

	/**
	 * Get Display Density & DPI Name
	 * @param context Context
	 * @return Display Data
	 */
	public static DisplayItem getDensityData(Context context) {
		DisplayMetrics metrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);

		DisplayItem item = new DisplayItem();
		item.density = metrics.density;
		int dpi = metrics.densityDpi;
		String dpiName;
		switch (dpi) {
			case DisplayMetrics.DENSITY_HIGH:
				dpiName = "HDPI";
				break;
			case DisplayMetrics.DENSITY_MEDIUM:
				dpiName = "MDPI";
				break;
			case DisplayMetrics.DENSITY_LOW:
				dpiName = "LDPI";
				break;
			case DisplayMetrics.DENSITY_XHIGH:
				dpiName = "XHDPI";
				break;
			case DisplayMetrics.DENSITY_TV:
				dpiName = "TVDPI";
				break;
			case DisplayMetrics.DENSITY_XXHIGH:
				dpiName = "XXHDPI";
				break;
			case DisplayMetrics.DENSITY_XXXHIGH:
				dpiName = "XXXHDPI";
				break;
			default:
				dpiName = "Default";
				break;
		}

		item.dpiName = dpiName;
		return item;
	}

}
