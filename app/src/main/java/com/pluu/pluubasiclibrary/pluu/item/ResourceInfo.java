package com.pluu.pluubasiclibrary.pluu.item;

/**
 * Created by PLUUSYSTEM-NEW on 2015-01-29.
 */
public class ResourceInfo {
	private final String name;
	private final Integer id;

	public ResourceInfo(String name, Integer id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ResourceInfo{" +
			"name='" + name + '\'' +
			", id=" + id +
			'}';
	}
}
