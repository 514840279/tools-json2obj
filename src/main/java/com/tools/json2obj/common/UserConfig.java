package com.tools.json2obj.common;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "user")
@Data
public class UserConfig {
	
	// 总开关
	private Boolean			start		= false;

	// 模式 file or dir
	private String			type		= "file";
	
	private Boolean			web			= Boolean.TRUE;
	
	// json文件目录
	private String			jsonDir;

	private String			sqlDir;

	private String			jsonFile	= "test.json";

	private String			sqlFile		= "test.sql";

	private List<String>	ignoreType;
	
}
