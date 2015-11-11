package com.pluu.pluubasiclibrary.pluu.network_Kakao.response;


/**
 * JSONObject Response
 * Created by nohhs on 2015-10-12.
 */
public class JSONObjectResponse extends ApiResponse {

	protected final ResponseBody body;

	protected JSONObjectResponse(ResponseData responseData)
		throws ResponseBody.ResponseBodyException, ApiResponseStatusError {
		super(responseData);
		body = new ResponseBody(responseData.getHttpStatusCode(), responseData.getResponseString());
	}

	protected ResponseBody getBody() {
		return body;
	}

}
