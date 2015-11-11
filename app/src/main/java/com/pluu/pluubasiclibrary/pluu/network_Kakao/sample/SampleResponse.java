package com.pluu.pluubasiclibrary.pluu.network_Kakao.sample;

import com.pluu.pluubasiclibrary.pluu.network_Kakao.response.ApiResponse;
import com.pluu.pluubasiclibrary.pluu.network_Kakao.response.ResponseBody;
import com.pluu.pluubasiclibrary.pluu.network_Kakao.response.ResponseData;

/**
 * Sample Response
 * Created by nohhs on 2015-11-11.
 */
public class SampleResponse extends ApiResponse {

	public SampleResponse(ResponseData responseData)
		throws ResponseBody.ResponseBodyException, ApiResponseStatusError {
		super(responseData);
	}

	public String getResult() {
		return null;
	}
}
