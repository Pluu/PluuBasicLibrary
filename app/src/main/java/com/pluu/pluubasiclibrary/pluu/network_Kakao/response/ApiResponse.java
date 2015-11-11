package com.pluu.pluubasiclibrary.pluu.network_Kakao.response;

import java.net.HttpURLConnection;

import com.pluu.pluubasiclibrary.pluu.network_Kakao.StringSet;
import com.pluu.pluubasiclibrary.pluu.network_Kakao.exception.ResponseStatusError;


/**
 * Api Response Class
 * Created by nohhs on 2015-10-12.
 */
public abstract class ApiResponse {
	private ApiResponse() { }

	protected ApiResponse(ResponseData responseData)
		throws ResponseBody.ResponseBodyException, ApiResponseStatusError {

		if (responseData.getHttpStatusCode() != HttpURLConnection.HTTP_OK) {
			ResponseBody errResponseBody = new ResponseBody(responseData.getHttpStatusCode(), responseData.getResponseString());
			throw new ApiResponseStatusError(errResponseBody.getInt(StringSet.code),
											 errResponseBody.optString(StringSet.msg, ""),
											 responseData.getHttpStatusCode());
		}
	}

	public static class ApiResponseStatusError extends ResponseStatusError {
		private static final long serialVersionUID = 3702596857996303483L;
		private final int errorCode;
		private final String errorMsg;
		private final int httpStatusCode;

		public ApiResponseStatusError(int errorCode, String errorMsg, int httpStatusCode) {
			super(errorMsg);
			this.errorCode = errorCode;
			this.errorMsg = errorMsg;
			this.httpStatusCode = httpStatusCode;
		}

		public int getErrorCode() {
			return errorCode;
		}

		public String getErrorMsg() {
			return errorMsg;
		}

		public int getHttpStatusCode() {
			return httpStatusCode;
		}
	}

}
