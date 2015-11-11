package com.pluu.pluubasiclibrary.pluu.network_Kakao;

import java.net.HttpURLConnection;

import com.pluu.pluubasiclibrary.pluu.network_Kakao.exception.ResponseStatusError;


/**
 * Error 에 대한 결과값을 저장하고 있는 객체.
 * error code, error message, http status 를 저장한다.
 * Created by nohhs on 2015-10-07.
 */
public class ErrorResult {

	final private ErrorCode errorCode;
	final private String errorMessage;
	final private int httpStatus;

	public ErrorResult(Exception e) {
		this.errorCode = ErrorCode.CLIENT_ERROR_CODE;
		this.errorMessage = e.getMessage();
		this.httpStatus = HttpURLConnection.HTTP_INTERNAL_ERROR;
	}

	public ErrorResult(ResponseStatusError e) {
		this.errorCode = ErrorCode.valueOf(e.getErrorCode());
		this.errorMessage = e.getMessage();
		this.httpStatus = e.getHttpStatusCode();
	}

	/**
	 * {@link ErrorCode}를 반환한다.
	 * @return response 에러코드.
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * 에러발생 메세지
	 * @return 에러발생 메세지
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * HttpResponse StatusCode 를 반환
	 * @return HttpResponse StatusCode
	 */
	public int getHttpStatus() {
		return httpStatus;
	}

	/**
	 * 결과 객체를 String 으로 표현
	 * @return 요청 URL, 에러 코드, 에러 메시지를 포함한 string
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("APIErrorResult{");
		sb.append(", errorCode=").append(errorCode.getErrorCode());
		sb.append(", errorMessage='").append(errorMessage).append('\'');
		sb.append(", httpStatus='").append(httpStatus).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
