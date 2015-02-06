package com.pluu.pluubasiclibrary.pluu.common.error;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

/**
 * Application Crash Exception Parser
 * Created by PLUUSYSTEM on 2015-02-05.
 */
public class ErrorConst {

	/**
	 * 에러로그 취득
	 * @param throwable Exception
	 * @return 에러로그
	 */
	public static String getMessage(Throwable throwable) {
		StringBuffer strBuffer = new StringBuffer();
		Date cuurentDate = new Date();
		strBuffer.append("Error Report collected on : " + cuurentDate.toString());
		strBuffer.append("\n\n");

		strBuffer.append("=Stack=============\n");

		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		throwable.printStackTrace(printWriter);
		strBuffer.append(result.toString());
		strBuffer.append("\n");

		strBuffer.append("=Cause====== \n");

		Throwable cause = throwable.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			strBuffer.append(result.toString());
			cause = cause.getCause();
		}

		printWriter.close();
		return strBuffer.toString();
	}

}
