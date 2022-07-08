package com.tools.json2obj.common.cmd;

import lombok.Data;

@Data
public class User {
	String	jsonFile;
	String	sqlFile;
	String	type;		// or dir
	Boolean	web;
}
