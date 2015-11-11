package com.pluu.pluubasiclibrary.pluu.network_Kakao.response;

import java.util.NoSuchElementException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pluu.pluubasiclibrary.pluu.network_Kakao.response.ResponseBody.ResponseBodyException;

/**
 * JSONArray Support Response Body
 * Created by nohhs on 2015-10-12.
 */
public class ResponseBodyArray {

	private final JSONArray jsonArray;
	private final int statusCode;

	public int getStatusCode() {
		return statusCode;
	}

	public ResponseBodyArray(int statusCode, String body) throws ResponseBodyException {
		this.statusCode = statusCode;
		if (body == null) {
			throw new ResponseBodyException();
		}

		try {
			this.jsonArray = new JSONArray(body);
		} catch (JSONException e) {
			throw new ResponseBodyException(e);
		}
	}

	public ResponseBodyArray(int statusCode, JSONArray jsonArray) throws ResponseBodyException {
		this.statusCode = statusCode;
		if (jsonArray == null) {
			throw new ResponseBodyException();
		}
		this.jsonArray = jsonArray;
	}
	public int length() {
		return jsonArray.length();
	}
	public long getLong(int i) throws ResponseBodyException {
		try {
			Object obj = getOrThrow(i);
			if (obj instanceof Integer) {
				return (Integer) obj;
			}
			else if (obj instanceof Long) {
				return (Long) obj;
			}
			else {
				throw new ResponseBodyException();
			}
		}
		catch(Exception e) {
			throw new ResponseBodyException(e);
		}
	}
	public ResponseBody getBody(int i) throws ResponseBodyException {
		try {
			return new ResponseBody(getStatusCode(), (JSONObject) getOrThrow(i));
		}
		catch(ResponseBodyException e) {
			throw e;
		}
		catch(Exception e) {
			throw new ResponseBodyException(e);
		}
	}
	public String getString(int i) throws ResponseBodyException {
		try {
			return (String) getOrThrow(i);
		}
		catch(Exception e) {
			throw new ResponseBodyException(e);
		}
	}

	public int getInt(int i) throws ResponseBodyException {
		try {
			return (Integer) getOrThrow(i);
		}
		catch(Exception e) {
			throw new ResponseBodyException(e);
		}
	}

	public Boolean getBoolean(int i) throws ResponseBodyException {
		try {
			return (Boolean) getOrThrow(i);
		}
		catch(Exception e) {
			throw new ResponseBodyException(e);
		}
	}

	private Object getOrThrow(int index) {
		Object v = null;
		try {
			v = jsonArray.get(index);
		} catch (JSONException ignore) {
		}

		if (v == null) {
			throw new NoSuchElementException();
		}
		return v;
	}

	@Override
	public String toString() {
		return jsonArray.toString();
	}

}
