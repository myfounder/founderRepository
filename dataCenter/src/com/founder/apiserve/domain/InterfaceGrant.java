package com.founder.apiserve.domain;

import java.io.Serializable;

public class InterfaceGrant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7362010953717508535L;

	private Integer id = 0;

	private String name;

	private String systemName;

	private String systemIp;

	private String portNo;

	private String status;

	private String description;

	private String explain;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getSystemIp() {
		return systemIp;
	}

	public void setSystemIp(String systemIp) {
		this.systemIp = systemIp;
	}

	public String getPortNo() {
		return portNo;
	}

	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	@Override
	public String toString() {
		return "InterfaceGrant [id=" + id + ", name=" + name + ", systemName="
				+ systemName + ", systemIp=" + systemIp + ", portNo=" + portNo
				+ ", status=" + status + ", description=" + description
				+ ", explain=" + explain + "]";
	}

}