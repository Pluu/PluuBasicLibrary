package com.pluu.pluubasiclibrary.pluu.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

/**
 * Config Preferences Class
 * Created by PLUUSYSTEM on 2015-02-06.
 */
public class PrefConfig {
	// SharedPreferences Name
	private final static String PREF_NAME_SERVICE = "service";

	// Key
	private final static String PREF_KEY_LOGIN_ID = "login_id";
	private final static String PREF_KEY_LOGIN_PW = "login_pw";

	/**
	 * SharedPreferences 취득
	 * @param context Context
	 * @param name Name
	 * @return SharedPreferences
	 */
	private static SharedPreferences getSharedPreferences(Context context, String name)
	{
		return context.getSharedPreferences(name, Context.MODE_PRIVATE);
	}

	/**
	 * Login 정보 설정
	 * @param context Context
	 * @param id Id
	 * @param pw Password
	 */
	public static void setLoginned(Context context, String id, String pw)
	{
		SharedPreferences pref = getSharedPreferences(context, PREF_NAME_SERVICE);
		Editor edit = pref.edit();
		edit.putString(PREF_KEY_LOGIN_ID, id);
		edit.putString(PREF_KEY_LOGIN_PW, pw);
		edit.commit();
	}

	/**
	 * Login Id 취득
	 * @param context Context
	 * @return id
	 */
	public static String getLoginId(Context context)
	{
		SharedPreferences pref = getSharedPreferences(context, PREF_NAME_SERVICE);
		return pref.getString(PREF_KEY_LOGIN_ID, null);
	}

	/**
	 * Login Password 취득
	 * @param context Context
	 * @return Password
	 */
	public static String getLoginPw(Context context)
	{
		SharedPreferences pref = getSharedPreferences(context, PREF_NAME_SERVICE);
		return pref.getString(PREF_KEY_LOGIN_PW, null);
	}

	/**
	 * Login 정보 존재체크
	 * @param context Context
	 * @return true/false
	 */
	public static boolean existLoginData(Context context)
	{
		return !TextUtils.isEmpty(getLoginId(context))
			&& !TextUtils.isEmpty(getLoginPw(context));
	}
}
