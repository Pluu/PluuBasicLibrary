package com.pluu.pluubasiclibrary.pluu.network.request;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

/**
 * 결과 데이터를 JSONObject 반환하도록 하는 Request
 * @author PLUUSYSTEM
 */
public class JsonByStringRequest extends Request<JSONObject> {

	private final Listener<JSONObject> mListener;

    public JsonByStringRequest(int method, String url, Listener<JSONObject> listener,
            ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }

    public JsonByStringRequest(String url, Listener<JSONObject> listener, ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
    }
	
	@Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString =
                new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

	@Override
	protected void deliverResponse(JSONObject response)
	{
		mListener.onResponse(response);
	}

}
