package com.pluu.pluubasiclibrary.pluu.network_Kakao.sample;

import com.pluu.pluubasiclibrary.pluu.network_Kakao.request.NetworkTask;
import com.pluu.pluubasiclibrary.pluu.network_Kakao.response.ResponseData;
import com.pluu.pluubasiclibrary.pluu.network_Kakao.sample.SampleRequest;
import com.pluu.pluubasiclibrary.pluu.network_Kakao.sample.SampleResponse;

/**
 * Sample API
 * Created by nohhs on 2015-11-11.
 */
public class SampleApi {

	public static String requestSample() throws Exception {
		NetworkTask task = new NetworkTask();
		ResponseData responseData = task.requestApi(new SampleRequest());
		return new SampleResponse(responseData).getResult();
	}

}
