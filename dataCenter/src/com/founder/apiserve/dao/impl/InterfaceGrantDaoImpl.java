package com.founder.apiserve.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.founder.apiserve.dao.InterfaceGrantDao;
import com.founder.apiserve.domain.InterfaceGrant;
import com.founder.base.dao.impl.BaseDaoImpl;

@Repository
public class InterfaceGrantDaoImpl extends BaseDaoImpl<InterfaceGrant>
		implements InterfaceGrantDao {

	public InterfaceGrantDaoImpl() {
		super.setNs("com.apiserve.domain.InterfaceGrant");
	}

	public void changeSate(Integer[] ids, Integer i) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("state", i);
		getSqlSession().update(getNs() + ".changState", map);
	}

	public void updateViewAndUploadNum(Map map) {
		getSqlSession().update(getNs() + ".updateViewAndUploadNum", map);
	}

	public List<InterfaceGrant> findServiceName() {
		return getSqlSession().selectList(getNs() + ".findServiceName");
	}

	public List<InterfaceGrant> findAll(Map<String, Object> map) {
		return getSqlSession().selectList(getNs() + ".findAll");
	}

	public List<InterfaceGrant> findGrantStatus() {
		return getSqlSession().selectList(getNs() + ".findGrantStatus");
	}
}
