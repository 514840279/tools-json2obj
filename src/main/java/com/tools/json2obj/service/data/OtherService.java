package com.tools.json2obj.service.data;

import java.util.List;
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
public class OtherService {

	@Autowired
	JsonTableCofigDao	jsonTableCofigDao;

	@Autowired
	JsonTableColumnDao	jsonTableColumnDao;
	
	public void spellSql(String string, String companyId, List<String> list, String sqlfilepath) {
		JsonTableCofig conf = new JsonTableCofig(string);
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

			for (String str : list) {
				JSONObject json = JSON.parseObject(str);
				JSONArray arr = json.getJSONArray("data");
				for (int i = 0; i < arr.size(); i++) {
					JSONObject jsond = (JSONObject) arr.get(i);
					//
					String sql = SpellSql.json2sql(jsond, conf, columns, companyId);

					// 写进sql文件
					TxtFilesWriter.appendWriteToFile(sql, sqlfilepath);
				}
			}
		}
	}
	
}
