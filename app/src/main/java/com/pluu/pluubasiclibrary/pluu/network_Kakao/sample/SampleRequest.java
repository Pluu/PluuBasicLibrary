package com.pluu.pluubasiclibrary.pluu.network_Kakao.sample;

import com.pluu.pluubasiclibrary.pluu.network_Kakao.ServerProtocol;
import com.pluu.pluubasiclibrary.pluu.network_Kakao.request.ApiRequest;

/**
 * Sample Request
 * Created by nohhs on 2015-11-11.
 */
public class SampleRequest extends ApiRequest {

	@Override
	public String getMethod() {
		return GET;
	}

	@Override
	public String getUrl() {
		return ApiRequest.createBaseURL(ServerProtocol.API_AUTHORITY, "/sample");
	}
}
