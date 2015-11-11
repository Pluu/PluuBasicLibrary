package com.pluu.pluubasiclibrary.pluu.network_Kakao.request;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.pluu.pluubasiclibrary.pluu.network_Kakao.tasks.ResultTask;


/**
 * Request Task
 * Created by nohhs on 2015-10-07.
 */
public class RequestTask {

	private static volatile RequestTask instance;
	private ExecutorService locoTaskExecutor = Executors.newCachedThreadPool();

	public static RequestTask getInstance() {
		if (instance == null) {
			synchronized (RequestTask.class) {
				if (instance == null) {
					instance = new RequestTask();
				}
			}
		}

		return instance;
	}

	private RequestTask() { }

	public <T> Future<T> addTask(ResultTask<T> task) {
		return locoTaskExecutor.submit(task.getCallable());
	}
}
