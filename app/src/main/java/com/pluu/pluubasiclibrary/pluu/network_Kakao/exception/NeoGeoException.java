package com.pluu.pluubasiclibrary.pluu.network_Kakao.exception;

/**
 * NeoGeo SDK Exception
 * Created by nohhs on 2015-10-13.
 */
public class NeoGeoException extends RuntimeException {

	private ErrorType errorType;

	/**
	 * Error Type
	 */
	public enum ErrorType {
		ILLEGAL_ARGUMENT,
		MISS_CONFIGURATION,
		CANCELED_OPERATION,
		UNSPECIFIED_ERROR
	}

	public NeoGeoException(final ErrorType errorType, final String msg) {
		super(msg);
		this.errorType = errorType;
	}

	public NeoGeoException(String detailMessage) {
		super(detailMessage);
	}

	public NeoGeoException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public NeoGeoException(Throwable throwable) {
		super(throwable);
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public String getMessage(){
		if(errorType != null)
			return errorType.toString() +" : " + super.getMessage();
		else
			return super.getMessage();
	}

	public String getSimpleMessage() {
		return super.getMessage();
	}
}
