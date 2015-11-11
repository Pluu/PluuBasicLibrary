package com.pluu.pluubasiclibrary.pluu.network_Kakao.sample;

import com.pluu.pluubasiclibrary.pluu.network_Kakao.callback.ResponseCallback;
import com.pluu.pluubasiclibrary.pluu.network_Kakao.request.RequestTask;
import com.pluu.pluubasiclibrary.pluu.network_Kakao.tasks.ResultTask;

/**
 * Sample Service
 * Created by nohhs on 2015-11-11.
 */
public class SampleService {

	public static void requestSqmple(ResponseCallback<String> callback) {
		RequestTask.getInstance().addTask(new ResultTask<String>(callback) {

			@Override
			public String call() throws Exception {
				return SampleApi.requestSample();
			}
		});

	}
}
