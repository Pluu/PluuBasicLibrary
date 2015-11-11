package com.pluu.pluubasiclibrary.pluu.network_Kakao.response;

/**
 * Response Result Data
 * Created by nohhs on 2015-10-12.
 */
public class ResponseData {

	private final int httpStatusCode;
	private final String responseString;

	public ResponseData(int httpStatusCode, String responseString) {
		this.httpStatusCode = httpStatusCode;
		this.responseString = responseString;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public String getResponseString() {
		return responseString;
	}

	@Override
	public String toString() {
		return "ResponseData { httpStatusCode=" + httpStatusCode + ", responseString=" + responseString + "}";
	}

}
