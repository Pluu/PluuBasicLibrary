package com.pluu.pluubasiclibrary.pluu.network_Kakao.response;

import java.util.NoSuchElementException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * JSONObject Support Response Body
 * Created by nohhs on 2015-10-12.
 */
public class ResponseBody {

	public static class ResponseBodyException extends Exception {
		private static final long serialVersionUID = 8171429617556607125L;

		public ResponseBodyException() {
		}

		public ResponseBodyException(String errMsg) {
			super(errMsg);
		}

		public ResponseBodyException(Exception e) {
			super(e);
		}
	}

	private JSONObject json = null;
	private final int statusCode;

	public int getStatusCode() {
		return statusCode;
	}

	public ResponseBody(int statusCode, String body) throws ResponseBodyException {
		this.statusCode = statusCode;
		if (body == null) {
			throw new ResponseBodyException();
		}

		try {
			this.json = new JSONObject(body);
		} catch (JSONException e) {
			throw new ResponseBodyException(e);
		}
	}

	public ResponseBody(int statusCode, JSONObject body) throws ResponseBodyException {
		this.statusCode = statusCode;
		if (body == null) {
			throw new ResponseBodyException();
		}
		this.json = body;
	}

	private Object getOrThrow(String key) {
		Object v = null;
		try {
			v = json.get(key);
		} catch (JSONException ignor) {
		}

		if (v == null) {
			throw new NoSuchElementException(key);
		}

		if (v == JSONObject.NULL) {
			return null;
		}
		return v;
	}

	public long getLong(String key) throws ResponseBodyException {
		try {
			Object obj = getOrThrow(key);
			if (obj instanceof Integer) {
				return (Integer) obj;
			}
			else if (obj instanceof Long) {
				return (Long) obj;
			}
			else {
				throw new ResponseBodyException();
			}
		} catch (ResponseBodyException e) {
			throw e;
		} catch (Exception e) {
			throw new ResponseBodyException(e);
		}
	}

	public int getInt(String key) throws ResponseBodyException {
		try {
			return (Integer) getOrThrow(key);
		} catch (Exception e) {
			throw new ResponseBodyException(e);
		}
	}

	public boolean has(String key) {
		return json.has(key);
	}

	public int optInt(String key, int def) {
		if (has(key)) {
			try {
				return getInt(key);
			} catch (ResponseBodyException e) {
			}
		}
		return def;
	}

	public String getString(String key) throws ResponseBodyException {
		try {
			return (String) getOrThrow(key);
		} catch (Exception e) {
			throw new ResponseBodyException(e);
		}
	}

	public String optString(String key, String def) {
		if (has(key)) {
			try {
				return getString(key);
			} catch (ResponseBodyException e) {
			}
		}
		return def;
	}

	public boolean getBoolean(String key) throws ResponseBodyException {
		try {
			return (Boolean) getOrThrow(key);
		} catch (Exception e) {
			throw new ResponseBodyException(e);
		}
	}

	public ResponseBody getBody(String key) throws ResponseBodyException {
		try {
			return new ResponseBody(getStatusCode(), (JSONObject)getOrThrow(key));
		} catch (Exception e) {
			throw new ResponseBodyException(e);
		}
	}

	public ResponseBodyArray getBodyArray(String key) throws ResponseBodyException {
		try {
			return new ResponseBodyArray(getStatusCode(), (JSONArray)getOrThrow(key));
		} catch (Exception e) {
			throw new ResponseBodyException(e);
		}
	}

	public ResponseBody optBody(String key, ResponseBody def) {
		if (has(key)) {
			try {
				return getBody(key);
			} catch (ResponseBodyException e) {
			}
		}
		return def;
	}

	public boolean optBoolean(String key, boolean def) {
		if (has(key)) {
			try {
				return getBoolean(key);
			} catch (ResponseBodyException e) {
			}
		}
		return def;
	}

	public long optLong(String key, long def) {
		if (has(key)) {
			try {
				return getLong(key);
			} catch (ResponseBodyException e) {
			}
		}
		return def;
	}

	@Override
	public String toString() {
		return json.toString();
	}

}
