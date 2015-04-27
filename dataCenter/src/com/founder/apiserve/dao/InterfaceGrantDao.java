package com.founder.apiserve.dao;

import java.util.List;
import java.util.Map;

import com.founder.apiserve.domain.InterfaceGrant;
import com.founder.base.dao.BaseDao;

public interface InterfaceGrantDao extends BaseDao<InterfaceGrant> {

	public void changeSate(Integer[] ids, Integer i);

	public void updateViewAndUploadNum(Map map);

	public List<InterfaceGrant> findServiceName();// 查询接口服务列表

	public List<InterfaceGrant> findAll(Map<String, Object> map);// 查询所有

	public List<InterfaceGrant> findGrantStatus();

}
