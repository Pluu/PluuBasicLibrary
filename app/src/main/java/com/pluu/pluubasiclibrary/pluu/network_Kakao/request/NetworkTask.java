package com.pluu.pluubasiclibrary.pluu.network_Kakao.request;

import java.io.IOException;

import com.pluu.pluubasiclibrary.BuildConfig;
import com.pluu.pluubasiclibrary.pluu.network_Kakao.response.ResponseData;

/**
 * Network Request Task
 * <br/> 실제 Request 하는 로직
 * Created by nohhs on 2015-10-12.
 */
public class NetworkTask {

	private static final String REQUEST_TAG = BuildConfig.APPLICATION_ID + "_NETWORK";

	// TODO : Request Method Modify
	public ResponseData requestApi(final IRequest request) throws IOException {
		return null;
	}

}
