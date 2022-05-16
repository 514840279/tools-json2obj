package com.tools.json2obj.common.cmd;

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
		if (!user.getWeb()) {
			if ("all".equals(user.getJsonFile())) {

			} else {

				// 文件信息
				String filePath = userConfig.getJsonDir() + user.getJsonFile();

				String t = userConfig.getSqlDir();
				if (user.getSqlFile() != null) {
					t = t + user.getSqlFile();
				} else {
					String tf = user.getJsonFile().replaceAll(".txt|.json", ".sql");
					t = t + tf;
				}

				jsonToSqlService.json2sql(filePath, t);

				System.exit(0);
			}
		}
	}
}
