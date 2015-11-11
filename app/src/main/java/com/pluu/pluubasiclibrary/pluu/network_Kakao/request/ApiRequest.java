package com.pluu.pluubasiclibrary.pluu.network_Kakao.request;

import android.net.Uri;

import java.util.Collections;
import java.util.Map;

import com.pluu.pluubasiclibrary.pluu.network_Kakao.ServerProtocol;


/**
 * Abstract Request Class
 * Created by nohhs on 2015-10-06.
 */
public abstract class ApiRequest implements IRequest {
	private final String TAG = ApiRequest.class.getSimpleName();

	static final String POST = "POST";
	protected static final String GET = "GET";
	protected static final String DELETE = "DELETE";

	// //////////////////////////////////////////////////
	// Network Resource
	// //////////////////////////////////////////////////

	public abstract String getMethod();

	public abstract String getUrl();

	@Override
	public Map<String, String> getParams() {
		return Collections.emptyMap();
	}

	@Override
	public Map<String, String> getHeaders() {
		return Collections.emptyMap();
	}

	@Override
	public String getBodyEncoding() {
		return ServerProtocol.API_BODY_ENCODING;
	}

	protected static String createBaseURL(final String authority, final String requestPath) {
		Uri.Builder builder = new Uri.Builder();
		builder.scheme(ServerProtocol.URL_SCHEME);
		builder.encodedAuthority(authority);
		builder.path(requestPath);
		return builder.build().toString();
	}

	protected static String createBaseURL(final String authority, final String requestPath, Object... args) {
		Uri.Builder builder = new Uri.Builder();
		builder.scheme(ServerProtocol.URL_SCHEME);
		builder.encodedAuthority(authority);

		if (args != null && args.length > 0) {
			builder.path(String.format(requestPath, args));
		} else {
			builder.path(requestPath);
		}
		return builder.build().toString();
	}

}
