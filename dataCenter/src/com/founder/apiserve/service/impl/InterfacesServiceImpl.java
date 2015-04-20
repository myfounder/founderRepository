package com.founder.apiserve.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.apiserve.dao.InterfacesDao;
import com.founder.apiserve.domain.Interfaces;
import com.founder.apiserve.service.InterfacesService;
import com.founder.base.page.Page;

@Service
public class InterfacesServiceImpl implements InterfacesService {

	@Autowired
	private InterfacesDao interfacesDao;

	/**
	 * 查询所有接口
	 */
	public List<Interfaces> findAll() {
		return interfacesDao.findAll();
	}

	/**
	 * 保存接口
	 */
	public void insert(Interfaces interfaces) {
		// interfaces.setRequestMode("POST");
		interfacesDao.insert(interfaces);
	}

	/**
	 * 查询所有接口的名称
	 */
	public List<Interfaces> findInterfacesName() {
		return interfacesDao.findInterfacesName();
	}

	/**
	 * 分页,带条件查询
	 */
	public List<Interfaces> findPage(Page page, Map paraMap) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String code = (String) paraMap.get("code");// 接口编码
		String species = (String) paraMap.get("species");// 接口种类
		String pageNoS = (String) paraMap.get("pageNo");// 当前页码
		int pageNo;
		if (StringUtils.isNotBlank(pageNoS)) {
			pageNo = Integer.parseInt(pageNoS);
		} else {
			pageNo = page.getPageNo();
		}
		hashMap.put("code", code);
		hashMap.put("species", species);
		page.setTotalRecord(getCount(hashMap));
		hashMap.put("startRow", (pageNo - 1) * page.getPageSize());
		hashMap.put("endRow", pageNo * page.getPageSize() + 1);
		List<Interfaces> list = interfacesDao.find(hashMap);
		return list;
	}

	/**
	 * @description:根据条件查询总记录数
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-10 下午04:32:38
	 */
	public int getCount(HashMap<String, Object> hashMap) {
		int count = interfacesDao.count(hashMap);
		return count;
	}

	public Interfaces findById(Integer id) {
		return interfacesDao.get(id);
	}

	public void update(Interfaces interfaces) {
		interfacesDao.update(interfaces);
	}

	public List<Interfaces> findInterfacesNameOpen() {
		return interfacesDao.findInterfacesNameOpen();
	}

	public Interfaces findInterfacesNameByCode(String checkCode) {
		return interfacesDao.findInterfacesNameByCode(checkCode);
	}

}
