/**
 * @file：JdbcBaseDaoImpl.java
 * @version: 1.0
 * @author: YeJianPing
 * @date：2015-2-12
 * @copyright: ©2015 Founder company limited copyright 
 */
package com.founder.base.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.founder.base.dao.IJdbcBaseDao;
import com.founder.base.dao.JdbcConnection;
import com.founder.base.utils.Constants;

/**
 * @description:
 * @author: YeJianPing
 * @date：2015-2-12
 */
public class JdbcBaseDaoImpl implements IJdbcBaseDao {

	/**
	 * 
	 * @description: 根据SQL获取数据集合
	 * @param: sql：sql ， objNum ：Object[]数组大小（SQL查询数据列数量）
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-2-12 下午02:00:46
	 */
	public synchronized List<Object[]> getListByObj(String sql, int objNum) {
		List<Object[]> list = new ArrayList<Object[]>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			if (conn != null) {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					Object[] objs = new Object[objNum];
					for (int i = 1; i <= objNum; i++) {
						objs[i - 1] = rs.getObject(i); // 数组从0开始 ， ResultSet从1开始
					}
					list.add(objs);
				}
				rs.close();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != conn) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 
	 * @description:根据SQL获取数据集合（原始库）
	 * @param: sql：sql ， objNum ：Object[]数组大小
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-2 下午04:27:04
	 */
	public List<Object[]> getYSKObjListBySQL(String sql, int objNum) {
		List<Object[]> list = new ArrayList<Object[]>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnectionYSK();
			if (conn != null) {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					Object[] objs = new Object[objNum];
					for (int i = 1; i <= objNum; i++) {
						objs[i - 1] = rs.getObject(i); // 数组从0开始 ， ResultSet从1开始
					}
					list.add(objs);
				}
				rs.close();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != conn) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 
	 * @description:根据SQL获取数据集合（基础库）
	 * @param: sql：sql ， objNum ：Object[]数组大小
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-2 下午04:27:04
	 */
	public List<Object[]> getJCKObjListBySQL(String sql, int objNum) {
		List<Object[]> list = new ArrayList<Object[]>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnectionJCK();
			if (conn != null) {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					Object[] objs = new Object[objNum];
					for (int i = 1; i <= objNum; i++) {
						objs[i - 1] = rs.getObject(i); // 数组从0开始 ， ResultSet从1开始
					}
					list.add(objs);
				}
				rs.close();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != conn) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 获取页面分页数据量
	 * 
	 * @description:
	 * @param: db_type：数据库，sql：select count(*) ....
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-9 下午01:40:13
	 */
	public int getPageDataCount(String db_type, String sql) {
		int count = 0;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			if (Constants.DB_TYPE_ZYPT.equals(db_type)) {
				conn = JdbcConnection.getConnection();
			} else if (Constants.DB_TYPE_YSK.equals(db_type)) {
				conn = JdbcConnection.getConnectionYSK();
			} else if (Constants.DB_TYPE_JCK.equals(db_type)) {
				conn = JdbcConnection.getConnectionJCK();
			}
			if (conn != null) {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				rs.next();
				count = rs.getInt(1);
				rs.close();
				st.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != conn) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	/**
	 * 页面分页获取数据
	 * 
	 * @description:
	 * @param: db_type：数据库;sql：sql；objNum:查询的字段数；beginIndex：开始下标；endIndex：结束下标
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-9 下午01:46:04
	 */
	public List<Object[]> getPageDataListByIndex(String db_type, String sql,
			int objNum, int beginIndex, int endIndex) {
		List<Object[]> list = new ArrayList<Object[]>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			if (Constants.DB_TYPE_ZYPT.equals(db_type)) {
				conn = JdbcConnection.getConnection();
			} else if (Constants.DB_TYPE_YSK.equals(db_type)) {
				conn = JdbcConnection.getConnectionYSK();
			} else if (Constants.DB_TYPE_JCK.equals(db_type)) {
				conn = JdbcConnection.getConnectionJCK();
			}
			if (conn != null) {
				st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				st.setMaxRows(endIndex);// 关键代码，设置最大记录数为当前页记录的截止下标
				rs = st.executeQuery(sql);
				if (0 < beginIndex) {
					rs.absolute(beginIndex);// 关键代码，直接移动游标为当前页起始记录处
				}
				while (rs.next()) {
					Object[] objs = new Object[objNum];
					for (int i = 1; i <= objNum; i++) {
						objs[i - 1] = rs.getObject(i); // 数组从0开始 ， ResultSet从1开始
					}
					list.add(objs);
				}
				rs.close();
				st.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != conn) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
