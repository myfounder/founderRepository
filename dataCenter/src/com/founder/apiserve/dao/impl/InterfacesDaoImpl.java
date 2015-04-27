package com.founder.apiserve.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.founder.apiserve.dao.InterfacesDao;
import com.founder.apiserve.domain.Interfaces;
import com.founder.base.dao.impl.BaseDaoImpl;

@Repository
public class InterfacesDaoImpl extends BaseDaoImpl<Interfaces> implements
		InterfacesDao {

	public InterfacesDaoImpl() {
		super.setNs("com.apiserve.domain.Interfaces");
	}

	/**
	 * 查询所有接口
	 */
	public List<Interfaces> findAll() {
		return this.getSqlSession().selectList(getNs() + ".findAll");
	}

	/**
	 * 查询所有接口的名称
	 */
	public List<Interfaces> findInterfacesName() {
		return this.getSqlSession().selectList(getNs() + ".findInterfacesName");
	}

	public List<Interfaces> findInterfacesNameOpen() {
		return this.getSqlSession().selectList(
				getNs() + ".findInterfacesNameOpen");
	}

	public Interfaces findInterfacesNameByCode(String checkCode) {
		List<Interfaces> list = this.getSqlSession().selectList(
				getNs() + ".findInterfacesNameByCode", checkCode);
		return list.size() == 0 ? null : list.get(0);
	}

	/**
	 * 查询所有接口种类
	 */
	public List<Interfaces> getInterfaceSpecies() {
		return this.getSqlSession().selectList(getNs(), ".getInterfaceSpecies");
	}
}
