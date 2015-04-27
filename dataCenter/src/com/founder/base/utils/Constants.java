/**
 * @file：Constants.java
 * @version: 1.0
 * @author: YeJianPing
 * @date：2015-2-11
 * @copyright: ©2015 Founder company limited copyright 
 */
package com.founder.base.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: 静态常量类
 * @author: YeJianPing
 * @date：2015-2-11
 */
public class Constants {

	// 数据字典类别
	public static final String WORDBOOK_TYPE_GENDER = "GENDER"; // 性别
	public static final String WORDBOOK_TYPE_ORGAN = "ORGAN"; // 禹会区委办局

	public static final String WORDBOOK_TYPE_DATAFORMAT = "DATAFORMAT"; // 资源节点--数据格式
	public static final String WORDBOOK_TYPE_RSNODETYPE = "RSNODETYPE"; // 资源节点--类别

	public static final String WORDBOOK_TYPE_RECORDTYPE = "RECORDTYPE";// 文档管理--文件种类
	public static final String WORDBOOK_TYPE_IMPDEGREE = "IMPDEGREE";// 文档管理--重要程度
	public static final String WORDBOOK_TYPE_URGENCYDEGREE = "URGENCYDEGREE";// 文档管理--紧急程度

	public static final String WORDBOOK_TYPE_SERVICE_TYPE = "SERVICE_TYPE"; // 接口服务--接口种类

	// -- 原始库、基础库、专题库、主题库 相关信息 --
	public static final String YSK_NUM_TABLE = "YSK_NUM_TABLE"; // 原始库表数量
	public static final String YSK_NUM_RECORD = "YSK_NUM_RECORD"; // 原始库记录数量
	public static final String YSK_NUM_UP_AVG_DAY = "YSK_NUM_UP_AVG_DAY"; // 原始库平均每天增长数量
	public static final String YSK_NUM_LATEST = "YSK_NUM_LATEST"; // 原始库上一次记录数量
	public static final String YSK_NUM_UP_LATEST_WEEK = "YSK_NUM_UP_LATEST_WEEK"; // 原始库上一周增长记录数量
	public static final String JCK_NUM_TABLE = "JCK_NUM_TABLE"; // 基础库表数量
	public static final String JCK_NUM_RECORD = "JCK_NUM_RECORD"; // 基础库记录数量
	public static final String JCK_NUM_UP_AVG_DAY = "JCK_NUM_UP_AVG_DAY"; // 基础库平均每天增长数量
	public static final String JCK_NUM_LATEST = "JCK_NUM_LATEST"; // 基础库上一次记录数量
	public static final String JCK_NUM_UP_LATEST_WEEK = "JCK_NUM_UP_LATEST_WEEK"; // 基础库上一周增长记录数量
	public static final String SPEC_NUM_TABLE = "SPEC_NUM_TABLE"; // 专题库表数量
	public static final String SPEC_NUM_RECORD = "SPEC_NUM_RECORD"; // 专题库记录数量
	public static final String SPEC_NUM_UP_AVG_DAY = "SPEC_NUM_UP_AVG_DAY"; // 专题库平均每天增长数量
	public static final String SPEC_NUM_LATEST = "SPEC_NUM_LATEST"; // 专题库上一次记录数量
	public static final String SPEC_NUM_UP_LATEST_WEEK = "SPEC_NUM_UP_LATEST_WEEK"; // 专题库上一周增长记录数量
	public static final String THEME_NUM_TABLE = "THEME_NUM_TABLE"; // 主题库表数量
	public static final String THEME_NUM_RECORD = "THEME_NUM_RECORD"; // 主题库记录数量
	public static final String THEME_NUM_UP_AVG_DAY = "THEME_NUM_UP_AVG_DAY"; // 主题库平均每天增长数量
	public static final String THEME_NUM_LATEST = "THEME_NUM_LATEST"; // 主题库上一次记录数量
	public static final String THEME_NUM_UP_LATEST_WEEK = "THEME_NUM_UP_LATEST_WEEK"; // 主题库上一周增长记录数量

	// 数据库
	public static final String DB_TYPE_ZYPT = "DB_ZYPT"; // 资源平台 数据库
	public static final String DB_TYPE_YSK = "DB_YSK"; // 原始库数据库
	public static final String DB_TYPE_JCK = "DB_JCK"; // 基础库数据库

	// 日期时间格式
	public static final String DATE_FORMAT = "yyyy-MM-dd";// DateFormat

	public static final String MESSAGE_REPETITIVE_OPERATION = "重复提交，操作无效！";
	public static final String MESSAGE_SUCC_OPERATION = "操作成功！";
	public static final String MESSAGE_FAIL_OPERATION = "操作失败，请重试或联系管理员！";

	public static final Map<String, String> intelfacesMap = new LinkedHashMap<String, String>();
	static {
		intelfacesMap.put("SearchService1", "SearchService");
		intelfacesMap.put("EnumService1", "EnumService");
		intelfacesMap.put("TestService1", "TestService");
		intelfacesMap.put("GetServiceInfo1", "GetServiceInfo");
		intelfacesMap.put("QueryDataResource1", "QueryDataResource");
		intelfacesMap.put("QueryData1", "QueryData");
	}
}
