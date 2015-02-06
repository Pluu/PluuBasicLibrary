package com.pluu.pluubasiclibrary.pluu.network;

import java.util.List;

/**
 * Callback Resource Interface
 * @author PLUUSYSTEM
 */
public interface IResource {
	/**
	 * Get Response Result - Code
	 * @return code
	 */
	public IParam.CODE getCode();
	/**
	 * Get Response Result - Message
	 * @return Message
	 */
	public String getMessage();
	/**
	 * Is Request Result
	 * @return true / false
	 */
	public boolean isSuccessed();
	/**
	 * Get Response Result - List
	 * @return list
	 */
	public List<?> getList();
	/**
	 * Get Response Result - List Count
	 * @return the number of elements in this List
	 */
	public int getListCount();
}
