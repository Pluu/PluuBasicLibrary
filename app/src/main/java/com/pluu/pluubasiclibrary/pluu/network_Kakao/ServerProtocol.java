package com.pluu.pluubasiclibrary.pluu.network_Kakao;


import com.pluu.pluubasiclibrary.pluu.network_Kakao.util.Helper;

/**
 * Server Protocol Constance
 * Created by nohhs on 2015-10-12.
 */
public class ServerProtocol {
	private static final Helper.DeployPhase DEPLOY_PHASE = Helper.DeployPhase.current();

	public static final String API_BODY_ENCODING = "UTF-8";

	// URL Scheme
	public static final String URL_SCHEME = initURLScheme();
	// API Authority
	public static final String API_AUTHORITY = initAPIAuthority();

	// Api Result Ok Code
	public static final int RESULT_OK_CODE = 1;

	/**
	 * Get Url Scheme
	 * @return Scheme
	 */
	private static String initURLScheme() {
		switch (DEPLOY_PHASE) {
			default:
				return "http";
		}
	}

	/**
	 * Get API Authority
	 * @return API URL
	 */
	private static String initAPIAuthority() {
		switch (DEPLOY_PHASE) {
			case Release:
				return "10.6.52.68:9000/publishingsdk";
			default:
				return "192.168.0.9:8282/publishingsdk";
		}

	}

}
