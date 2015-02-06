package com.pluu.pluubasiclibrary.pluu.network.request;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 * 결과 데이터를 String 반환하도록 하는 Multipart Supported Request
 * @author PLUUSYSTEM
 */
public class MultipartRequest extends Request<String> {
	
	private MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	private HttpEntity entity;

    private final Response.Listener<String> mListener;
    private final Map<String, String> mStringParts;
    private final Map<String, File> mFileParts;

    public MultipartRequest(String url, Response.Listener<String> listener,
            Response.ErrorListener errorListener,
            Map<String, String> stringParts, Map<String, File> fileParts) {
        super(Method.POST, url, errorListener);

        mListener = listener;
        mStringParts = stringParts;
        mFileParts = fileParts;
        buildMultipartEntity();
    }

    private void buildMultipartEntity() {
    	if (mStringParts != null && mStringParts.size() > 0)
		{
    		for (Map.Entry<String, String> entry : mStringParts.entrySet()) {
    			builder.addTextBody(entry.getKey(), entry.getValue());
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
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        return Response.success("Uploaded", getCacheEntry());
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

}
