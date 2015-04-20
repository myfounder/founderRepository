package com.founder.apiserve.domain;

/**
 * 
 * @description:接收页面传递的查询条件
 * @author: YeJianPing
 * @date：2015-3-10
 */
public class InterfaceGrantQC {
	private String name;// 接口名称
	private String systemName;// 系统名称

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

}
