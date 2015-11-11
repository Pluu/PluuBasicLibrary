package com.pluu.pluubasiclibrary.pluu.network_Kakao.util;

/**
 * Log Tag Class
 * Created by nohhs on 2015-10-14.
 */
public enum Tag {
	// TODO: Package Name
	DEFAULT("com.pluu.pluubasiclibrary");

	private final String tag;

	Tag(String tag) {
		this.tag = tag;
	}

	public String tag() {
		return tag;
	}
}
