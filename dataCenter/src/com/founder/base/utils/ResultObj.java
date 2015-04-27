package com.founder.base.utils;

import java.io.Serializable;

public class ResultObj<T> implements Serializable {

	private static final long serialVersionUID = -540957848950019573L;
	/**
	 * @param 是否成功
	 */
	private boolean success = false;
	/**
	 * @param 消息信息
	 */
	private String message = "";
	/**
	 * @param 结果数据
	 */
	private T data = null;
	/**
	 * @param 清除缓存返回值
	 */
	private Object cacheReturnObj = null;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	public Object getCacheReturnObj() {
		return cacheReturnObj;
	}

	public void setCacheReturnObj(Object cacheReturnObj) {
		this.cacheReturnObj = cacheReturnObj;
	}

	@Override
	public String toString() {
		return org.apache.commons.lang.builder.ReflectionToStringBuilder
				.toString(this);
	}

}
