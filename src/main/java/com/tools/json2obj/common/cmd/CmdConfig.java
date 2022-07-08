package com.tools.json2obj.common.cmd;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CmdConfig {

	// 将方法注入到容器中，值为返回值对象
	@Bean
	public User getUser(ApplicationArguments arguments) {
		// 获取输入的参数（--n，--a）
		List<String> f = arguments.getOptionValues("f");
		List<String> t = arguments.getOptionValues("t");
		List<String> web = arguments.getOptionValues("web");
		User user = new User();
		try {
			if (web != null) {
				user.setWeb("true".equals(web.get(0)));
			}
			if (f != null) {
				user.setJsonFile(f.get(0));
			}
			if (t != null) {
				user.setType(t.get(0));
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return user;
	}
}
