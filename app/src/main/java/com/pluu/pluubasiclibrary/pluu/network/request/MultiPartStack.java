package com.pluu.pluubasiclibrary.pluu.network.request;

import java.io.IOException;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HurlStack;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * Only Multipart Supported HurlStack
 * @author PLUUSYSTEM
 */
public class MultiPartStack extends HurlStack {
	private final static String HEADER_CONTENT_TYPE = "Content-Type";

    @Override
    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders)
            throws AuthFailureError, IOException {
        if (!(request instanceof MultipartRequest)) {
            return super.performRequest(request, additionalHeaders);
        }
        HttpPost httpRequest = new HttpPost(request.getUrl());
        httpRequest.addHeader(HEADER_CONTENT_TYPE, request.getBodyContentType());
        setMultiPartBody(httpRequest, request);
        addHeaders(httpRequest, additionalHeaders);
        addHeaders(httpRequest, request.getHeaders());
        HttpParams httpParams = httpRequest.getParams();
        int timeoutMs = request.getTimeoutMs();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        HttpConnectionParams.setSoTimeout(httpParams, timeoutMs);

        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", new PlainSocketFactory(), 80));
        registry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

        ThreadSafeClientConnManager manager = new ThreadSafeClientConnManager(httpParams, registry);
        HttpClient httpClient = new DefaultHttpClient(manager, httpParams);

        return httpClient.execute(httpRequest);
    }

    private void addHeaders(HttpUriRequest httpRequest, Map<String, String> headers) {
        for (String key : headers.keySet()) {
            httpRequest.setHeader(key, headers.get(key));
        }
    }

    private static void setMultiPartBody(HttpEntityEnclosingRequestBase httpRequest,
            Request<?> request) throws AuthFailureError {
        if (request instanceof MultipartRequest) {
            httpRequest.setEntity(((MultipartRequest) request).getEntity());
        }
    }

}
