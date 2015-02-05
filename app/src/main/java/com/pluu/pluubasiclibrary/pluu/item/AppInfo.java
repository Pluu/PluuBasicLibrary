package com.pluu.pluubasiclibrary.pluu.item;

import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;

import java.text.Collator;
import java.util.Comparator;

/**
 * Application greenShow
 * Created by PLUUSYSTEM on 2014-09-02.
 */
public class AppInfo {
    public static interface AppFilter {
        public void init();
        public boolean filterApp(ApplicationInfo info);
    }

    // Icon
    public Drawable mIcon = null;
    // Application Name
    public String mAppNaem = null;
    // Package Name
    public String mAppPackge = null;
    // Version Name
    public String mAppVersionName = null;
    // Version Code
    public int mAppVersionCode;

    /**
     * Third party Filter
     */
    public static final AppFilter THIRD_PARTY_FILTER = new AppFilter() {
        public void init() {
        }

        @Override
        public boolean filterApp(ApplicationInfo info) {
            if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                return true;
            } else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                return true;
            }
            return false;
        }
    };

    /**
     * Application Name Sort
     */
    public static final Comparator<AppInfo> ALPHA_COMPARATOR = new Comparator<AppInfo>() {
        private final Collator sCollator = Collator.getInstance();
        @Override
        public int compare(AppInfo object1, AppInfo object2) {
            return sCollator.compare(object1.mAppNaem, object2.mAppNaem);
        }
    };
}
