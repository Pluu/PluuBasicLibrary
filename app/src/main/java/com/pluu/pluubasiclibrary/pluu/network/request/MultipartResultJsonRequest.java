package com.pluu.pluubasiclibrary.pluu.network.request;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 * 결과 데이터를 JSONObject 반환하도록 하는 Multipart Supported Request
 * @author PLUUSYSTEM
 */
public class MultipartResultJsonRequest extends Request<JSONObject> {
	
	private MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	private HttpEntity entity;

    private final Response.Listener<JSONObject> mListener;
    private final Map<String, String> mParts;
    private final Map<String, File> mFileParts;

    public MultipartResultJsonRequest(String url, Response.Listener<JSONObject> listener,
            Response.ErrorListener errorListener,
            Map<String, String> stringParts, Map<String, File> fileParts) {
        super(Method.POST, url, errorListener);

        mListener = listener;
        mParts = stringParts;
        mFileParts = fileParts;
        buildMultipartEntity();
    }

    private void buildMultipartEntity() {
    	builder.setCharset(Consts.UTF_8);
    	
    	if (mParts != null && mParts.size() > 0)
		{
    		ContentType textContentType = ContentType.create("text/plain", Consts.UTF_8);
    		
    		for (Map.Entry<String, String> entry : mParts.entrySet()) {
    			builder.addTextBody(entry.getKey(), entry.getValue(), textContentType);
    		}
		}
    	
    	if (mFileParts != null && mFileParts.size() > 0)
		{
	    	for (Map.Entry<String, File> entry : mFileParts.entrySet()) {
	    		builder.addBinaryBody(entry.getKey(), entry.getValue());
	        }
		}
    	
    	entity = builder.build();
    }
    
	@Override
	public String getBodyContentType()
	{
		return entity.getContentType().getValue();
	}

	public HttpEntity getEntity()
	{
		return entity;
	}
	
	@Override
	public byte[] getBody() throws AuthFailureError
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try
		{
			entity.writeTo(bos);
		} catch (IOException e)
		{
			VolleyLog.e("IOException writing to ByteArrayOutputStream");
		}
		return bos.toByteArray();
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
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }

}
