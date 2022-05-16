package com.tools.json2obj.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.tools.json2obj.common.BaseException;
import com.tools.json2obj.common.json.JsonFileRead;
import com.tools.json2obj.common.json.TxtFilesWriter;
import com.tools.json2obj.dao.BaseCompanyTypeDao;
import com.tools.json2obj.dao.JsonTableCofigDao;
import com.tools.json2obj.dao.JsonTableColumnDao;
import com.tools.json2obj.po.JsonTableCofig;
import com.tools.json2obj.po.JsonTableColumn;
import com.tools.json2obj.po.JsonTableType;
import com.tools.json2obj.service.data.GudongService;
import com.tools.json2obj.service.data.NianbaoService;
import com.tools.json2obj.service.data.OtherService;

@Service
public class JsonToSqlService {

	@Autowired
	JsonFileRead		jsonFileRead;
	
	@Autowired
	JsonTableCofigDao	jsonTableCofigDao;
	
	@Autowired
	JsonTableColumnDao	jsonTableColumnDao;

	@Autowired
	BaseCompanyTypeDao	baseCompanyTypeDao;
	
	@Autowired
	GudongService		gudongService;
	
	@Autowired
	OtherService		otherService;

	@Autowired
	NianbaoService		nianbaoService;
	
	// 处理企业数据的json格式转sql格式
	public String json2sql(String filePath, String sqlfilepath) {
		try {
			// 处理json文件
			Map<String, List<String>> map = jsonFileRead.read(filePath);
			if (map != null) {
				// 创建结果文件 获取文件路径
				createTofile(filePath, sqlfilepath);
				// 企业信息 - 营业执照信息
				List<String> company = map.get(JsonTableType.TYPE_1);
				// 该企业文件的全局唯一id
				String companyId = jsonforCompany(company.get(0), sqlfilepath);
				// 循环读取每一个类型
				for (String string : map.keySet()) {
					switch (string) {
						// 股东及出资信息上
						case JsonTableType.TYPE_2:
							gudongService.spellSql1(map, companyId, map.get(string), sqlfilepath);
							break;
						// 主要人员信息
						case JsonTableType.TYPE_3:
							// 变更信息
						case JsonTableType.TYPE_4:
							// 行政许可信息
						case JsonTableType.TYPE_9:
							otherService.spellSql(string, companyId, map.get(string), sqlfilepath);
							break;
						case JsonTableType.TYPE_6:
							nianbaoService.spellSql(map, companyId, map.get(string), sqlfilepath);
							break;
// 以下在其他位置处理，这里不需要处理
						case JsonTableType.TYPE_1:
						case JsonTableType.TYPE_2_1:
						case JsonTableType.TYPE_6_1:
						case JsonTableType.TYPE_6_2:
						case JsonTableType.TYPE_6_3:
						case JsonTableType.TYPE_6_4:
						case JsonTableType.TYPE_6_5:
						case JsonTableType.TYPE_6_6:
						case JsonTableType.TYPE_6_7:
						case JsonTableType.TYPE_6_8:
							break;

						default:
							System.out.println(String.format("该类型的数据还没有进行处理{%s}", string));
							break;
					}

				}
			}
		} catch (IOException e) {
			throw new BaseException("文件处理错误：" + e.getMessage());
		}
		return sqlfilepath;

	}

	// 处理 营业执照信息 转换成企业信息
	private String jsonforCompany(String string, String sqlfilepath) {
		String companyId = null;
		// 如果字符串是JSON 格式的 TODO
		if (string.startsWith("{") && string.endsWith("}")) {
			
		} else {
			// 一般复制的 key: value key2: value2
			String[] a = string.split(" ");
			String key = null;
			String value = null;
			Map<String, String> map = new HashMap<>();
			int keysize = 0;
			for (String str : a) {
				if (str.endsWith("：")) {
					if (keysize > 0) {
						map.put(key, value);
						value = null;
					}
					key = str.replace("：", "").trim();
					keysize++;
				} else {
					switch (key) {
						case "成立日期":
						case "核准日期":
						case "营业期限自":
						case "营业期限至":
							value = str.replaceAll("年|月", "-").replace("日", "");
							break;
						case "注册资本":
							String[] ss = str.split("万");
							if (ss.length > 1) {
								value = ss[0];
								map.put("币种", ss[1]);
							} else if (ss.length == 1) {
								value = ss[0];
								map.put("币种", "人民币");
							}
//							value = str.replace("万人民币", "").replace("万", "");
							
							break;

						case "类型":
//							BaseCompanyType type = new BaseCompanyType(str);
//							Optional<BaseCompanyType> optional = baseCompanyTypeDao.findOne(Example.of(type));
//							if (optional.isPresent()) {
//								type = optional.get();
//								map.put("公司类型", type.getCompanyTypeCode());
//							}
							value = str;
							break;

						default:
							value = str;
							break;
					}
				}
			}
			map.put(key, value);
			
			// 获取全局唯一id
			companyId = dataforid(map);

			// 整理成入库结构语句
			String sql = mapToSql(map, companyId);

			// 写进sql文件
			TxtFilesWriter.appendWriteToFile(sql, sqlfilepath);

		}
		
		return companyId;
	}

	// 首先拿统一信用代码作为唯一id，如果没有则用企业名称的md5
	private String dataforid(Map<String, String> map) {
		String companyid = map.get("统一社会信用代码");
		if (companyid == null) {
			String company = map.get("企业名称");
			companyid = MD5Encoder.encode(company.getBytes());
		}
		return companyid;
	}
	
	// 拼接sql
	private String mapToSql(Map<String, String> map, String companyId) {
		JsonTableCofig conf = new JsonTableCofig(JsonTableType.TYPE_1);
		Optional<JsonTableCofig> optional = jsonTableCofigDao.findOne(Example.of(conf));
		if (optional.isPresent()) {
			conf = optional.get();
			JsonTableColumn column = new JsonTableColumn(conf.getId());
			List<JsonTableColumn> columns = jsonTableColumnDao.findAll(Example.of(column));
			
			return SpellSql.map2sql(map, conf, columns, companyId);
		}
		return null;
		
	}
	
	// 创建一个新的sql文件
	private void createTofile(String jsonFilePath, String sqlfilepath) throws IOException {
		File tofile = new File(sqlfilepath);
		if (!tofile.exists()) {
			tofile.getParentFile().mkdirs();
			tofile.createNewFile();
		} else {
			tofile.createNewFile();
			try (FileWriter fileWriter = new FileWriter(tofile)) {
				fileWriter.write("");
				fileWriter.flush();
				fileWriter.close();
			}
		}
	}
}
