package com.pluu.pluubasiclibrary.pluu.common;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;

/**
 * Common
 * Created by PLUUSYSTEM on 2014-10-29.
 */
public class ActivityCommon {

    /**
     * Activity Overflow Menu 활성화
     * @param context
     */
    public static void DirectOverflowMenuEnable(Activity context) {
        try {
            ViewConfiguration config = ViewConfiguration.get(context);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show IME
     * @param context Context
     */
    public static void showIME(Context context)
    {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * Hide IME
     * @param context Context
     * @param view focused view
     */
    public static void hideIME(Context context, View view)
    {
        if (view == null)
        {
            return;
        }
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
