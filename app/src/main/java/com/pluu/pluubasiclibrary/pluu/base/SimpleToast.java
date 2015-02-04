package com.pluu.pluubasiclibrary.pluu.base;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pluu.pluubasiclibrary.R;

/**
 * Simple Toast
 * Created by nohhs on 2015-02-04.
 */
public class SimpleToast {

	/** Color Type */
	public enum COLOR_TYPE {
		BLACK,
		BLUE,
		GREEN,
		RED,
		GRAY,
	}

	public static void blackShow(Context context, String msg) {
		show(context, COLOR_TYPE.BLACK, msg);
	}

	public static void blueShow(Context context, String msg) {
		show(context, COLOR_TYPE.BLUE, msg);
	}

	public static void greenShow(Context context, String msg) {
		show(context, COLOR_TYPE.GREEN, msg);
	}

	public static void redShow(Context context, String msg) {
		show(context, COLOR_TYPE.RED, msg);
	}

	public static void grayShow(Context context, String msg) {
		show(context, COLOR_TYPE.GRAY, msg);
	}

	public static void show(Context context, COLOR_TYPE type, String msg) {
		View view = createView(context, type, msg);
		showToast(context, view);
	}

	private static void showToast(Context context, View view) {
		Toast toast = new Toast(context);
		toast.setView(view);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
	}

	private static View createView(Context context, COLOR_TYPE type, String msg) {
		LinearLayout layout = new LinearLayout(context);
		layout.setBackgroundResource(getColorResource(type));

		Button button = new Button(new ContextThemeWrapper(context, R.style.ToastTextStyle));
		button.setBackgroundResource(android.R.color.transparent);
		button.setText(msg);

		layout.addView(button);
		return layout;
	}

	private static int getColorResource(COLOR_TYPE type) {
		switch (type) {
			case BLACK:
				return R.drawable.list_border_back_black;
			case BLUE:
				return R.drawable.list_border_back_blue;
			case GREEN:
				return R.drawable.list_border_back_green;
			case RED:
				return R.drawable.list_border_back_red;
			case GRAY:
				return R.drawable.list_border_back_gray;
			default:
				return R.drawable.list_border_back_blue;
		}
	}

}
