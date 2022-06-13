package com.tools.json2obj.service.data;

import java.util.ArrayList;
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
public class NianbaoService {
	
	@Autowired
	JsonTableCofigDao	jsonTableCofigDao;
	
	@Autowired
	JsonTableColumnDao	jsonTableColumnDao;

	// 列表 base_annual_report
	public void spellSql(Map<String, List<String>> map, String companyId, List<String> list, String sqlfilepath) {
		// TODO Auto-generated method stub
		
		JsonTableCofig conf = new JsonTableCofig(JsonTableType.TYPE_6);
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
				JSONArray arr = JSON.parseArray(str);
				for (int i = 0; i < arr.size(); i++) {
					JSONObject jsond = (JSONObject) arr.get(i);
					//
					String sql = SpellSql.json2sql(jsond, conf, columns, companyId);
					
					// 写进sql文件
					TxtFilesWriter.appendWriteToFile(sql, sqlfilepath);
				}
			}
		}
		
		// 企业基本 base_company_2016
		List<String> nian1 = map.get(JsonTableType.TYPE_6_1);
		if (nian1 != null) {
			JsonTableCofig confbase = new JsonTableCofig(JsonTableType.TYPE_6_1);
			Optional<JsonTableCofig> optionalbase = jsonTableCofigDao.findOne(Example.of(confbase));
			if (optionalbase.isPresent()) {
				confbase = optionalbase.get();
				JsonTableColumn column = new JsonTableColumn(confbase.getId());
				List<JsonTableColumn> columns = jsonTableColumnDao.findAll(Example.of(column));
				
				// 对外担保 base_guarantee_2017
				List<JSONObject> dabaoList = jsonObjList(map, JsonTableType.TYPE_6_8);
				JsonTableCofig confdanbao = null;
				List<JsonTableColumn> columndanbaos = null;
				if (dabaoList != null) {
					confdanbao = new JsonTableCofig(JsonTableType.TYPE_6_8);
					Optional<JsonTableCofig> optionaldabao = jsonTableCofigDao.findOne(Example.of(confdanbao));
					if (optionaldabao.isPresent()) {
						confdanbao = optionaldabao.get();
						JsonTableColumn columndanbao = new JsonTableColumn(confdanbao.getId());
						columndanbaos = jsonTableColumnDao.findAll(Example.of(columndanbao));
					}
				}

				// 对外投资 base_investment_2019
				List<JSONObject> touziList = jsonObjList(map, JsonTableType.TYPE_6_5);
				JsonTableCofig conftouzi = null;
				List<JsonTableColumn> columntouzis = null;
				if (touziList != null) {
					conftouzi = new JsonTableCofig(JsonTableType.TYPE_6_5);
					Optional<JsonTableCofig> optionaltouzi = jsonTableCofigDao.findOne(Example.of(conftouzi));
					if (optionaltouzi.isPresent()) {
						conftouzi = optionaltouzi.get();
						JsonTableColumn columntouzi = new JsonTableColumn(conftouzi.getId());
						columntouzis = jsonTableColumnDao.findAll(Example.of(columntouzi));
					}
				}

				// 社保 base_insurance_2015
				List<JSONObject> shebaoList = jsonObjList(map, JsonTableType.TYPE_6_3);
				JsonTableCofig confshebao = null;
				List<JsonTableColumn> columnshebaos = null;
				if (shebaoList != null) {
					confshebao = new JsonTableCofig(JsonTableType.TYPE_6_3);
					Optional<JsonTableCofig> optionalshebao = jsonTableCofigDao.findOne(Example.of(confshebao));
					if (optionalshebao.isPresent()) {
						confshebao = optionalshebao.get();
						JsonTableColumn columnshebao = new JsonTableColumn(confshebao.getId());
						columnshebaos = jsonTableColumnDao.findAll(Example.of(columnshebao));
					}
				}

				// 网站 base_website_2018
				List<JSONObject> wangzhanList = jsonObjList(map, JsonTableType.TYPE_6_4);
				JsonTableCofig confwangzhan = null;
				List<JsonTableColumn> columnwangzhans = null;
				if (wangzhanList != null) {
					confwangzhan = new JsonTableCofig(JsonTableType.TYPE_6_4);
					Optional<JsonTableCofig> optionalwangzhan = jsonTableCofigDao.findOne(Example.of(confwangzhan));
					if (optionalwangzhan.isPresent()) {
						confwangzhan = optionalwangzhan.get();
						JsonTableColumn columnwangzhan = new JsonTableColumn(confwangzhan.getId());
						columnwangzhans = jsonTableColumnDao.findAll(Example.of(columnwangzhan));
					}
				}

				// 企业资产 base_assetsInfo_2013
				JsonTableCofig confzichan = new JsonTableCofig(JsonTableType.TYPE_6_6);
				List<JsonTableColumn> columnzichans = null;
				Optional<JsonTableCofig> optionalzichan = jsonTableCofigDao.findOne(Example.of(confzichan));
				if (optionalzichan.isPresent()) {
					confzichan = optionalzichan.get();
					JsonTableColumn columnzichan = new JsonTableColumn(confzichan.getId());
					columnzichans = jsonTableColumnDao.findAll(Example.of(columnzichan));
				}

				for (String str : nian1) {
					JSONObject json = JSON.parseObject(str);
					JSONArray arr = json.getJSONArray("data");
					for (int i = 0; i < arr.size(); i++) {
						JSONObject jsond = (JSONObject) arr.get(i);
						String anCheId = jsond.getString("anCheId");
						String year = jsond.getString("anCheYear");
						if (!conf.getType().equals(JsonTableType.TYPE_1)) {
							String sql = " delete from " + confbase.getTableName() + year + " where company_id ='" + companyId + "';";
							// 写进sql文件
							TxtFilesWriter.appendWriteToFile(sql, sqlfilepath);
						}

						String basesql = SpellSql.json2sql(jsond, confbase, columns, companyId, year);

						// 写进sql文件
						TxtFilesWriter.appendWriteToFile(basesql, sqlfilepath);

						// TODO 对外担保 base_guarantee_2017
						if (dabaoList != null && confdanbao != null) {
							Boolean flag = true;
							for (JSONObject danbao : dabaoList) {
								String aid = danbao.getString("anCheId");
								if (anCheId.equals(aid)) {
									if (flag) {
										String sql = " delete from " + confdanbao.getTableName() + year + " where company_id ='" + companyId + "';";
										// 写进sql文件
										TxtFilesWriter.appendWriteToFile(sql, sqlfilepath);
										flag = false;
									}
									String sqldabao = SpellSql.json2sql(danbao, confdanbao, columndanbaos, companyId, year);
									// 写进sql文件
									TxtFilesWriter.appendWriteToFile(sqldabao, sqlfilepath);
								}
							}

						}

						// TODO 对外投资 base_investment_2019
						if (touziList != null && conftouzi != null) {
							Boolean flag = true;
							for (JSONObject touzi : touziList) {
								String aid = touzi.getString("anCheId");
								if (anCheId.equals(aid)) {
									if (flag) {
										String sql = " delete from " + conftouzi.getTableName() + year + " where company_id ='" + companyId + "';";
										// 写进sql文件
										TxtFilesWriter.appendWriteToFile(sql, sqlfilepath);
										flag = false;
									}
									String sqldabao = SpellSql.json2sql(touzi, conftouzi, columntouzis, companyId, year);
									// 写进sql文件
									TxtFilesWriter.appendWriteToFile(sqldabao, sqlfilepath);
								}
							}
						}

						// 社保 base_insurance_2015
						if (shebaoList != null && confshebao != null) {
							Boolean flag = true;
							for (JSONObject shebao : shebaoList) {
								String aid = shebao.getString("anCheId");
								if (anCheId.equals(aid)) {
									if (flag) {
										String sql = " delete from " + confshebao.getTableName() + year + " where company_id ='" + companyId + "';";
										// 写进sql文件
										TxtFilesWriter.appendWriteToFile(sql, sqlfilepath);
										flag = false;
									}
									String sqldabao = SpellSql.json2sql(shebao, confshebao, columnshebaos, companyId, year);
									// 写进sql文件
									TxtFilesWriter.appendWriteToFile(sqldabao, sqlfilepath);
								}
							}
						}

						// 网站 base_website_2018
						if (wangzhanList != null && confwangzhan != null) {
							Boolean flag = true;
							for (JSONObject wangzhan : wangzhanList) {
								String aid = wangzhan.getString("anCheId");
								if (anCheId.equals(aid)) {
									if (flag) {
										String sql = " delete from " + confwangzhan.getTableName() + year + " where company_id ='" + companyId + "';";
										// 写进sql文件
										TxtFilesWriter.appendWriteToFile(sql, sqlfilepath);
										flag = false;
									}
									String sqldabao = SpellSql.json2sql(wangzhan, confwangzhan, columnwangzhans, companyId, year);
									// 写进sql文件
									TxtFilesWriter.appendWriteToFile(sqldabao, sqlfilepath);
								}
							}
						}

						if (!conf.getType().equals(JsonTableType.TYPE_1)) {
							String sql = " delete from " + confzichan.getTableName() + year + " where company_id ='" + companyId + "';";
							// 写进sql文件
							TxtFilesWriter.appendWriteToFile(sql, sqlfilepath);
						}
						// 企业资产 base_assetsInfo_2013
						String sqldabao = SpellSql.json2sql(jsond, confzichan, columnzichans, companyId, year);
						// 写进sql文件
						TxtFilesWriter.appendWriteToFile(sqldabao, sqlfilepath);
					}
				}

			}
		}
	}
	
	// 担保
	private List<JSONObject> jsonObjList(Map<String, List<String>> map, String type) {
		List<String> list = map.get(type);
		if (list != null) {
			List<JSONObject> result = new ArrayList<>();
			for (String str : list) {
				JSONObject json = JSON.parseObject(str);
				JSONArray arr = json.getJSONArray("data");
				for (int i = 0; i < arr.size(); i++) {
					JSONObject jsond = (JSONObject) arr.get(i);
					result.add(jsond);
				}
			}
			return result;
		} else {
			return null;
		}
	}

}
