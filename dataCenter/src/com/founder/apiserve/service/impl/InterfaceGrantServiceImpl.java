package com.founder.apiserve.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.apiserve.dao.InterfaceGrantDao;
import com.founder.apiserve.domain.InterfaceGrant;
import com.founder.apiserve.service.InterfaceGrantService;
import com.founder.base.page.Page;

@Service
public class InterfaceGrantServiceImpl implements InterfaceGrantService {

	@Autowired
	private InterfaceGrantDao interfaceGrantDao;

	public List<InterfaceGrant> findPage(Page page, Map paraMap) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String name = (String) paraMap.get("name");// 接口名称
		String systemName = (String) paraMap.get("systemName");// 系统名称
		String pageNoS = (String) paraMap.get("pageNo");// 当前页码
		int pageNo;
		if (StringUtils.isNotBlank(pageNoS)) {
			pageNo = Integer.parseInt(pageNoS);
		} else {
			pageNo = page.getPageNo();
		}
		hashMap.put("name", name);
		hashMap.put("systemName", systemName);

		page.setTotalRecord(getCount(hashMap));

		hashMap.put("startRow", (pageNo - 1) * page.getPageSize());
		hashMap.put("endRow", pageNo * page.getPageSize() + 1);
		List<InterfaceGrant> list = interfaceGrantDao.find(hashMap);

		return list;

	}

	public int getCount(Map paraMap) {
		int count = interfaceGrantDao.count(paraMap);
		return count;
	}

	public List<InterfaceGrant> find(Map paraMap) {
		return interfaceGrantDao.find(paraMap);
	}

	public InterfaceGrant get(Integer id) {
		return interfaceGrantDao.get(id);
	}

	public void insert(InterfaceGrant itg) {
		interfaceGrantDao.insert(itg);
	}

	public void update(InterfaceGrant itg) {
		interfaceGrantDao.update(itg);
	}

	public void deleteById(Integer id) {
		interfaceGrantDao.deleteById(id);
	}

	public void delete(Integer[] ids) {

	}

	public void audit(Integer[] ids) {

	}

	public void toAudit(Integer[] ids) {

	}

	public int count(Map paraMap) {
		return 0;
	}

	public void updateViewAndUploadNum(Map map) {

	}

	public List<InterfaceGrant> findServiceName() {
		return interfaceGrantDao.findServiceName();
	}

	public List<InterfaceGrant> findAll(Map<String, Object> map) {
		return interfaceGrantDao.findAll(map);
	}

	public List<InterfaceGrant> findGrantStatus() {
		return interfaceGrantDao.findGrantStatus();
	}

}
