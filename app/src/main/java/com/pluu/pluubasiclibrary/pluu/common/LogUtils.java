package com.pluu.pluubasiclibrary.pluu.common;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.util.Log;

/**
 * LogUtility
 * Created by PLUUSYSTEM on 2015-01-06.
 */
public class LogUtils {
	public static void v(String msg) {
		if (Const.IS_LOG) {
			Log.v(Const.LOG_TAG, msg);
		}
	}

	public static void v(String tag, String msg) {
		if (Const.IS_LOG) {
			Log.v(Const.LOG_TAG, "[" + tag + "] " + msg);
		}
	}

	public static void d(String msg) {
		if (Const.IS_LOG) {
			Log.d(Const.LOG_TAG, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (Const.IS_LOG) {
			Log.d(Const.LOG_TAG, "[" + tag + "] " + msg);
		}
	}

	public static void i(String msg) {
		if (Const.IS_LOG) {
			Log.i(Const.LOG_TAG, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (Const.IS_LOG) {
			Log.i(Const.LOG_TAG, "[" + tag + "] " + msg);
		}
	}

	public static void w(String msg) {
		if (Const.IS_LOG) {
			Log.w(Const.LOG_TAG, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (Const.IS_LOG) {
			Log.w(Const.LOG_TAG, "[" + tag + "] " + msg);
		}
	}

	public static void e(String msg) {
		if (Const.IS_LOG) {
			Log.e(Const.LOG_TAG, msg);
		}
	}

	public static void e(String msg, Throwable tr) {
		if (Const.IS_LOG) {
			Log.e(Const.LOG_TAG, msg, tr);
		}
	}

	public static void e(String category, String msg) {
		if (Const.IS_LOG) {
			Log.e(Const.LOG_TAG, "[" + category + "] " + msg);
		}
	}

	public static void e(String category, String msg, Throwable tr) {
		if (Const.IS_LOG) {
			StringWriter sw = new StringWriter();
			tr.printStackTrace(new PrintWriter(sw));
			Log.e(Const.LOG_TAG, "[" + category + "] " + msg + "\n" + sw.toString());
		}
	}

	public static void log(int priority, String tag, String msg) {
		if (Const.IS_LOG) {
			Log.println(priority, Const.LOG_TAG, "[" + tag + "]" + msg);
		}
	}
}
