package com.pluu.pluubasiclibrary.pluu.network_Kakao.response;

import android.net.Uri;

import java.net.HttpURLConnection;

import com.pluu.pluubasiclibrary.pluu.network_Kakao.ServerProtocol;
import com.pluu.pluubasiclibrary.pluu.network_Kakao.StringSet;


/**
 * NeoGeo Base Api Response
 * Created by nohhs on 2015-10-21.
 */
public class NeoGeoApiResponse extends JSONObjectResponse {

	protected NeoGeoApiResponse(ResponseData responseData)
		throws ResponseBody.ResponseBodyException, ApiResponseStatusError {
		super(responseData);

		checkResultCode();
	}

	/**
	 * API Result Code 처리
	 * @throws ResponseBody.ResponseBodyException
	 * @throws ApiResponseStatusError
	 */
	private void checkResultCode() throws ResponseBody.ResponseBodyException, ApiResponseStatusError {
		ResponseBody body = getBody();
		try {
			int resultCode = body.getInt(StringSet.RESULT_CODE);
			if (ServerProtocol.RESULT_OK_CODE != resultCode) {
				throw new ApiResponseStatusError(resultCode,
												 body.getString(StringSet.RESULT_ERROR),
												 HttpURLConnection.HTTP_OK);
			}
		} catch (ResponseBody.ResponseBodyException e) {
			e.printStackTrace();
			throw new ResponseBody.ResponseBodyException(e);
		}
	}

	protected static String createBaseURL(final String authority, final String requestPath, Object... args) {
		Uri.Builder builder = new Uri.Builder();
		builder.scheme(ServerProtocol.URL_SCHEME);
		builder.encodedAuthority(authority);
		builder.path(requestPath);

		if (args != null && args.length > 0) {
			for (Object arg : args) {
				builder.appendPath(String.valueOf(arg));
			}
		}
		return builder.build().toString();
	}

}
