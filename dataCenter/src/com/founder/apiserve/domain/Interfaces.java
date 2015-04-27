package com.founder.apiserve.domain;

import java.io.Serializable;

public class Interfaces implements Serializable {

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = -9086361207860722760L;

	private Integer id = 0;
	private String code;// 接口code
	private String name;// 接口名称
	private String species;
	// 接口种类,1-服务资源类接口,2-请求服务类接口,3-非结构化信息访问接口,4-分析类接口
	private String explain;// 接口说明----未用字段
	private String status;// 状态，默认是0 1
	private String isRun;// 开关，0表示关，1表示开。默认是0
	private String url;// 接口访问路径
	private String requestMode;// 请求方式
	private String dataFormat;// 数据交换格式

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsRun() {
		return isRun;
	}

	public void setIsRun(String isRun) {
		this.isRun = isRun;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRequestMode() {
		return requestMode;
	}

	public void setRequestMode(String requestMode) {
		this.requestMode = requestMode;
	}

	public String getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	@Override
	public String toString() {
		return "Interfaces [code=" + code + ", dataFormat=" + dataFormat
				+ ", explain=" + explain + ", id=" + id + ", isRun=" + isRun
				+ ", name=" + name + ", requestMode=" + requestMode
				+ ", species=" + species + ", status=" + status + ", url="
				+ url + "]";
	}

}