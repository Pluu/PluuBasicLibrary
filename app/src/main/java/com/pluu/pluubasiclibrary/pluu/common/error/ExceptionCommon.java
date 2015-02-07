package com.pluu.pluubasiclibrary.pluu.common.error;

import java.io.File;

import android.content.Context;

import com.pluu.pluubasiclibrary.pluu.common.FileCommon;
import com.pluu.pluubasiclibrary.pluu.common.LogUtils;

/**
 * Crash Exception관련 수집 Handler
 * Created by PLUUSYSTEM on 2014-11-27.
 */
public class ExceptionCommon implements Thread.UncaughtExceptionHandler {
	public static final String REPORT_FILE_NAME = "CrashErrorLog";

	private final String TAG = ExceptionCommon.class.getSimpleName();

	private Context mContext;
	private Thread.UncaughtExceptionHandler mPreviousHandler;

	public ExceptionCommon(Context context) {
		super();
		this.mContext = context;

		init(mContext);
	}

	private void init(Context context) {
		mPreviousHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
		String msg = ErrorConst.getMessage(throwable);
		LogUtils.d(TAG, "ErrorLog=" + msg);
		saveAsFile(msg);
		mPreviousHandler.uncaughtException(thread, throwable);
	}

	/**
	 * 에러로그를 파일로 저장함
	 * @param msg 에러로그
	 */
	private void saveAsFile(String msg) {
		File path = FileCommon.getTempPath(mContext, "error");
		LogWriter mLogWriter = new LogWriter(true);
		mLogWriter.openFile(path.getAbsolutePath()  ,REPORT_FILE_NAME, "txt");
		mLogWriter.write(msg);
	}
}
