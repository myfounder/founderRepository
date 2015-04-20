package com.founder.apiserve.domain;

import java.util.Date;

/**
 * 
 * @description:接收页面的查询条件，封装成一个对象
 * @author: YeJianPing
 * @date：2015-3-17
 */
public class InterfaceServiceLogQC {
	private String name;// 接口名称
	private Date startTime;// 开始时间
	private Date endTime;// 接收时间

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}