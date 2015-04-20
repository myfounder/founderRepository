package com.founder.base.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class ComUtil {
	public static final byte[] img_jpeg = { (byte) 0xFF, (byte) 0xD8 };
	public static final byte[] img_png = { (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D,
			0x0A, 0x1A, 0x0A };
	public static final byte[] img_gif = { 0x47, 0x49, 0x46, 0x38, 0x39, 0x61 };
	public static final byte[] img_bmp = { 0x42, 0x4D };

	static private String[] cSVTCONV = { "'", "\\", "%" };
	static private String[] cJSPCONV = { "\\'", "\\\\\\\\", "\\\\%" };

	static private DecimalFormat doubleFormat = new DecimalFormat();

	static {
		doubleFormat.setMaximumFractionDigits(2);
		doubleFormat.setMaximumIntegerDigits(8);
	}

	/******************************** 验证函数 *********************************/
	/**
	 * 验证必须输入
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean isNull(String value) {

		try {
			if (value == null) {
				return true;
			}
			value = value.replaceAll("[ ]", "");
			value = value.replaceAll("[　]", "");
			if (value.trim().length() == 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 长度验证
	 * 
	 * @param value
	 *            String
	 * @param len
	 *            int
	 * @return boolean
	 */
	public static boolean isOutOfLength(String value, int len) {
		try {
			if (value == null)
				return false;

			if (getLength(value.trim()) > len)
				return false;
			else
				return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 图片重命名
	 * 
	 * 
	 * @param fileName
	 */
	public static String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);

		return formatDate + random + extension;
	}

	/**
	 * 取得字串长度
	 * 
	 * @param value
	 *            String
	 * @return int
	 */
	public static int getLength(String value) {
		try {
			int ret = 0;
			if (value == null)
				return ret;
			char[] chars = value.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				String target = String.valueOf(chars[i]);
				byte[] b = target.getBytes();
				if (b.length == 2) {
					ret += 2;
				} else {
					ret++;
				}
			}
			return ret;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 数值长度验证
	 * 
	 * 
	 * @param value
	 *            String
	 * @param len
	 *            int
	 * @return boolean
	 */
	public static boolean isOutOfLengthNum(String value, int len) {
		try {
			if (value == null)
				return false;

			value = value.replaceAll("[,]", "");
			value = value.replaceAll("[.]", "");
			value = value.replaceAll("[-]", "");

			if (getLength(value.trim()) > len)
				return false;
			else
				return true;

		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 数值验证
	 * 
	 * 
	 * @param flg
	 *            int 1：非负数；2：所有
	 * 
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean isNum(int flg, String value) {
		try {
			if (value == null || getLength(value.trim()) == 0)
				return false;

			value = value.replaceAll("[,]", "");

			if (flg == 2)
				value = value.replaceAll("[-]", "");

			byte[] b = value.trim().getBytes();

			for (int i = 0; i < b.length; i++) {
				if (ascallnum(b[i]) == false)
					return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 实数验证（带小数位数验证）
	 * 
	 * 
	 * @param flg
	 *            int
	 * @param value
	 *            String
	 * @param p_len
	 *            int
	 * @return boolean
	 */
	public static boolean isPer(int flg, String value, int p_len) {
		try {
			if (value == null)
				return false;

			if (value.indexOf(".") != -1) {

				String[] value2 = value.split("\\.");

				if (p_len > 0) {
					if (value2.length != 2 || (!isNum(flg, value2[0].trim()))
							|| (!isNum(1, value2[1].trim()))
							|| getLength(value2[1].trim()) > p_len) {
						return false;
					}
				} else {
					if (value2.length != 2 || (!isNum(flg, value2[0].trim()))
							|| (!isNum(1, value2[1].trim()))) {
						return false;
					}
				}
			} else {
				if (!isNum(flg, value)) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 日期验证
	 * 
	 * @param year
	 *            String
	 * @param month
	 *            String
	 * @param day
	 *            String
	 * @return boolean
	 */
	public static boolean isDate(String year, String month, String day) {
		try {
			if (year == null || month == null || day == null)
				return false;

			if (Integer.parseInt(year) == 0)
				return false;

			GregorianCalendar cal = new GregorianCalendar();
			cal.setLenient(false);
			cal.clear();
			cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1,
					Integer.parseInt(day));
			Date date = cal.getTime();

			return true;

		} catch (IllegalArgumentException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断输入的字符串是否是日期格式
	 * 
	 * 
	 * 若字符串为空，返回false 通过DateFormat.parse()是否产生异常来判断是否是日期字符串
	 * 
	 * 
	 * @param dataString
	 * @return
	 */
	public static boolean isDate(String dataString) {
		if (ComUtil.isNull(dataString)) {
			return false;
		}

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(dataString);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * 时间验证
	 * 
	 * @param hour
	 *            String
	 * @param minute
	 *            String
	 * @return boolean
	 */
	public static boolean isTime(String hour, String minute) {
		try {
			if (hour == null || minute == null)
				return false;

			// 验证小时数

			if (isNum(1, hour.trim()) == false
					|| Integer.parseInt(hour.trim()) < 0
					|| Integer.parseInt(hour.trim()) > 23) {
				return false;
			}
			// 验证分钟数

			if (isNum(1, minute.trim()) == false
					|| Integer.parseInt(minute.trim()) < 0
					|| Integer.parseInt(minute.trim()) > 59) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 日期月的判断
	 * 
	 * @param month
	 *            String
	 * @return boolean
	 */
	public static boolean isMonth(String month) {
		try {
			if (month == null)
				return false;

			if (isNum(1, month.trim()) == false
					|| Integer.parseInt(month.trim()) < 0
					|| Integer.parseInt(month.trim()) > 12) {
				return false;
			}

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 日期日的判断
	 * 
	 * @param day
	 *            String
	 * @return boolean
	 */
	public static boolean isDay(String day) {
		try {
			if (day == null)
				return false;

			if (isNum(1, day.trim()) == false
					|| Integer.parseInt(day.trim()) < 0
					|| Integer.parseInt(day.trim()) > 31) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 日期年月的判断
	 * 
	 * 
	 * @param year
	 *            String
	 * @param month
	 *            String
	 * @return boolean
	 */
	public static boolean isYearMonth(String year, String month) {
		try {

			if (year == null || month == null)
				return false;

			if (!isDate(year, month, "1")) {
				return false;
			}

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取输入的月份中的天数
	 * 
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int showDaysOfMonth(int year, int month) {
		int days[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (2 == month && 0 == (year % 4)
				&& (0 != (year % 100) || 0 == (year % 400))) {
			days[1] = 29;
		}
		return days[month - 1];
	}

	/**
	 * 
	 * @description: 手机号验证
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2013-2-20 上午11:32:26
	 */
	public static boolean isPhone(String phoneNum) {
		try {
			if (null != phoneNum) {
				Pattern p = Pattern.compile("1[358][0-9]{9}");
				Matcher m = p.matcher(phoneNum);
				return m.matches();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 电话号码验证
	 * 
	 * @param value
	 *            String
	 * @param len
	 *            int
	 * @return boolean
	 */
	public static boolean isTelNo(String value, int len) {
		try {
			if (value == null)
				return false;

			value = value.trim();

			if (isNum(1, value) == false || getLength(value) != len) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 邮政编码验证
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean isZip(String value) {
		try {
			if (value == null)
				return false;

			value = value.trim();

			if (value.length() <= 0)
				return false;

			if (getLength(value) != 6 || isNum(1, value) == false) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 电子邮件地址验证
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean isEmailAddress(String value) {
		try {
			if (value == null)
				return false;
			value = value.trim();
			if (!value
					.matches("^[_0-9a-z-][_.0-9a-z/-]+@([0-9a-z][_0-9a-z.-]+.)+[a-z]{2,3}$")) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断中文字符
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean isChineseChar(String value) {

		try {
			if (value == null)
				return false;
			char[] chars = value.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				String target = String.valueOf(chars[i]);
				byte[] b = target.getBytes();
				if (b.length == 2) {
				} else {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 过去日期判断
	 * 
	 * @param year
	 *            String
	 * @param month
	 *            String
	 * @param day
	 *            String
	 * @return boolean
	 */
	public static boolean isPastDate(String year, String month, String day) {
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setLenient(false);
			SimpleDateFormat fmDate = new SimpleDateFormat("yyyyMMdd");
			String today = fmDate.format(cal.getTime());
			cal.clear();
			cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1,
					Integer.parseInt(day));
			String inputday = fmDate.format(cal.getTime());

			if (inputday.compareTo(today) < 0) {
				return true;
			} else {
				return false;
			}
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * 判断是否包含（",;,'）等字符
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean hasInvalidCharactor(String value) {
		String key = value;

		try {
			if (key.indexOf(";") != -1 || key.indexOf("'") != -1
					|| key.indexOf("\"") != -1) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * 英数判断
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean isAlphabetOrNumeric(String value) {
		try {
			byte[] b = value.getBytes();

			for (int i = 0; i < b.length; i++) {
				if (ascnum(b[i]) == false)
					return false;
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 数字、字母、特殊字符验证
	 * 
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean isAlphabetOrNumericOrSymbol(String value) {
		try {
			byte[] b = value.getBytes();

			for (int i = 0; i < b.length; i++) {
				if (ascnumcode(b[i]) == false)
					return false;
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/******************************** 日期函数 *********************************/
	/**
	 * 修改月份
	 */
	@SuppressWarnings("deprecation")
	public static String changeDateAsMonth(String date, int imonth) {
		GregorianCalendar calendar = new GregorianCalendar();
		if (date.length() == 8) {
			calendar.set(Integer.parseInt(date.substring(0, 4)), Integer
					.parseInt(date.substring(4, 6)) - 1, Integer.parseInt(date
					.substring(6, 8)));
		} else if (date.length() == 10) {
			calendar.set(Integer.parseInt(date.substring(0, 4)), Integer
					.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date
					.substring(8, 10)));
		}
		calendar.add(GregorianCalendar.MONTH, imonth);
		String sDate[] = calendar.getTime().toLocaleString().split(" ");
		return sDate[0];
	}

	/**
	 * 日期比较
	 * 
	 */
	public static int compareDate(String aDate, String bDate) {

		String[] strDate;
		GregorianCalendar aCal = new GregorianCalendar();
		GregorianCalendar bCal = new GregorianCalendar();
		if (aDate.indexOf("/") != -1) {
			strDate = aDate.split("/");
			aCal.set(Integer.parseInt(strDate[0].trim()), Integer
					.parseInt(strDate[1].trim()) - 1, Integer
					.parseInt(strDate[2].trim()));
		} else if (aDate.indexOf("-") != -1) {
			strDate = aDate.split("-");
			aCal.set(Integer.parseInt(strDate[0].trim()), Integer
					.parseInt(strDate[1].trim()) - 1, Integer
					.parseInt(strDate[2].trim()));
		} else if (aDate.length() == 8) {
			aCal.set(Integer.parseInt(aDate.substring(0, 4)), Integer
					.parseInt(aDate.substring(4, 6)) - 1, Integer
					.parseInt(aDate.substring(6, 8)));
		} else if (aDate.length() == 10) {
			aCal.set(Integer.parseInt(aDate.substring(0, 4)), Integer
					.parseInt(aDate.substring(5, 7)) - 1, Integer
					.parseInt(aDate.substring(8, 10)));
		}

		if (bDate.indexOf("/") != -1) {
			strDate = bDate.split("/");
			bCal.set(Integer.parseInt(strDate[0].trim()), Integer
					.parseInt(strDate[1].trim()) - 1, Integer
					.parseInt(strDate[2].trim()));
		} else if (bDate.indexOf("-") != -1) {
			strDate = bDate.split("-");
			bCal.set(Integer.parseInt(strDate[0].trim()), Integer
					.parseInt(strDate[1].trim()) - 1, Integer
					.parseInt(strDate[2].trim()));
		} else if (bDate.length() == 8) {
			bCal.set(Integer.parseInt(bDate.substring(0, 4)), Integer
					.parseInt(bDate.substring(4, 6)) - 1, Integer
					.parseInt(bDate.substring(6, 8)));
		} else if (bDate.length() == 10) {
			bCal.set(Integer.parseInt(bDate.substring(0, 4)), Integer
					.parseInt(bDate.substring(5, 7)) - 1, Integer
					.parseInt(bDate.substring(8, 10)));
		}

		return aCal.compareTo(bCal);
	}

	/**
	 * 闰年判断
	 * 
	 * @param year
	 *            String
	 * @return boolean
	 */
	public static boolean isLeapYear(String year) {
		int intyear;

		try {

			intyear = Integer.parseInt(year);
			if (intyear % 400 == 0 || (intyear % 4 == 0 && intyear % 100 != 0)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 计算年龄
	 * 
	 * @param birthday
	 *            String 出生日期
	 * @return String
	 */
	public static String getAge(String birthday) {
		SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
		String strNowDate = fm.format(new Date());
		String age = new String();
		age = String.valueOf((Integer.parseInt(strNowDate) - Integer
				.parseInt(birthday)) / 10000);
		return age;
	}

	/**
	 * 取当前日期
	 * 
	 * 
	 * @param format
	 *            String 日期格式
	 * @throws Exception
	 * @return String
	 */
	public static String getDate(String format) throws Exception {
		SimpleDateFormat fm = new SimpleDateFormat(format);
		return fm.format(new Date());
	}

	/**
	 * 取得指定年月的天数
	 * 
	 * 
	 * @param year
	 *            String
	 * @param month
	 *            String
	 * @return int
	 */
	public static int getMonthDay(String year, String month) {
		int iRet = 0;
		switch (Integer.parseInt(month)) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			iRet = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			iRet = 30;
			break;
		case 2:
			iRet = isLeapYear(year) == true ? 28 : 29;
			break;

		}
		return iRet;
	}

	/**
	 * 取得指定日期的星期值
	 * 
	 * 
	 * @param year
	 *            String
	 * @param month
	 *            String
	 * @param day
	 *            String
	 * @return String
	 */
	public static String weekDay(String year, String month, String day) {
		String strWeekday = "";
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setLenient(false);
			cal.clear();
			cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1,
					Integer.parseInt(day));
			strWeekday = String.valueOf(cal.get(Calendar.DAY_OF_WEEK) - 1);
		} catch (IllegalArgumentException e) {
			strWeekday = "";
		}
		return strWeekday;
	}

	/**
	 * 抓取html中的图片地址
	 * 
	 * @param description
	 *            给定的html字符串
	 * 
	 * 
	 * @return 图片地址的集合
	 * 
	 * 
	 * @author 王学成
	 */
	public static List<String> getImagesURL(String description) {

		List<String> list = new ArrayList<String>();
		// img 的正则表达式:匹配<img>标签
		String imgPattern = "<\\s*img\\s+([^>]+)\\s*>";
		Pattern pattern1 = Pattern
				.compile(imgPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern1.matcher(description);

		// img src元素的正则表达式：匹配img标签内的src属性

		String srcPattern = "\\s*src\\s*=\\s*\"([^\"]+)\\s*\"";
		Pattern pattern2 = Pattern
				.compile(srcPattern, Pattern.CASE_INSENSITIVE);
		while (matcher.find()) {

			// group()返回符合表达式的内容
			Matcher matcher2 = pattern2.matcher(matcher.group());
			// 一定要find(),这是实际的匹配动作

			if (matcher2.find()) {
				String src = matcher2.group();
				list.add(src.substring(6, src.length() - 1));
			}
		}
		return list;
	}

	/***************************** 字符串格式化函数 *****************************/
	/**
	 * 格式化数值为逗号分隔
	 * 
	 * @param strValue
	 *            String
	 * @return String
	 */
	public static String setComma(String strValue) {
		String sCommaSet = "";
		try {
			String sign;
			StringBuffer sb;
			;
			if (Long.parseLong(strValue) < 0) {
				sign = "-";
				sb = new StringBuffer(Long.toString(Long.parseLong(strValue)
						* -1));
			} else {
				sign = "";
				sb = new StringBuffer(strValue);
			}
			int iLength = sb.length();
			int iCommaCount = (int) ((iLength - 1) / 3); // 逗号总数
			int iCommaPosition = (int) (iLength % 3); // 逗号位置
			if (iCommaPosition == 0) {
				iCommaPosition = 0;
				iCommaPosition = 3;
			}
			if (iCommaCount == 0) {
			}
			for (int i = 0; i < iCommaCount; i++) {
				sb.insert(iCommaPosition + 3 * i + i, ",");
			}
			sCommaSet = sign + sb.toString();

		} catch (NumberFormatException e) {
			sCommaSet = "";
		}
		return sCommaSet;
	}

	/**
	 * 通用防SQL注入函数java版
	 * 
	 * 
	 * @param str
	 * @return
	 * @author 章勇
	 */
	public static boolean sql_inj(String str) {
		if (!ComUtil.isNull(str)) {
			str = str.toLowerCase();
			// String inj_str =
			// "' and exec insert select delete update count * % chr mid master truncate char declare ; or - + , ? \"";
			String inj_str = "' % ? \"";
			String inj_stra[] = inj_str.split(" ");
			for (int i = 0; i < inj_stra.length; i++) {
				if (str.indexOf(inj_stra[i]) >= 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 补零至指定长度
	 * 
	 * 
	 * @param strvalue
	 *            String 原字串
	 * 
	 * 
	 * @param length
	 *            int 字串总长
	 * @return String
	 */
	public static String fillZero(String strvalue, int length) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < length - strvalue.length(); i++) {
			s.append("0");
		}
		s.append(strvalue);
		return s.toString();
	}

	/**
	 * 设置时间分隔符
	 * 
	 * 
	 * @param value
	 *            String
	 * @return String
	 */
	public static String setTimeColon(String value) {
		if (value == null) {
			return "";
		} else if (value.length() == 4) {
			return value.substring(0, 2) + ":" + value.substring(2, 4);
		} else if (value.length() == 6) {
			return value.substring(0, 2) + ":" + value.substring(2, 4) + ":"
					+ value.substring(4, 6);
		} else {
			return "";
		}
	}

	/********************************* 私有函数 ********************************/
	/**
	 * 数字字符验证
	 * 
	 * @param byt
	 *            byte
	 * @return boolean
	 */
	private static boolean ascallnum(byte byt) {
		try {
			if ((byt >= 48 && byt <= 57) == false)
				return false;
			else
				return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 数字、字母验证
	 * 
	 * 
	 * @param byt
	 *            byte
	 * @return boolean
	 */
	private static boolean ascnum(byte byt) {
		// Asc: 48 - 57 = '0' -'9'
		// Asc: 65-90 = 'A' - 'Z'
		// Asc: 97-122 = 'a' - 'z'
		if ((byt >= 48 && byt <= 57) == false
				&& (byt >= 65 && byt <= 90) == false
				&& (byt >= 97 && byt <= 122) == false)
			return false;
		else
			return true;
	}

	/**
	 * 数字、字母、特殊字符验证
	 * 
	 * 
	 * @param byt
	 *            byte
	 * @return boolean
	 */
	private static boolean ascnumcode(byte byt) {
		// Asc: 33 - 126 but not 34(") and 39(')
		if ((byt != 34) == false || (byt != 39) == false)
			return false;
		else if ((byt >= 33 && byt <= 126) == false)
			return false;
		else
			return true;
	}

	// 取汉字子串。非汉字字符跳过
	public static String subChineseContents(String sContents, int strLen) {
		StringBuffer retSubContents = new StringBuffer();
		int iLen = 0;
		// if(sContents.length() <= strLen) {
		// return sContents;
		// } else {
		for (int i = 0; i < sContents.length(); i++) {
			String sTemp = sContents.substring(i, i + 1);
			if (sTemp.matches("[\\u4E00-\\u9FA5]+")) {
				retSubContents.append(sTemp);
				iLen++;
			}
			if (iLen >= strLen) {
				break;
			}
		}
		// }

		return retSubContents.toString();
	}

	// 取子串时防止因长度问题取出乱码

	public static String subContents(String sContents, int strLen) {
		if (isNull(sContents)) {
			sContents = "";
		} else {
			String sOriginContents = new String();
			sOriginContents = sContents;
			if (sContents.length() > strLen) {
				sContents = sContents.substring(0, strLen);

				if (sContents.lastIndexOf("&") != -1) {
					String sTemp = sContents.substring(sContents
							.lastIndexOf("&"), sContents.length());
					if (sTemp.length() < 7) {
						int i = 1;
						while (sContents.lastIndexOf("&") != -1
								&& sTemp.length() < 7) {
							sContents = sOriginContents.substring(0, strLen
									+ i++);
							sTemp = sContents.substring(sContents
									.lastIndexOf("&"), sContents.length());
						}
					}
				}
				sContents = sContents + "...";
			}
		}
		return sContents;
	}

	/**
	 * 删除input字符串中的html格式
	 * 
	 * @param input
	 * @param length
	 * @return
	 */
	public static String splitAndFilterString(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
				"<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "......";
		}
		return str;
	}

	/**
	 * 把文本编码为Html代码,由于函数返回
	 */
	public static String htmlEncode(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		int j = s.length();
		for (int i = 0; i < j; i++) {
			char c = s.charAt(i);
			switch (c) {
			case 60:
				stringbuffer.append("&lt;");
				break;
			case 62:
				stringbuffer.append("&gt;");
				break;
			case 38:
				stringbuffer.append("&amp;");
				break;
			case 34:
				stringbuffer.append("&quot;");
				break;
			case 169:
				stringbuffer.append("&copy;");
				break;
			case 174:
				stringbuffer.append("&reg;");
				break;
			case 165:
				stringbuffer.append("&yen;");
				break;
			case 8364:
				stringbuffer.append("&euro;");
				break;
			case 8482:
				stringbuffer.append("&#153;");
				break;
			case 13:
				if (i < j - 1 && s.charAt(i + 1) == 10) {
					stringbuffer.append("<br>");
					i++;
				}
				break;
			case 32:
				if (i < j - 1 && s.charAt(i + 1) == ' ') {
					stringbuffer.append(" &nbsp;");
					i++;
					break;
				}
			default:
				stringbuffer.append(c);
				break;
			}
		}
		return new String(stringbuffer.toString());
	}

	/**
	 * 当输入的内容含有"'"时将它转换为"''"即可查询 当输入的内容含有"/"时将它转换为"//"即可查询
	 * 当输入的内容含有"%"时将它转换为"/%"即可查询 当输入的内容含有"_"时将它转换为"/_"即可查询
	 */
	public static String escapeString(String sValue) {
		if (ComUtil.isNull(sValue)) {
			return sValue;
		}
		if (sValue.contains("'") || sValue.contains("/")
				|| sValue.contains("%") || sValue.contains("_")) {
			StringBuffer str = new StringBuffer();
			for (int i = 0; i < sValue.length(); i++) {
				char ca = sValue.charAt(i);
				switch (ca) {
				case '\'':
					str.append("''");
					break;
				case '/':
					str.append("//");
					break;
				case '%':
					str.append("/%");
					break;
				case '_':
					str.append("/_");
					break;
				default:
					str.append(ca);
					break;
				}
			}
			return str.toString();
		}
		return sValue;
	}

	/**
	 * 判断某字符串是否含有特殊字符
	 * 
	 * @param sValue
	 * @return
	 */
	public static boolean isSpecialString(String sValue) {
		if (sValue.contains("'") || sValue.contains("/")
				|| sValue.contains("%") || sValue.contains("_")) {
			return true;
		}
		return false;
	}

	/**
	 * 章勇 对图片进行缩略
	 * 
	 * 
	 * @throws Exception
	 */
	public static void JpgTset() throws Exception {
		// File _file = new File("F://100DICAM//0926//DSCI0277.JPG"); //读入文件
		File _file = new File("F://100DICAM//0926//cs.gif");
		Image src = javax.imageio.ImageIO.read(_file); // 构造Image对象
		int wideth = src.getWidth(null); // 得到源图宽

		int height = src.getHeight(null); // 得到源图长

		BufferedImage tag = new BufferedImage(wideth / 2, height / 2,
				BufferedImage.TYPE_INT_RGB);
		tag.getGraphics().drawImage(src, 0, 0, wideth / 2, height / 2, null); // 绘制缩小后的图

		// FileOutputStream out=new
		// FileOutputStream("F://100DICAM//0926//DSCI0277tttt.JPG"); //输出到文件流
		FileOutputStream out = new FileOutputStream(
				"F://100DICAM//0926//cstttt.gif");
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(tag); // 近JPEG编码
		// System.out.print(width+"*"+height);
		out.close();
	}

	/**
	 * 根据指定的DecimalFormat，对传入的Double数据格式化
	 * 
	 * 
	 * 若格式化类型df为空，则指定默认类型为：最大整数位8，最大小数位2
	 * 
	 * @param df
	 * @param value
	 * @return
	 */
	public static Double Doubleformat(DecimalFormat df, Double value) {
		if (value == null || value.compareTo(0.0) == 0) {
			return 0.0;
		}
		if (df == null) {
			df = doubleFormat;
		}
		String dfValue = df.format(value);
		dfValue = dfValue.replaceAll("[ ]", "");
		dfValue = dfValue.replaceAll("[,]", "");
		return Double.valueOf(dfValue);
	}

	public static boolean isImage(Blob blob) throws Exception {
		InputStream in = null;
		byte[] bytes = new byte[8];
		try {
			in = blob.getBinaryStream();

			in.read(bytes);
			return isImage(bytes);
		} catch (Exception ex) {
			throw new Exception(ex.toString());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
					throw new Exception(ex.toString());
				}
			}
			bytes = null;
		}
	}

	/**
	 * 判断文件头内容，确定是否属于图片文件格式
	 * 
	 * @param byt
	 * @return
	 */
	private static boolean isImage(byte[] byt) {
		if (byt == null || byt.length < 8) {
			return false;
		}

		if (byt[0] == img_jpeg[0] && byt[1] == img_jpeg[1]) {
			return true;
		} else if (byt[0] == img_png[0] && byt[1] == img_png[1]
				&& byt[2] == img_png[2] && byt[3] == img_png[3]
				&& byt[4] == img_png[4] && byt[5] == img_png[5]
				&& byt[6] == img_png[6] && byt[7] == img_png[7]) {
			return true;
		} else if (byt[0] == img_gif[0] && byt[1] == img_gif[1]
				&& byt[2] == img_gif[2] && byt[3] == img_gif[3]
				&& (byt[4] == img_gif[4] || byt[4] == 0x37)
				&& byt[5] == img_gif[5]) {
			return true;
		} else if (byt[0] == img_bmp[0] && byt[1] == img_bmp[1]) {
			return true;
		}
		return false;
	}

	/**
	 * @description 判断String是否为空
	 * @author YeJianPing
	 * @date 2012-05-04
	 * @param str
	 * @return
	 */
	public static boolean isStringNotEmpty(String str) {
		if (null != str && !"".equals(str) && !"null".equals(str.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @description: 判断Object是否为空
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2013-7-26 下午03:41:32
	 */
	public static boolean isObjectNotEmpty(Object obj) {
		if (null != obj && !"".equals(obj.toString())
				&& !"null".equals(obj.toString().toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @description: 字符串转为日期类型
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2013-1-4 下午03:24:01
	 */
	public static Date stringToDate(String str) throws Exception {
		Date result = new Date();
		try {
			result = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @description: 字符串转为日期类型
	 * @param: pattern:日期类型，如：yyyy-MM-dd,yyyy年MM月dd日
	 * @return:
	 * @author: YeJianPing
	 * @date：2013-1-4 下午03:24:01
	 */
	public static Date stringToDate(String pattern, String str)
			throws Exception {
		Date result = new Date();
		try {
			result = new SimpleDateFormat(pattern).parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @description: 将日期字符串转换为带星期的日期字符串
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2013-1-4 下午03:37:04
	 */
	public static String stringToDateStringWithWeek(String str) {
		String strReturn = "";
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		try {
			Date date = bartDateFormat.parse(str);
			SimpleDateFormat bartDateFormat2 = new SimpleDateFormat(
					"yyyy年MM月dd日EEEE HH:mm");
			strReturn = bartDateFormat2.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strReturn;
	}

	/**
	 * 
	 * @description: 日期类型转为字符串
	 * @param: pattern:日期类型，如：yyyy-MM-dd,yyyy年MM月dd日
	 * @return:
	 * @author: YeJianPing
	 * @date：2013-1-4 下午03:25:25
	 */
	public static String dateToString(String pattern, Date date) {
		String result = "";
		try {
			result = new SimpleDateFormat(pattern).format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
