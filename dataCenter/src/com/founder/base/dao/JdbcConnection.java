/**
 * @file：JdbcConnection.java
 * @version: 1.0
 * @author: YeJianPing
 * @date：2015-2-12
 * @copyright: ©2015 Founder company limited copyright 
 */
package com.founder.base.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

/**
 * @description:
 * @author: YeJianPing
 * @date：2015-2-12
 */
public class JdbcConnection {

	/**
	 * 
	 * @description: 获取数据源连接（资源平台用户）
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-2-12 下午01:37:34
	 */
	public static Connection getConnection() throws Exception {
		String DB_driver = ResourceBundle.getBundle("c3p0").getString(
				"jdbc.driverClassName");
		String DB_url = ResourceBundle.getBundle("c3p0").getString("jdbc.url");
		String DB_dbuser = ResourceBundle.getBundle("c3p0").getString(
				"jdbc.username");
		String DB_dbpwd = ResourceBundle.getBundle("c3p0").getString(
				"jdbc.password");

		Class.forName(DB_driver);
		Connection conn = DriverManager.getConnection(DB_url, DB_dbuser,
				DB_dbpwd);
		if (conn.isClosed()) {
			throw new Exception("Jndi Connection is closed.");
		}
		return conn;
	}

	/**
	 * 
	 * @description: 获取数据源连接（原始库）
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-2-13 下午04:42:20
	 */
	public static Connection getConnectionYSK() throws Exception {
		String DB_driver = ResourceBundle.getBundle("c3p0").getString(
				"jdbc.driverClassName");
		String DB_url = ResourceBundle.getBundle("c3p0").getString("jdbc.url");
		String DB_dbuser = ResourceBundle.getBundle("c3p0").getString(
				"ysk.username");
		String DB_dbpwd = ResourceBundle.getBundle("c3p0").getString(
				"ysk.password");

		Class.forName(DB_driver);
		Connection conn = DriverManager.getConnection(DB_url, DB_dbuser,
				DB_dbpwd);
		if (conn.isClosed()) {
			throw new Exception("Jndi Connection is closed.");
		}
		return conn;
	}

	/**
	 * 
	 * @description: 获取数据源连接（基础库）
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-2-13 下午04:42:49
	 */
	public static Connection getConnectionJCK() throws Exception {
		String DB_driver = ResourceBundle.getBundle("c3p0").getString(
				"jdbc.driverClassName");
		String DB_url = ResourceBundle.getBundle("c3p0").getString("jdbc.url");
		String DB_dbuser = ResourceBundle.getBundle("c3p0").getString(
				"jck.username");
		String DB_dbpwd = ResourceBundle.getBundle("c3p0").getString(
				"jck.password");

		Class.forName(DB_driver);
		Connection conn = DriverManager.getConnection(DB_url, DB_dbuser,
				DB_dbpwd);
		if (conn.isClosed()) {
			throw new Exception("Jndi Connection is closed.");
		}
		return conn;
	}

}
