/**
 * @file：IWordBookDao.java
 * @version: 1.0
 * @author: YeJianPing
 * @date：2015-2-12
 * @copyright: ©2015 Founder company limited copyright 
 */
package com.founder.base.dao;

import java.util.List;

import com.founder.base.domain.WordBookPojo;

/**
 * @description:
 * @author: YeJianPing
 * @date：2015-2-12
 */
public interface IWordBookDao {

	/**
	 * 
	 * @description: 获取数据字典
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-2-12 下午03:23:59
	 */
	List<WordBookPojo> getWordBookList();

}
