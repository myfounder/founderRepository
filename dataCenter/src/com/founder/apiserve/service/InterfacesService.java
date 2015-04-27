package com.founder.apiserve.service;

import java.util.List;
import java.util.Map;

import com.founder.apiserve.domain.Interfaces;
import com.founder.base.page.Page;

public interface InterfacesService {

	/**
	 * @description:查询所有接口
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-10 下午01:50:04
	 */
	public List<Interfaces> findAll();

	/**
	 * @description:保存
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-10 下午02:49:33
	 */
	public void insert(Interfaces interfaces);

	/**
	 * @description:查询接口名称有哪些
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-10 下午03:18:08
	 */
	public List<Interfaces> findInterfacesName();

	/**
	 * @description:分页查询
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-10 下午04:11:21
	 */
	public List<Interfaces> findPage(Page page, Map paraMap);

	/**
	 * @description:根据ID查询接口对象
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-11 下午06:05:19
	 */
	public Interfaces findById(Integer id);

	/**
	 * @description:根据实体类对象修改
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-12 上午10:58:38
	 */
	public void update(Interfaces interfaces);

	public List<Interfaces> findInterfacesNameOpen();

	public Interfaces findInterfacesNameByCode(String checkCode);

}
