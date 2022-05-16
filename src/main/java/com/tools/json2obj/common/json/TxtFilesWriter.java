package com.tools.json2obj.common.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tools.json2obj.common.BaseException;

/**
 * 文件名 ： TxtFilesWriter.java
 * 包 名 ： org.danyuan.utils.files
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 作 者 ： Tenghui.Wang
 * 时 间 ： 2016年4月13日 下午11:53:47
 * 版 本 ： V1.0
 */
public class TxtFilesWriter {
	
	private static final Logger logger = LoggerFactory.getLogger(TxtFilesWriter.class);

	/**
	 * 拼接写入
	 * 方法名： appendWriteToFile
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param data
	 * 参 数： @param filepath
	 * 返 回： void
	 * 作 者 ： Tenghui.Wang
	 * @throws
	 */
	public static void appendWriteToFile(String data, String filepath) {
		FileWriter fileWritter = null;
		try {
			String path = filepath.substring(0, filepath.lastIndexOf("\\") > 0 ? filepath.lastIndexOf("\\") : filepath.lastIndexOf("/") > 0 ? filepath.lastIndexOf("/") : filepath.length());
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(filepath);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				if (!file.createNewFile()) {
					throw new BaseException(-1, "創建文件錯誤");
				}
			}
			// true = append file
			fileWritter = new FileWriter(filepath, true);
			fileWritter.write(data + "\r\n");
			fileWritter.flush();
			fileWritter.close();
		} catch (IOException e) {
			logger.error("读文件时出错:{}", e.getMessage());
			throw new BaseException(-1, "創建文件錯誤");
		} finally {
			if (fileWritter != null) {
				try {
					fileWritter.close();
				} catch (IOException e) {
					logger.error("关闭出错:{}", e.getMessage());
				}
			}
		}
	}

	/**
	 * 重新写入文件
	 * 方法名： writeToFile
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param data
	 * 参 数： @param filepath
	 * 返 回： void
	 * 作 者 ： Tenghui.Wang
	 * @throws
	 */
	public static void writeToFile(String data, String filepath) {
		FileWriter fileWritter = null;
		try {
			String path = filepath.substring(0, filepath.lastIndexOf("\\") > 0 ? filepath.lastIndexOf("\\") : filepath.lastIndexOf("/") > 0 ? filepath.lastIndexOf("/") : filepath.length());
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(filepath);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				if (!file.createNewFile()) {
					throw new BaseException(-1, "創建文件錯誤");
				}
			}
			// true = append file
			fileWritter = new FileWriter(filepath);
			fileWritter.write(data + "\r\n");
			fileWritter.flush();
			fileWritter.close();
		} catch (IOException e) {
			logger.error("读文件时出错:{}", e.getMessage());
			throw new BaseException(-1, "創建文件錯誤");
		} finally {
			if (fileWritter != null) {
				try {
					fileWritter.close();
				} catch (IOException e) {
					logger.error("关闭出错:{}", e.getMessage());
				}
			}
		}
	}
}
