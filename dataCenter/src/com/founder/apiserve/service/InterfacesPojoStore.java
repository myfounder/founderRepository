/**
 * @file：InterfacesPojoStore.java
 * @version: 1.0
 * @author: YeJianPing
 * @date：2015-3-27
 * @copyright: ©2015 Founder company limited copyright 
 */
package com.founder.apiserve.service;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.founder.apiserve.dao.InterfacesDao;
import com.founder.apiserve.dao.impl.InterfacesDaoImpl;
import com.founder.base.dao.IJdbcBaseDao;
import com.founder.base.dao.IWordBookDao;
import com.founder.base.dao.impl.JdbcBaseDaoImpl;
import com.founder.base.dao.impl.WordBookDaoImpl;
import com.founder.base.domain.WordBookPojo;

/**
 * @description: 接口 字典类
 * @author: YeJianPing
 * @date：2015-3-27
 */
public class InterfacesPojoStore {

	// 所有接口数据
	private Map<String, String> intfAllDataMap = new LinkedHashMap<String, String>();
	// 已启用接口数据
	private Map<String, String> intfOpenDataMap = new LinkedHashMap<String, String>();

	// 存储类型 : <type，<code，codeName>>
	private Hashtable<String, LinkedHashMap<String, String>> wordbookMap = new Hashtable<String, LinkedHashMap<String, String>>();

	private static InterfacesPojoStore instance = new InterfacesPojoStore();

	private InterfacesPojoStore() {
		init();
	}

	public static InterfacesPojoStore getInstance() {
		return instance;
	}

	public synchronized void init() {
		intfAllDataMap.clear();
		intfOpenDataMap.clear();
		wordbookMap.clear();// 所有接口种类
		// jdbcDao
		IJdbcBaseDao jdbcDao = new JdbcBaseDaoImpl();
		String sql = " select t.code,t.name,t.is_run from T_INTERFACES t order by t.code,t.id";
		List<Object[]> objList1 = jdbcDao.getListByObj(sql, 3);
		if (null != objList1 && 0 < objList1.size()) {
			for (int i = 0; i < objList1.size(); i++) {
				Object[] obj = objList1.get(i);
				String iCode = null != obj[0] ? obj[0].toString() : ""; // 接口编码
				String iName = null != obj[1] ? obj[1].toString() : ""; // 接口名称
				String is_open = null != obj[2] ? obj[2].toString() : ""; // 接口是否启用
				// 所有接口数据
				intfAllDataMap.put(iCode, iName);
				if ("1".equals(is_open.trim())) {
					intfOpenDataMap.put(iCode, iName);
				}
			}
		}
		IWordBookDao wordBookDao = new WordBookDaoImpl();
		List<WordBookPojo> list = wordBookDao.getWordBookList();
		InterfacesDao interfacesDao = new InterfacesDaoImpl();
		for (WordBookPojo pojo : list) {
			// 将字典数据置于集合中 -- 存储类型 : <type，<code，codeName>>
			if (wordbookMap.containsKey(pojo.getWb_type())) {
				wordbookMap.get(pojo.getWb_type()).put(pojo.getWb_code(),
						pojo.getWb_name());
			} else {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				map.put(pojo.getWb_code(), pojo.getWb_name());
				wordbookMap.put(pojo.getWb_type(), map);
			}
		}
	}

	/**
	 * @return intfAllDataMap : return the property intfAllDataMap.
	 */
	public Map<String, String> getIntfAllDataMap() {
		return intfAllDataMap;
	}

	/**
	 * @return intfOpenDataMap : return the property intfOpenDataMap.
	 */
	public Map<String, String> getIntfOpenDataMap() {
		return intfOpenDataMap;
	}

	/**
	 * 
	 * @description: 根据接口编码 获取 接口名称
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-4-1 下午02:37:37
	 */
	public String getIntfaceNameByCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			return intfAllDataMap.get(code);
		} else {
			return "";
		}

	}

	/**
	 * 根据类型,获取数据字典项-code和name
	 * 
	 * @param type
	 * @return
	 */
	public synchronized LinkedHashMap<String, String> getWordbookMap(String type) {
		return wordbookMap.get(type);
	}

}
