package com.tools.json2obj.common.cmd;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tools.json2obj.common.UserConfig;
import com.tools.json2obj.service.JsonToSqlService;

@Component
public class Command implements CommandLineRunner {

	// 获取到注入的User对象
	@Resource
	private User		user;

	@Autowired
	private UserConfig	userConfig;

	@Autowired
	JsonToSqlService	jsonToSqlService;
	
	// 重写CommandLineRunner类的run方法
	@Override
	public void run(String... args) throws Exception {
		if (user.getWeb() == null) {
			user.setWeb(userConfig.getWeb() == true);
		}
		if (user.getType() == null) {
			user.setType(userConfig.getType());
		}
		if (user.getJsonFile() == null) {
			user.setJsonFile(userConfig.getJsonFile());
		}
		if (!user.getWeb()) {
			if ("dir".equals(user.getType())) {
				String filePath = userConfig.getJsonDir();
				File file = new File(filePath);
				File[] files = file.listFiles();
				for (File file2 : files) {
					if (!file2.getName().equals("list.txt") && !file2.isDirectory()) {
						String t = userConfig.getSqlDir();
						String tf = file2.getName().replaceAll(".txt|.json", ".sql");
						t = t + tf;
						jsonToSqlService.json2sql(file2.getAbsolutePath(), t);
					}
				}
				
				System.exit(0);
				
			} else {
				
				// 文件信息
				String filePath = userConfig.getJsonDir() + user.getJsonFile();
				
				StringBuilder t = new StringBuilder(userConfig.getSqlDir());
				if (user.getSqlFile() != null) {
					t.append(user.getSqlFile());
				} else {
					String tf = user.getJsonFile().replaceAll(".txt|.json", ".sql");
					t.append(tf);
				}
				
				jsonToSqlService.json2sql(filePath, t.toString());
				
				System.exit(0);
			}
		}
	}
}
