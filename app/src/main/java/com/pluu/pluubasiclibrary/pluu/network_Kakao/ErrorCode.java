package com.pluu.pluubasiclibrary.pluu.network_Kakao;

import java.util.HashMap;
import java.util.Map;

/**
 * API 요청에 대한 에러 코드
 * Created by nohhs on 2015-10-07.
 */
public enum ErrorCode {

	/**
	 * 클라이언트 단에서 http 요청 전,후로 에러 발생한 경우. 대게 인터넷 연결이 끊어진 경우 발생한다. code = -777
	 */
	CLIENT_ERROR_CODE(-777),
	/**
	 * SDK 가 인지 못하고 있는 에러코드. code = -888
	 */
	UNDEFINED_ERROR_CODE(-888),
	/**
	 * 서버 내부에서 에러가 발생한 경우. code = -1
	 */
	INTERNAL_ERROR_CODE(-1),
	/**
	 * API 규약에 맞지않는 파라매터 전송시의 에러가 발생한 경우. code = -2
	 */
	INVALID_PARAM_CODE(-2);

	private final int errorCode;
	private static final Map<Integer, ErrorCode> reverseMap = new HashMap<Integer, ErrorCode>(24);

	static {
		for (ErrorCode errorCode : ErrorCode.values()) {
			reverseMap.put(errorCode.getErrorCode(), errorCode);
		}
	}

	ErrorCode(final int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 숫자로 구성된 에러코드
	 * @return 숫자로 구성된 에러코드
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * 숫자 에러코드를 enum으로 변경하여 반환한다.
	 * @param i 변경할 숫자 에러코드
	 * @return 숫자에 해당하는 enum 에러코드
	 */
	public static ErrorCode valueOf(final Integer i) {
		if(i == null)
			return null;
		ErrorCode errorCode = reverseMap.get(i);
		if(errorCode != null)
			return errorCode;
		else
			return UNDEFINED_ERROR_CODE;
	}

}
