package com.founder.base.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	private static String uploadPath;
	// 初始化上传根目录
	static {

		Properties properties = new Properties();
		try {
			properties.load(FileUtil.class.getClassLoader()
					.getResourceAsStream("properties.properties"));
			uploadPath = properties.getProperty("uploadPath");
			File file = new File(uploadPath);
			if (!file.exists()) {
				file.mkdirs();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取上传文件的路径
	 * 
	 * @return文件保存的路径
	 */
	public static String getUploadPath() {

		return uploadPath;
	}

	/**
	 * 获取文件上传子目录
	 * 
	 * @return文件上传的路径
	 */
	public static String getSubUploadPath() {

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String path = dateFormat.format(date);
		File file = new File(getUploadPath() + "/" + path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}

	/**
	 * 上传文件
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @return文件上传的路径
	 */
	public static String upload(MultipartFile mpartFile, String oldFileName) {

		String filetype = oldFileName
				.substring(oldFileName.lastIndexOf('.') + 1);
		String filename = getSubUploadPath() + "/"
				+ UUID.randomUUID().toString() + "." + filetype;

		try {
			FileCopyUtils.copy(mpartFile.getInputStream(),
					new FileOutputStream(new File(getUploadPath() + "/"
							+ filename)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filename;
	}

	/**
	 * 下载文件
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static void download(String oldFileName, String newFileName,
			HttpServletResponse response) throws UnsupportedEncodingException {

		File file = new File(getUploadPath() + "/" + newFileName);
		if (file.exists()) {
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(oldFileName.getBytes("gb2312"), "iso-8859-1"));
			response.setHeader("Content-Type", "application/octet-stream");

			try {
				ServletOutputStream outputStream = response.getOutputStream();
				FileInputStream inputStream = new FileInputStream(file);
				byte[] bs = new byte[1024];
				int i = -1;
				while ((i = inputStream.read(bs)) != -1) {
					outputStream.write(bs, 0, i);
				}
				inputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			try {
				response.setCharacterEncoding("utf-8");
				response.setHeader("Content-Type", "text/html;charset=utf-8");
				response.getWriter().print("此文件已不存在或你没权限");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
