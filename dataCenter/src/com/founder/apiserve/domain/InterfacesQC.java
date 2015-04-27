package com.founder.apiserve.domain;

/**
 * 
 * @description:接收页面的查询条件，封装成一个对象
 * @author: YeJianPing
 * @date：2015-3-10
 */
public class InterfacesQC {

	private String code;// 接口code
	private String name;// 接口名称-----为用到
	private String isRun;// 开关，0表示关，1表示开。默认是0
	private String species;// 接口种类

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsRun() {
		return isRun;
	}

	public void setIsRun(String isRun) {
		this.isRun = isRun;
	}

}