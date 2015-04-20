/**
 * @file：DateUtil.java
 * @version: 1.0
 * @author: YeJianPing
 * @date：2015-3-17
 * @copyright: ©2015 Founder company limited copyright 
 */
package com.founder.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @description:
 * @author: YeJianPing
 * @date：2015-3-17
 */
public class DateUtil {
	public static Date stringToDate(String sDate) throws ParseException {
		Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isNotBlank(sDate)) {
			date = dateFormat.parse(sDate);
		}
		return date;
	}
}
