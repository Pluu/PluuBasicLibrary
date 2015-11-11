package com.pluu.pluubasiclibrary.pluu.network_Kakao.exception;

/**
 * Response Status Error Exception
 * Created by nohhs on 2015-10-12.
 */
public abstract class ResponseStatusError extends Exception {
	public ResponseStatusError(String errorMsg) {
		super(errorMsg);
	}

	public abstract int getErrorCode();
	public abstract String getErrorMsg();
	public abstract int getHttpStatusCode();
}
