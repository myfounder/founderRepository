package com.founder.apiserve.dao;

import java.util.List;

import com.founder.apiserve.domain.Interfaces;
import com.founder.base.dao.BaseDao;

public interface InterfacesDao extends BaseDao<Interfaces> {

	/**
	 * @description:
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-10 下午01:51:24
	 */
	public List<Interfaces> findAll();

	/**
	 * @description:
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-10 下午03:20:19
	 */
	public List<Interfaces> findInterfacesName();

	public List<Interfaces> findInterfacesNameOpen();

	public Interfaces findInterfacesNameByCode(String checkCode);

	/**
	 * 查询所有接口种类
	 * 
	 * @return
	 */
	public List<Interfaces> getInterfaceSpecies();

}
