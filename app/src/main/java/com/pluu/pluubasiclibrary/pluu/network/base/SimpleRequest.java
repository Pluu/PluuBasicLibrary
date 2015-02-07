package com.pluu.pluubasiclibrary.pluu.network.base;

import java.io.File;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.pluu.pluubasiclibrary.pluu.network.AbstractResource;

/**
 * Simple Request
 * @author PLUUSYSTEM
 */
public abstract class SimpleRequest extends AbstractResource {

	protected SimpleRequest(Context context) {
		super(context);
	}

	@Override
	protected boolean isMultiPart() {
		return false;
	}

	@Override
	protected Map<String, File> getMultiPartContent() {
		return null;
	}

	@Override
	protected void parse(JSONObject param) throws JSONException {
	}

}
