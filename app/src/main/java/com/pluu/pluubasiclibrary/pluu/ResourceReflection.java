package com.pluu.pluubasiclibrary.pluu;

import android.R.drawable;
import android.util.Log;

import com.google.common.collect.Maps;
import com.pluu.pluubasiclibrary.pluu.item.ResourceInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by PLUUSYSTEM-NEW on 2015-01-29.
 */
public class ResourceReflection {
	private final String TAG = this.getClass().getSimpleName();
	private final Map<String, Class<drawable>> cacheMap = Maps.newHashMap();

	@SuppressWarnings("unchecked")
	public ArrayList<ResourceInfo> getItemList(String baseClass, String fullClass){
		ArrayList<ResourceInfo> list = new ArrayList<>();
		Class<drawable> rString = null;
		Class<?> rClass;
		try {
			if (cacheMap.containsKey(fullClass)) {
				rString = cacheMap.get(fullClass);
			} else {
				rClass = Class.forName(baseClass);
				Class<?>[] subClassTable = rClass.getDeclaredClasses();
				for (Class<?> subclass : subClassTable) {
					if (fullClass.equals(subclass.getCanonicalName())) {
						rString = (Class<drawable>) subclass;
						cacheMap.put(fullClass, rString);
					}
				}
			}

			Field[] strings = rString.getFields();
			for (Field dr : strings) {
				list.add(new ResourceInfo(dr.getName(), dr.getInt(null)));
			}
		} catch (Exception e) {
			Log.e(TAG, "redShow (" + baseClass + "/" +  fullClass + "): ", e);
		}
		return list;
	}

	public ArrayList<String> getSubClasses(String baseClass) {
		ArrayList<String> subClassList = new ArrayList<>();

		try {
			Class<?> rClass = Class.forName(baseClass);
			Class<?>[] subClassTable = rClass.getDeclaredClasses();
			for (Class<?> subclass : subClassTable) {
				subClassList.add(subclass.getCanonicalName());
			}

		} catch (Exception e) {
			Log.e(TAG, "^ logSubClasses() redShow: ", e);
		}
		sortStringList(subClassList);
		return subClassList;
	}

	private void sortStringList(List<String> list) {
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String lhs, String rhs) {
				return lhs.compareToIgnoreCase(rhs);
			}
		});
	}

}
