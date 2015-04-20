package com.founder.apiserve.service;

import java.util.List;
import java.util.Map;

import com.founder.apiserve.domain.InterfaceGrant;
import com.founder.base.page.Page;

public interface InterfaceGrantService {

	public List<InterfaceGrant> findPage(Page page, Map paraMap); // 分页查询

	public List<InterfaceGrant> find(Map paraMap); // 带条件查询，条件可以为null，既没有条件；返回lisDocument对象集合

	public InterfaceGrant get(Integer id); // 只查询一个，常用于修改

	public void insert(InterfaceGrant itg); // 插入，用实体作为参数

	public void update(InterfaceGrant itg); // 修改，用实体作为参数

	public void deleteById(Integer id); // 按id删除，删除一条；支持整数型和字符串类型ID

	public void delete(Integer[] ids);

	public void audit(Integer[] ids); // 审核state状态变成2.

	public void toAudit(Integer[] ids); // 提交state状态变成0.

	public int count(Map paraMap);

	public void updateViewAndUploadNum(Map map);

	public List<InterfaceGrant> findServiceName();// 查询接口服务名称列表

	public List<InterfaceGrant> findGrantStatus();// 获取 用户授权状态

	public List<InterfaceGrant> findAll(Map<String, Object> map);// 查询 所有

}
