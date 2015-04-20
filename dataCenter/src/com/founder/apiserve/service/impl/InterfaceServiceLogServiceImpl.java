package com.founder.apiserve.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.apiserve.dao.InterfaceServiceLogDao;
import com.founder.apiserve.domain.InterfaceServiceLog;
import com.founder.apiserve.service.InterfaceServiceLogService;
import com.founder.base.page.Page;
import com.founder.base.utils.DateUtil;

@Service
public class InterfaceServiceLogServiceImpl implements
		InterfaceServiceLogService {

	@Autowired
	private InterfaceServiceLogDao interfaceServiceLogDao;

	public List<InterfaceServiceLog> findPage(Page page, Map paraMap)
			throws ParseException {// 分页,带条件查询
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String name = (String) paraMap.get("name");// 接口名称
		String startTime = (String) paraMap.get("startTime");// 开始时间
		String endTime = (String) paraMap.get("endTime");// 结束时间
		String pageNoS = (String) paraMap.get("pageNo");// 当前页码
		int pageNo;
		if (StringUtils.isNotBlank(pageNoS)) {
			pageNo = Integer.parseInt(pageNoS);
		} else {
			pageNo = page.getPageNo();
		}
		hashMap.put("name", name);
		hashMap.put("startTime", DateUtil.stringToDate(startTime));
		hashMap.put("endTime", DateUtil.stringToDate(endTime));
		page.setTotalRecord(getCount(hashMap));
		hashMap.put("startRow", (pageNo - 1) * page.getPageSize());
		hashMap.put("endRow", pageNo * page.getPageSize() + 1);
		List<InterfaceServiceLog> list = interfaceServiceLogDao.find(hashMap);
		return list;
	}

	/**
	 * 
	 * @description:根据条件查询总记录数
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-17 下午05:08:47
	 */
	public int getCount(HashMap<String, Object> hashMap) {
		int count = interfaceServiceLogDao.count(hashMap);
		return count;
	}

	public List<InterfaceServiceLog> findPage(com.orsonpdf.Page page,
			Map paraMap) throws ParseException {
		return null;
	}

}
