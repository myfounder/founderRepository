package com.founder.apiserve.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @description:接口服务日志实体类
 * @author: YeJianPing
 * @date：2015-3-17
 */
/**
 * @description:
 * @author: YeJianPing
 * @date：2015-3-17
 */
public class InterfaceServiceLog implements Serializable {

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 6664809322030192733L;
	private Integer id = 0;
	private String name;// 接口名称
	private String species;// 接口种类

	private String systemName;// 系统名称
	private String systemIp;// 系统IP
	private String status;// 授权状态

	private Date serviceTime;// 服务时间
	private String serviceFeedback;// 服务反馈
	private Integer dataAmount;// 数据量---未用字段
	private Date requestTime;// 请求时间

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

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Date serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getServiceFeedback() {
		return serviceFeedback;
	}

	public void setServiceFeedback(String serviceFeedback) {
		this.serviceFeedback = serviceFeedback;
	}

	public Integer getDataAmount() {
		return dataAmount;
	}

	public void setDataAmount(Integer dataAmount) {
		this.dataAmount = dataAmount;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

}