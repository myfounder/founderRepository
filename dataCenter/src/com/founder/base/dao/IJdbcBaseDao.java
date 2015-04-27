/**
 * @file：IJdbcBaseDao.java
 * @version: 1.0
 * @author: YeJianPing
 * @date：2015-2-12
 * @copyright: ©2015 Founder company limited copyright 
 */
package com.founder.base.dao;

import java.util.List;

/**
 * @description:
 * @author: YeJianPing
 * @date：2015-2-12
 */
public interface IJdbcBaseDao {

	/**
	 * 
	 * @description: 根据SQL获取数据集合
	 * @param: sql：sql ， objNum ：Object[]数组大小
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-2-12 下午02:00:46
	 */
	public List<Object[]> getListByObj(String sql, int objNum);

	/**
	 * 
	 * @description:根据SQL获取数据集合（原始库）
	 * @param: sql：sql ， objNum ：Object[]数组大小
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-2 下午04:27:04
	 */
	public List<Object[]> getYSKObjListBySQL(String sql, int objNum);

	/**
	 * 
	 * @description:根据SQL获取数据集合（基础库）
	 * @param: sql：sql ， objNum ：Object[]数组大小
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-2 下午04:27:04
	 */
	public List<Object[]> getJCKObjListBySQL(String sql, int objNum);

	/**
	 * 获取页面分页数据量
	 * 
	 * @description:
	 * @param: db_type：数据库，sqlCount: count(*) ....
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-9 下午01:40:13
	 */
	public int getPageDataCount(String db_type, String sqlCount);

	/**
	 * 页面分页获取数据
	 * 
	 * @description:
	 * @param: 
	 *         db_type：数据库;sqlSelect：sql；objNum:查询的字段数；beginIndex：开始下标；endIndex：结束下标
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-9 下午01:46:04
	 */
	public List<Object[]> getPageDataListByIndex(String db_type,
			String sqlSelect, int objNum, int beginIndex, int endIndex);
}
