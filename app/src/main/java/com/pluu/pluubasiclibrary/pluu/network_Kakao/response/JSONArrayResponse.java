package com.pluu.pluubasiclibrary.pluu.network_Kakao.response;


/**
 * JSONArray Response
 * Created by nohhs on 2015-10-12.
 */
public class JSONArrayResponse extends ApiResponse {

	protected final ResponseBodyArray body;

	protected JSONArrayResponse(ResponseData responseData)
		throws ResponseBody.ResponseBodyException, ApiResponseStatusError {
		super(responseData);
		body = new ResponseBodyArray(responseData.getHttpStatusCode(), responseData.getResponseString());
	}

}
