package com.pluu.pluubasiclibrary.pluu.common;

import java.io.File;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

/**
 * File 관련 Const
 * Created by PLUUSYSTEM on 2015-02-06.
 */
public class FileCommon {

	/**
	 * Get Cache Folder
	 * @param context Context
	 * @param directoryName Directory Name
	 * @return Cache Folder
	 */
	public static File getTempPath(Context context, String directoryName)
	{
		final String cachePath =
			Environment.MEDIA_MOUNTED.equals(
				Environment.getExternalStorageState()) || !isExternalStorageRemovable()
				? getExternalCacheDir(context).getPath()
				: context.getCacheDir().getPath();

		if (TextUtils.isEmpty(directoryName))
		{
			directoryName = "tmp";
		}

		File file = new File(cachePath + File.separator + directoryName);
		if (!file.exists() || !file.isDirectory())
		{
			file.mkdirs();
		}

		return file;
	}

	/**
	 * Check if external storage is built-in or removable.
	 * @return True if external storage is removable (like an SD card), false
	 *         otherwise.
	 */
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	private static boolean isExternalStorageRemovable() {
		if (hasGingerbread()) {
			return Environment.isExternalStorageRemovable();
		}
		return true;
	}

	/**
	 * Get the external app cache directory.
	 * @param context The context to use
	 * @return The external cache dir
	 */
	@TargetApi(Build.VERSION_CODES.FROYO)
	private static File getExternalCacheDir(Context context) {
		if (hasFroyo()) {
			return context.getExternalCacheDir();
		}

		// Before Froyo we need to construct the external cache dir ourselves
		final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
		return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
	}

	public static boolean hasFroyo() {
		// Can use static final constants like FROYO, declared in later versions
		// of the OS since they are inlined at compile time. This is guaranteed behavior.
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
	}

	public static boolean hasGingerbread() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
	}

}
