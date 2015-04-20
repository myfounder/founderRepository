package com.founder.apiserve.dao.impl;

import org.springframework.stereotype.Repository;

import com.founder.apiserve.dao.InterfaceServiceLogDao;
import com.founder.apiserve.domain.InterfaceServiceLog;
import com.founder.base.dao.impl.BaseDaoImpl;

@Repository
public class InterfaceServiceLogDaoImpl extends
		BaseDaoImpl<InterfaceServiceLog> implements InterfaceServiceLogDao {

	public InterfaceServiceLogDaoImpl() {
		super.setNs("com.apiserve.domain.InterfaceServiceLog");
	}

}
