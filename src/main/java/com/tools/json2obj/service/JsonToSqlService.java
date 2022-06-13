package com.tools.json2obj.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.JdbcTemplate;
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

	@Autowired
	JdbcTemplate		jdbcTemplate;
	
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
				String companyId = jsonforCompany(company, sqlfilepath);
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
	private String jsonforCompany(List<String> arrstr, String sqlfilepath) {
		String companyId = null;
		for (String string : arrstr) {

			// 如果字符串是JSON 格式的 TODO
			if (string.startsWith("{") && string.endsWith("}")) {

			} else if (string.startsWith("$$")) {
				companyId = string.replace("$$", "").trim();
			} else {
				// 一般复制的 key: value key2: value2
				String[] a = string.split(" ");
				String key = null;
				String value = null;
				Map<String, String> map = new HashMap<>();
				int keysize = 0;
				for (String str : a) {
					if (str.endsWith("：") || str.endsWith(":")) {
						if (keysize > 0) {
							map.put(key, value);
							value = null;
						}
						key = str.replaceAll("：|:", "").trim();
						keysize++;
					} else {
						switch (key) {
							case "成立日期":
							case "核准日期":
							case "营业期限自":
							case "营业期限至":
								value = str.replaceAll("年|月", "-").replace("日", "");
								break;
							case "行业":
							case "所属行业":
								key = "行业";
								value = str;
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
				String sql = null;
				if (StringUtils.isBlank(companyId)) {
					companyId = dataforid(map);
					// 整理成入库结构语句
					sql = mapToSql(map, companyId);
				} else {
					// 整理成入库更新语句
					sql = mapToUpd(map, companyId);
				}
				
				// 写进sql文件
				TxtFilesWriter.appendWriteToFile(sql, sqlfilepath);
				
				// 行业整理
				String companyindustry = map.get("行业");

				sql = industryToSql(companyId, companyindustry);
				
				TxtFilesWriter.appendWriteToFile(sql, sqlfilepath);
				
			}
		}

		return companyId;
	}

	private String mapToUpd(Map<String, String> map, String companyId) {
		JsonTableCofig conf = new JsonTableCofig(JsonTableType.TYPE_1);
		Optional<JsonTableCofig> optional = jsonTableCofigDao.findOne(Example.of(conf));
		if (optional.isPresent()) {
			conf = optional.get();
			JsonTableColumn column = new JsonTableColumn(conf.getId());
			List<JsonTableColumn> columns = jsonTableColumnDao.findAll(Example.of(column));
			
			return SpellSql.map2Upd(map, conf, columns, companyId);
		}
		return null;
	}
	
	private String industryToSql(String companyId, String companyindustry) {
		if (StringUtils.isBlank(companyindustry)) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
//		sb.append(" select i1.superior_code,i1.`code` as code_1,i2.`code` as code_2,i3.`code` as code_3,i4.`code` as code_4,i4.criteria_level,i4.industry_name,i4.`code` as industry_code,1 as sort,concat( '/0_$', i1.superior_code, '/1_$', i1.`code`, '/2_$', i2.`code`, '/3_$', i3.`code`, '/4_$', i4.`code` ) AS industryCodePath ,4 AS stage");
//		sb.append("  from industry_4 i4");
//		sb.append(" inner join industry_3 i3 on i4.superior_code = i3.`code`");
//		sb.append(" inner join industry_2 i2 on i2.`code` = i3.superior_code");
//		sb.append(" INNER JOIN industry_1 i1 on i2.superior_code = i1.`code`");
//		sb.append(" where i4.industry_name = '" + companyindustry + "'");
//		sb.append(" union ");
		sb.append(" select i1.superior_code,i1.`code` as code_1,i2.`code` as code_2,i3.`code` as code_3,null  as code_4,i3.criteria_level,i3.industry_name,i3.`code` as industry_code,2 as sort,concat( '/0_$', i1.superior_code, '/1_$', i1.`code`, '/2_$', i2.`code`, '/3_$', i3.`code` ) AS industryCodePath ,3 AS stage");
		sb.append("  from industry_3 i3");
		sb.append(" inner join industry_2 i2 on i2.`code` = i3.superior_code");
		sb.append(" INNER JOIN industry_1 i1 on i2.superior_code = i1.`code`");
		sb.append(" where i3.industry_name = '" + companyindustry + "'");
		sb.append(" union ");
		sb.append(" select i1.superior_code,i1.`code` as code_1,i2.`code` as code_2,null as code_3,null  as code_4,i2.criteria_level,i2.industry_name,i2.`code` as industry_code,3 as sort,concat( '/0_$', i1.superior_code, '/1_$', i1.`code`, '/2_$', i2.`code` ) AS industryCodePath ,2 AS stage");
		sb.append("  from industry_2 i2");
		sb.append(" INNER JOIN industry_1 i1 on i2.superior_code = i1.`code`");
		sb.append(" where i2.industry_name = '" + companyindustry + "'");
		sb.append(" union ");
		sb.append(" select i1.superior_code,i1.`code` as code_1,null as code_2,null as code_3,null  as code_4,i1.criteria_level,i1.industry_name,i1.`code` as industry_code,4 as sort,concat( '/0_$', i1.superior_code, '/1_$', i1.`code` ) AS industryCodePath ,1 AS stage");
		sb.append("  from industry_1 i1");
		sb.append(" where i1.industry_name = '" + companyindustry + "'");
		sb.append(" union ");
		sb.append(" select i0.`code` as superior_code,null as code_1,null as code_2,null as code_3,null  as code_4,0 as  criteria_level,i0.industry_name,i0.`code` as industry_code,5 as sort,concat( '/0_$',i0.`code` ) AS industryCodePath ,0 AS stage");
		sb.append("  from industry_category i0");
		sb.append(" where i0.industry_name = '" + companyindustry + "'");
		sb.append("  limit 1");
		
		StringBuilder result = new StringBuilder();
		Map<String, Object> a = jdbcTemplate.queryForMap(sb.toString());
		if (a != null) {
			result.append("delete from `data`.base_company_industry_stage where company_id ='" + companyId + "';\n");
			result.append("insert into `data`.base_company_industry_stage (company_id,industry_category,industry_stage1,industry_stage2,industry_stage3)");
			result.append("values('");
			result.append(companyId);
			result.append("'");
			result.append(valueIfNull(a.get("SUPERIOR_CODE")));
			result.append(valueIfNull(a.get("CODE_1")));
			result.append(valueIfNull(a.get("CODE_2")));
			result.append(valueIfNull(a.get("CODE_3")));
			result.append(");\n");

			result.append("delete from `data`.prec_bcis where company_id ='" + companyId + "';\n");
			result.append("insert into `data`.prec_bcis(company_id,industry_code,industry_category,criteria_code_1,criteria_code_2,criteria_code_3,stage,industry_name,industryCodePath)");
			result.append("values('");
			result.append(companyId);
			result.append("'");
			result.append(valueIfNull(a.get("INDUSTRY_CODE")));
			result.append(valueIfNull(a.get("SUPERIOR_CODE")));
			result.append(valueIfNull(a.get("CODE_1")));
			result.append(valueIfNull(a.get("CODE_2")));
			result.append(valueIfNull(a.get("CODE_3")));
			result.append(valueIfNull(a.get("STAGE")));
			result.append(valueIfNull(a.get("INDUSTRY_NAME")));
			result.append(valueIfNull(a.get("INDUSTRYCODEPATH")));
			result.append(");");
		}
		
		return result.toString();
	}

	private Object valueIfNull(Object object) {
		if (object == null) {
			return ",null";
		}
		String string = object.toString();
		if (StringUtils.isBlank(string)) {
			return ",null";
		} else {
			return ",'" + string.trim() + "'";
		}
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
