package com.tools.json2obj.service.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tools.json2obj.common.json.TxtFilesWriter;
import com.tools.json2obj.dao.JsonTableCofigDao;
import com.tools.json2obj.dao.JsonTableColumnDao;
import com.tools.json2obj.po.JsonTableCofig;
import com.tools.json2obj.po.JsonTableColumn;
import com.tools.json2obj.po.JsonTableType;
import com.tools.json2obj.service.SpellSql;

@Component
public class GudongService {
	
	@Autowired
	JsonTableCofigDao	jsonTableCofigDao;

	@Autowired
	JsonTableColumnDao	jsonTableColumnDao;
	
	// 股东及出资信息上
	public void spellSql1(Map<String, List<String>> map, String companyId, List<String> list, String sqlfilepath) {
		JsonTableCofig conf = new JsonTableCofig(JsonTableType.TYPE_2);
		Optional<JsonTableCofig> optional = jsonTableCofigDao.findOne(Example.of(conf));
		if (optional.isPresent()) {
			conf = optional.get();
			JsonTableColumn column = new JsonTableColumn(conf.getId());
			List<JsonTableColumn> columns = jsonTableColumnDao.findAll(Example.of(column));

			if (!conf.getType().equals(JsonTableType.TYPE_1)) {
				String sql = " delete from " + conf.getTableName() + " where company_id ='" + companyId + "';";
				// 写进sql文件
				TxtFilesWriter.appendWriteToFile(sql, sqlfilepath);
			}

			// TODO 股东及出资信息上详细信息
			List<String> detl = map.get(JsonTableType.TYPE_2_1);
			Map<String, Map<String, Object>> lima = null;
			if (detl != null) {
				// 处理详细信息为一个map方便取
				lima = json2Map(detl);
			}

			//
			for (String str : list) {
				JSONObject json = JSON.parseObject(str);
				JSONArray arr = json.getJSONArray("data");

				for (int i = 0; i < arr.size(); i++) {
					JSONObject jsondata = (JSONObject) arr.get(i);
					String invId = jsondata.getString("invId");
					Map<String, Object> delmap = null;
					if (lima != null) {
						for (String string : lima.keySet()) {
							if (invId.contains(string)) {
								delmap = lima.get(string);
								break;
							}

						}
					}
					System.out.println(jsondata.toJSONString());

					//
					String sql = SpellSql.json2sql(jsondata, delmap, conf, columns, companyId);

					// 写进sql文件
					TxtFilesWriter.appendWriteToFile(sql, sqlfilepath);
				}

			}

		}
	}

	// 处理详细信息中每一个数据成一个map
	private Map<String, Map<String, Object>> json2Map(List<String> detl) {
		Map<String, Map<String, Object>> result = new HashMap<>();
		for (String string : detl) {
			JSONObject json = JSON.parseObject(string);
			JSONArray arr = json.getJSONArray("data");
			String invId = null;
			Map<String, Object> map = new HashMap<>();
			// 认缴
			JSONArray arr1 = (JSONArray) arr.get(0);
			for (int i = 0; i < arr1.size(); i++) {
				JSONObject json1 = (JSONObject) arr1.get(i);
				for (String s : json1.keySet()) {
					if ("invId".equals(s)) {
						invId = json1.getString(s);
					}
					if ("acConAm".equals(s)) {
						Double d = (Double) map.get(s);
						if (d == null) {
							map.put(s, json1.getDouble(s));
						} else {
							map.put(s, json1.getDouble(s) + d);
						}
					} else {
						map.put(s, json1.getString(s));
					}
				}
			}
			
			// 实缴 公示日期取最新的，出资额累加 出资方式拼接
			JSONArray arr2 = (JSONArray) arr.get(1);
			for (int i = 0; i < arr2.size(); i++) {
				JSONObject json2 = (JSONObject) arr2.get(i);
				for (String s : json2.keySet()) {
					if ("invId".equals(s)) {
						invId = json2.getString(s);
					}
					if ("subConAm".equals(s)) {
						Double d = (Double) map.get(s);
						if (d == null) {
							map.put(s, json2.getDouble(s));
						} else {
							map.put(s, json2.getDouble(s) + d);
						}
					} else {
						map.put(s, json2.getString(s));
					}
				}
				
			}
			result.put(invId, map);

		}
		
		return result;
	}
	
}
