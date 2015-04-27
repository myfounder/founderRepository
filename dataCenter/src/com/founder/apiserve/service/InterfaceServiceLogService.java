package com.founder.apiserve.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.founder.apiserve.domain.InterfaceServiceLog;
import com.founder.base.page.Page;

public interface InterfaceServiceLogService {
	public List<InterfaceServiceLog> findPage(Page page, Map paraMap)
			throws ParseException;
}
