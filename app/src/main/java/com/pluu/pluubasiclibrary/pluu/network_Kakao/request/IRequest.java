package com.pluu.pluubasiclibrary.pluu.network_Kakao.request;

import java.util.Map;

/**
 * Request Interface
 * Created by nohhs on 2015-10-06.
 */
public interface IRequest {

	/**
	 * http 통신방법.
	 * @return httpMethod. GET, POST, PUT, DELETE 등등.
	 */
	String getMethod();

	/**
	 * 요청할 target url.
	 * @return 요청할 target url.
	 */
	String getUrl();

	/**
	 * http 요청에 필요한 params.
	 * @return http 요청에 필요한 params.
	 */
	Map<String, String> getParams();

	/**
	 * http 요청에 필요한 headers.
	 * @return http 요청에 필요한 headers.
	 */
	Map<String, String> getHeaders();

//	/**
//	 * MultiPart에 대한 정보
//	 * MultiPart로 요청을 보낼때 값을 채워주면 된다.
//	 * @return MultiPart에 대한 정보
//	 */
//	List<Part> getMultiPartList();

	/**
	 * param 의 encording 정보.
	 * @return param 의 encording 정보. default "UTF-8"
	 */
	String getBodyEncoding();

}
