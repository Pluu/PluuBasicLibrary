package com.pluu.pluubasiclibrary.pluu.network_Kakao.tasks;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import com.pluu.pluubasiclibrary.pluu.network_Kakao.ErrorResult;
import com.pluu.pluubasiclibrary.pluu.network_Kakao.callback.ResponseCallback;
import com.pluu.pluubasiclibrary.pluu.network_Kakao.exception.ResponseStatusError;


/**
 * Result Task
 * Created by nohhs on 2015-10-07.
 */
public abstract class ResultTask<T> {

	private final static Handler mainHandler = new Handler(Looper.getMainLooper());
	final ResponseCallback<T> callback;

	public ResultTask(ResponseCallback<T> callback) {
		this.callback = callback;
	}

	private Callable<T> task = new Callable<T>() {
		@Override
		public T call() throws Exception {
			T result = null;
			Exception ex = null;

			try {
				result = ResultTask.this.call();
			} catch (Exception e) {
				ex = e;
			}

			final T response = result;
			final Exception t = ex;

			final CountDownLatch lock = new CountDownLatch(1);
			mainHandler.post(new Runnable() {
				@Override
				public void run() {
					if (callback == null) {
						return;
					}

					try {
						if (t != null) {
							ErrorResult errorResult;
							if (t instanceof ResponseStatusError) {
								ResponseStatusError err = (ResponseStatusError) t;
								errorResult = new ErrorResult(err);
							} else {
								errorResult = new ErrorResult(t);
							}

							callback.onFailureForUiThread(errorResult);
						} else {
							callback.onSuccessForUiThread(response);
						}
					} finally {
						lock.countDown();
					}
				}
			});

			lock.await();
			return result;
		}
	};

	public abstract T call() throws Exception;

	final public Callable<T> getCallable() {
		return task;
	}

}
