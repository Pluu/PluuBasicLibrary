package com.pluu.pluubasiclibrary.pluu;

import android.app.Activity;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;

/**
 * Common
 * Created by Administrator on 2014-10-29.
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
}
