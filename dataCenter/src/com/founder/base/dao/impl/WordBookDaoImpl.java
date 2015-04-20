/**
 * @file：WordBookDaoImpl.java
 * @version: 1.0
 * @author: YeJianPing
 * @date：2015-2-12
 * @copyright: ©2015 Founder company limited copyright 
 */
package com.founder.base.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.founder.base.dao.IWordBookDao;
import com.founder.base.domain.WordBookPojo;

/**
 * @description:
 * @author: YeJianPing
 * @date：2015-2-12
 */
public class WordBookDaoImpl extends JdbcBaseDaoImpl implements IWordBookDao {

	/**
	 * 
	 * @description: 获取数据字典
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-2-12 下午03:23:59
	 */
	public List<WordBookPojo> getWordBookList() {
		List<WordBookPojo> list = new ArrayList<WordBookPojo>();
		String sql = "select t.wb_id,t.wb_type,t.wb_code,t.wb_name,t.wb_note,t.wb_order,t.wb_state from W_WORDBOOK t order by t.wb_type,t.wb_order";
		List<Object[]> objList = super.getListByObj(sql, 7);
		if (null != objList && 0 < objList.size()) {
			for (int i = 0; i < objList.size(); i++) {
				WordBookPojo pojo = new WordBookPojo();
				Object[] obj = objList.get(i);
				pojo.setWb_id(Integer.valueOf(null != obj[0] ? obj[0]
						.toString() : "0"));
				pojo.setWb_type(null != obj[1] ? obj[1].toString() : "");
				pojo.setWb_code(null != obj[2] ? obj[2].toString() : "");
				pojo.setWb_name(null != obj[3] ? obj[3].toString() : "");
				pojo.setWb_note(null != obj[4] ? obj[4].toString() : "");
				pojo.setWb_order(Integer.valueOf(null != obj[5] ? obj[5]
						.toString() : "0"));
				pojo.setWb_state(null != obj[6] ? obj[6].toString() : "");
				list.add(pojo);
			}
		}
		return list;
	}

}
