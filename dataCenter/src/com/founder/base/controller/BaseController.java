/**
 * @file：BaseController.java
 * @version: 1.0
 * @author: YeJianPing
 * @date：2015-2-28
 * @copyright: ©2015 Founder company limited copyright 
 */
package com.founder.base.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.founder.base.utils.DataKey;

/**
 * @description:
 * @author: YeJianPing
 * @date：2015-2-28
 */
public class BaseController {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 处理时间
	 * 
	 * @param:
	 * @return:
	 * @author: YeJianPing
	 * @date：2015-3-2 上午11:28:28
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	protected int getPageC(HttpServletRequest request) {
		// 设置页面属性
		int pageCount = 10;// 默认每页的记录条数为15条;
		try {
			String pageC = request.getParameter(DataKey.PAGE_C);
			pageCount = Integer
					.parseInt(pageC == null ? pageCount + "" : pageC);
		} catch (Exception e) {
			pageCount = 15;
		}
		return pageCount;
	}

	protected int getPageC(HttpServletRequest request, int pageCountArgs) {
		// 设置页面属性
		int pageCount = pageCountArgs;// 默认每页的记录条数
		try {
			String pageC = request.getParameter(DataKey.PAGE_C);
			pageCount = Integer
					.parseInt(pageC == null ? pageCount + "" : pageC);
		} catch (Exception e) {
			pageCount = 10;
		}
		return pageCount;
	}

	protected int getPage(HttpServletRequest request) {
		int page = 0; // 页号;
		try {
			page = Integer
					.parseInt(request.getParameter(DataKey.PAGE) == null ? "0"
							: request.getParameter(DataKey.PAGE));
		} catch (Exception e) {
			page = 0;
		}
		return page;
	}

	/**
	 * 
	 * @param response
	 */
	public void setResponseNoCache(HttpServletResponse response) {
		response.setHeader("Expires", "0");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
	}

	/**
	 * 创建TOKEN YeJianPing add
	 * 
	 * @param request
	 */
	public synchronized void saveToken(HttpServletRequest request,
			HttpServletResponse response) {
		// setResponseNoCache(response); //
		HttpSession session = request.getSession();
		String token = generateToken(request);
		if (token != null) {
			session.setAttribute("TRANSACTION_TOKEN_KEY", token);
		}
	}

	public synchronized void saveToken(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String token = generateToken(request);
		if (token != null) {
			session.setAttribute("TRANSACTION_TOKEN_KEY", token);
		}

	}

	public synchronized String getToken(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String token = generateToken(request);
		if (token != null) {
			return (String) session.getAttribute("TRANSACTION_TOKEN_KEY");
		}
		return null;
	}

	/**
	 * 验证TOKEN
	 * 
	 * @param request
	 * @return
	 */
	public synchronized boolean isTokenValid(HttpServletRequest request) {
		// Retrieve the current session for this request
		HttpSession session = request.getSession(false);

		if (session == null) {
			return false;
		}

		// Retrieve the transaction token from this session, and
		// reset it if requested
		String saved = (String) session.getAttribute("TRANSACTION_TOKEN_KEY");

		if (saved == null) {
			return false;
		}

		// Retrieve the transaction token included in this request
		String token = request.getParameter("TRANSACTION_TOKEN_KEY");

		if (token == null) {
			return false;
		}
		session.removeAttribute("TRANSACTION_TOKEN_KEY");
		return saved.equals(token);
	}

	private synchronized String generateToken(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			byte id[] = session.getId().getBytes();
			byte now[] = new Long(System.currentTimeMillis()).toString()
					.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(id);
			md.update(now);
			return (toHex(md.digest()));
		} catch (IllegalStateException e) {
			return (null);
		} catch (NoSuchAlgorithmException e) {
			return (null);
		}
	}

	private static String toHex(byte[] buffer) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);

		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}

		return sb.toString();
	}

	// --/sgw/loadFillcyclelList'
	protected String loadScript(HttpServletRequest request, String message,
			String host) {
		String temp = "<script type='text/javascript'>alert('" + message
				+ "');";
		if (!("".equals(host))) {
			temp += "window.parent.location.href='" + request.getContextPath()
					+ "" + host + "';";
		}
		temp += "</script>";
		return temp;
	}

	protected String loadScripts(HttpServletRequest request, String message,
			String host) {
		String temp = "<script type='text/javascript'>alert('" + message
				+ "');";
		if (!("".equals(host))) {
			temp += "window.location.href='" + request.getContextPath() + ""
					+ host + "';";
		}
		temp += "</script>";
		return temp;
	}

	protected String loadScript(HttpServletRequest request, String message) {
		return loadScript(request, message, "");
	}
}
