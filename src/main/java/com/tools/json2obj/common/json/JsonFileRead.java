package com.tools.json2obj.common.json;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.tools.json2obj.common.BaseException;

@Component
public class JsonFileRead {
	
	public Map<String, List<String>> read(String filePath) {
		File file = new File(filePath);
		// 判断文件存在
		if (file.exists()) {
			// 读取文件内容
			List<String> listString = TxtFilesReader.readFileByLines(filePath, "UTF-8");
			// 处理内容为map形式
			Map<String, List<String>> map = new HashMap<>();
			String key = "";
			List<String> value = new ArrayList<>();
			int keysize = 0;
			for (String string : listString) {
				if (StringUtils.isBlank(string)) {
					continue;
				} else {
					string = string.trim();
					if (string.startsWith("//")) {
						continue;
					}
					if (string.startsWith("$$")) {
						List<String> c = new ArrayList<>();
						c.add(string.replace("$$", "").trim());
						map.put("companyId", c);
					}
					if (string.startsWith("#")) {
						if (keysize > 0) {
							map.put(key, value);
							value = new ArrayList<>();
						}
						key = string.replace("#", "").trim();
						keysize++;
					} else {
						value.add(string);
					}
				}
			}
			
			map.put(key, value);

			//

			return map;
		} else {
			throw new BaseException(String.format("%s 文件没找到叫", file.getAbsoluteFile()));
		}

	}
	
}
