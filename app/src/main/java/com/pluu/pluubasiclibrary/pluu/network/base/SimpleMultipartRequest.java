package com.pluu.pluubasiclibrary.pluu.network.base;

import java.io.File;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;

import com.pluu.pluubasiclibrary.pluu.network.AbstractResource;

/**
 * Simple Multipart Supported Request
 *
 * @author PLUUSYSTEM
 */
public abstract class SimpleMultipartRequest extends AbstractResource {

	protected SimpleMultipartRequest(Context context) {
		super(context);
	}

	@Override
	protected boolean isMultiPart() {
		return true;
	}

	@Override
	protected void parse(JSONObject param) throws JSONException {
	}

	protected void setMultipartData(Map<String, File> data, String tag, String path) {
		if (TextUtils.isEmpty(tag)
			|| TextUtils.isEmpty(path)) {
			return;
		}
		data.put(tag, new File(path));
	}

}
