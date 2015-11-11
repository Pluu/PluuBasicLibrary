package com.pluu.pluubasiclibrary.pluu.network_Kakao.callback;

import com.pluu.pluubasiclibrary.pluu.network_Kakao.ErrorResult;

/**
 * 공통 Api Callback Class
 * Created by nohhs on 2015-10-07.
 */
public abstract class ApiResponseCallback<T> {

	/**
	 * 요청이 실패한 경우 호출된다.
	 * @param errorResult 실패한 원인이 담긴 결과
	 */
	public abstract void onFailure(final ErrorResult errorResult);

	/**
	 * Request 에 대한 result.
	 * @param result nullable value
	 */
	public abstract void onSuccess(T result);

	/**
	 * 요청한 Request 가 실패했을때 불리는 callback.
	 * 해당 Error 를 통해서 Request 별로 공통처리 및 onFailure Callback 을 재정의 할 수 있습니다.
	 * @param errorResult 실패결과.
	 */
	public void onFailureForUiThread(ErrorResult errorResult) {
		onFailure(errorResult);
	}

	/**
	 * 요청한 Request 가 성공했을때 불리는 callback.
	 * 해당 callback 을 통해서 Request 별로 공통처리 및 onSuccess Callback 을 재정의 할 수 있습니다.
	 * @param result 성공에 대한 response.
	 */
	public void onSuccessForUiThread(T result) {
		onSuccess(result);
	}

}
