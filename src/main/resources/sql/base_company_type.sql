
DROP TABLE IF EXISTS `base_company_type`;
CREATE TABLE `base_company_type`  (
  `id` varchar(37) PRIMARY KEY,
  `company_type_code` varchar(39)  COMMENT '公司类型编号',
  `company_type_name` varchar(63) COMMENT '公司类型名称',
  `company_type_remark` varchar(31) COMMENT '备注',
  `company_type_secondary` varchar(20)   COMMENT '二层类型'

) ;

-- ----------------------------
-- Records of base_company_type
-- ----------------------------
INSERT INTO `base_company_type` VALUES ('1da2cbbc-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司分公司(国有独资)', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da2cc39-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司(自然人投资或控股)', '9', '5');
INSERT INTO `base_company_type` VALUES ('1da2cc53-f981-11ea-9326-ba3c24c4cebd', '7', '个人独资企业', '2', '3');
INSERT INTO `base_company_type` VALUES ('1da2cc62-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司（非自然人投资或控股的法人独资）', '9', '2');
INSERT INTO `base_company_type` VALUES ('1da2ccb8-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司(自然人投资或控股)', '9', '1');
INSERT INTO `base_company_type` VALUES ('1da2cd52-f981-11ea-9326-ba3c24c4cebd', '9', '国有经营单位(非法人)', '5', '6');
INSERT INTO `base_company_type` VALUES ('1da2cda0-f981-11ea-9326-ba3c24c4cebd', '4', '全民所有制分支机构(非法人)', '11', '6');
INSERT INTO `base_company_type` VALUES ('1da2cdcf-f981-11ea-9326-ba3c24c4cebd', '2', '其他有限责任公司分公司', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da2cddf-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司(自然人独资)', '9', '1');
INSERT INTO `base_company_type` VALUES ('1da2ce09-f981-11ea-9326-ba3c24c4cebd', '2', '其他股份有限公司分公司(上市)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da2ce37-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司(自然人投资或控股的法人独资)', '9', '5');
INSERT INTO `base_company_type` VALUES ('1da2ce62-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司(国有控股)(2140)', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da2cecf-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司（自然人独资）', '9', '1');
INSERT INTO `base_company_type` VALUES ('1da2cedd-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司', '9', '2');
INSERT INTO `base_company_type` VALUES ('1da2cf06-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司(国有控股)', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da2cf53-f981-11ea-9326-ba3c24c4cebd', '2', '其他股份有限公司分公司(非上市)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da2cf61-f981-11ea-9326-ba3c24c4cebd', '11', '分公司', NULL, '6');
INSERT INTO `base_company_type` VALUES ('1da2cf9d-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(非上市)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da2cfc5-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(上市、国有控股)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da2d037-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(国有控股)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da2d0b2-f981-11ea-9326-ba3c24c4cebd', '11', '外商投资企业分公司', '10', '6');
INSERT INTO `base_company_type` VALUES ('1da2d175-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(上市)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da2d37b-f981-11ea-9326-ba3c24c4cebd', '5', '有限合伙企业', '3', '3');
INSERT INTO `base_company_type` VALUES ('1da2d3c5-f981-11ea-9326-ba3c24c4cebd', '3', '内资企业法人', NULL, '4');
INSERT INTO `base_company_type` VALUES ('1da2d498-f981-11ea-9326-ba3c24c4cebd', '4', '国有经济', '5', '6');
INSERT INTO `base_company_type` VALUES ('1da2d4b3-f981-11ea-9326-ba3c24c4cebd', '5', '普通合伙企业', '4', '3');
INSERT INTO `base_company_type` VALUES ('1da2d6a4-f981-11ea-9326-ba3c24c4cebd', '2', '分公司', NULL, '6');
INSERT INTO `base_company_type` VALUES ('1da2d754-f981-11ea-9326-ba3c24c4cebd', '3', '全民所有制', '11', '4');
INSERT INTO `base_company_type` VALUES ('1da2d7b9-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(上市、国有控股)(2213)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da2d986-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司(国有独资)(2110)', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da2da43-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司（非自然人投资或控股的法人独资）', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da2da52-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司(2100)', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da2dc21-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da2dc77-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司(非自然人投资或控股的法人独资)', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da2ddd9-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(国有控股)(2223)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da2df79-f981-11ea-9326-ba3c24c4cebd', '2', '其他有限责任公司分公司(2190)', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da2e0bc-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(非上市、国有控股)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da2e13a-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(上市、自然人投资或控股)', '7', '5');
INSERT INTO `base_company_type` VALUES ('1da2e1ef-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da2e34a-f981-11ea-9326-ba3c24c4cebd', '2', '分公司', NULL, '5');
INSERT INTO `base_company_type` VALUES ('1da2e374-f981-11ea-9326-ba3c24c4cebd', '8', '个人独资企业分支机构', '2', '5');
INSERT INTO `base_company_type` VALUES ('1da2e9ac-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(非上市、自然人投资或控股)', '7', '5');
INSERT INTO `base_company_type` VALUES ('1da2ea5e-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司（非上市、国有控股）', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da2eb01-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(上市、外商投资企业投资)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da2ebb9-f981-11ea-9326-ba3c24c4cebd', '4', '全民所有制分支机构(非法人)(4310)', '11', '6');
INSERT INTO `base_company_type` VALUES ('1da2ed4d-f981-11ea-9326-ba3c24c4cebd', '11', '股份有限公司(中外合资、未上市)分公司', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da2edd0-f981-11ea-9326-ba3c24c4cebd', '14', '外国(地区)企业在中国境内从事经营活动', '10', '6');
INSERT INTO `base_company_type` VALUES ('1da2efa4-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司(外商投资企业法人独资)', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da2f139-f981-11ea-9326-ba3c24c4cebd', '11', '非公司外商投资企业分支机构', NULL, '6');
INSERT INTO `base_company_type` VALUES ('1da2f3c2-f981-11ea-9326-ba3c24c4cebd', '1', '其他有限责任公司', '9', '2');
INSERT INTO `base_company_type` VALUES ('1da2f504-f981-11ea-9326-ba3c24c4cebd', '2', '其他股份有限公司分公司(非上市)(2229)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da2f648-f981-11ea-9326-ba3c24c4cebd', '11', '分公司(5810)', NULL, '6');
INSERT INTO `base_company_type` VALUES ('1da2f690-f981-11ea-9326-ba3c24c4cebd', '4', '股份制分支机构', NULL, '6');
INSERT INTO `base_company_type` VALUES ('1da2f72e-f981-11ea-9326-ba3c24c4cebd', '9', '国有事业单位营业', '5', '6');
INSERT INTO `base_company_type` VALUES ('1da2f847-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司（自然人投资或控股的法人独资）', '9', '1');
INSERT INTO `base_company_type` VALUES ('1da303db-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司（非自然人投资或控股的法人独资）(2153)', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da304c0-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(非上市、外商投资企业投资）', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da3052e-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司(法人独资)', '9', '5');
INSERT INTO `base_company_type` VALUES ('1da30842-f981-11ea-9326-ba3c24c4cebd', '11', '外商投资企业分支机构', '10', '6');
INSERT INTO `base_company_type` VALUES ('1da3090b-f981-11ea-9326-ba3c24c4cebd', '11', '台、港、澳投资企业分支机构', NULL, '6');
INSERT INTO `base_company_type` VALUES ('1da309db-f981-11ea-9326-ba3c24c4cebd', '11', '有限责任公司(台港澳法人独资)分公司', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da30b59-f981-11ea-9326-ba3c24c4cebd', '4', '集体分支机构(非法人)', '6', '6');
INSERT INTO `base_company_type` VALUES ('1da30d0f-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司(国有控股)', '9', '2');
INSERT INTO `base_company_type` VALUES ('1da30d1f-f981-11ea-9326-ba3c24c4cebd', '11', '台港澳分公司', NULL, '6');
INSERT INTO `base_company_type` VALUES ('1da3138a-f981-11ea-9326-ba3c24c4cebd', '4', '内资企业法人分支机构(非法人)', NULL, '6');
INSERT INTO `base_company_type` VALUES ('1da31456-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司(外商投资企业投资)', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da31691-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司(法人独资)', '9', '1');
INSERT INTO `base_company_type` VALUES ('1da31d9e-f981-11ea-9326-ba3c24c4cebd', '4', '股份合作制分支机构', '8', '6');
INSERT INTO `base_company_type` VALUES ('1da31e60-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司(外商投资企业与内资合资)', '9', '6');
INSERT INTO `base_company_type` VALUES ('1da31fb2-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司(非自然人投资或控股的法人独资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('1da32a11-f981-11ea-9326-ba3c24c4cebd', '10', '有限责任公司(中外合作)', '9', '2');
INSERT INTO `base_company_type` VALUES ('1da32a1f-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(2200)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da32ac1-f981-11ea-9326-ba3c24c4cebd', '2', '内资分公司', NULL, '6');
INSERT INTO `base_company_type` VALUES ('1da32d55-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(上市)(2210)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da32dfd-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(非上市、外商投资企业投资)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da3360b-f981-11ea-9326-ba3c24c4cebd', '4', '非公司企业法人分支机构（国有经济）', NULL, '6');
INSERT INTO `base_company_type` VALUES ('1da33bd5-f981-11ea-9326-ba3c24c4cebd', '9', '国有经营单位(非法人)(4410)', '5', '6');
INSERT INTO `base_company_type` VALUES ('1da33fe9-f981-11ea-9326-ba3c24c4cebd', '11', '台、港、澳投资企业分公司', NULL, '6');
INSERT INTO `base_company_type` VALUES ('1da3439c-f981-11ea-9326-ba3c24c4cebd', '8', '个人独资企业分支机构(4560)', '2', '5');
INSERT INTO `base_company_type` VALUES ('1da34966-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司(法人独资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('1da350e9-f981-11ea-9326-ba3c24c4cebd', '3', '股份合作制', '8', '4');
INSERT INTO `base_company_type` VALUES ('1da35b25-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司(自然人独资)', '9', '5');
INSERT INTO `base_company_type` VALUES ('1da365eb-f981-11ea-9326-ba3c24c4cebd', '9', '事业单位营业', '1', '6');
INSERT INTO `base_company_type` VALUES ('1da36a60-f981-11ea-9326-ba3c24c4cebd', '3', '联营', '1', '4');
INSERT INTO `base_company_type` VALUES ('1da36d0f-f981-11ea-9326-ba3c24c4cebd', '9', '国有社团法人营业', '5', '6');
INSERT INTO `base_company_type` VALUES ('1da375d5-f981-11ea-9326-ba3c24c4cebd', '9', '股份制企业(非法人)', '1', '6');
INSERT INTO `base_company_type` VALUES ('1da37a85-f981-11ea-9326-ba3c24c4cebd', '3', '国有经济', '5', '4');
INSERT INTO `base_company_type` VALUES ('1da38069-f981-11ea-9326-ba3c24c4cebd', '9', NULL, '1', '6');
INSERT INTO `base_company_type` VALUES ('1da3820f-f981-11ea-9326-ba3c24c4cebd', '9', '联营', '1', '6');
INSERT INTO `base_company_type` VALUES ('1da38b26-f981-11ea-9326-ba3c24c4cebd', '4', '内资非法人企业、非公司私营企业', NULL, '6');
INSERT INTO `base_company_type` VALUES ('1da38c46-f981-11ea-9326-ba3c24c4cebd', '11', '办事处', NULL, '6');
INSERT INTO `base_company_type` VALUES ('1da3900c-f981-11ea-9326-ba3c24c4cebd', '3', '股份制', NULL, '4');
INSERT INTO `base_company_type` VALUES ('1da391bf-f981-11ea-9326-ba3c24c4cebd', '3', '集体所有制', '6', '4');
INSERT INTO `base_company_type` VALUES ('1da3961f-f981-11ea-9326-ba3c24c4cebd', '9', '经营单位(非法人)', '1', '6');
INSERT INTO `base_company_type` VALUES ('1da39b38-f981-11ea-9326-ba3c24c4cebd', '5', '合伙企业', '4', '3');
INSERT INTO `base_company_type` VALUES ('1da39ead-f981-11ea-9326-ba3c24c4cebd', '9', '联营（非法人）', '1', '6');
INSERT INTO `base_company_type` VALUES ('1da3a87f-f981-11ea-9326-ba3c24c4cebd', '3', '全民所有制(3100)', '11', '4');
INSERT INTO `base_company_type` VALUES ('1da3a89c-f981-11ea-9326-ba3c24c4cebd', '4', '集体经济', '6', '6');
INSERT INTO `base_company_type` VALUES ('1da3a943-f981-11ea-9326-ba3c24c4cebd', '3', '联营（法人）', '1', '4');
INSERT INTO `base_company_type` VALUES ('1da3b49e-f981-11ea-9326-ba3c24c4cebd', '5', '非公司私营企业', '4', '6');
INSERT INTO `base_company_type` VALUES ('1da3b899-f981-11ea-9326-ba3c24c4cebd', '9', '集体经营单位(非法人)', '6', '6');
INSERT INTO `base_company_type` VALUES ('1da3bb8f-f981-11ea-9326-ba3c24c4cebd', '2', '股份有限公司分公司(非上市)(2220)', '7', '6');
INSERT INTO `base_company_type` VALUES ('1da3bc60-f981-11ea-9326-ba3c24c4cebd', '3', '非公司企业法人（国有经济）', NULL, '4');
INSERT INTO `base_company_type` VALUES ('1da3bf27-f981-11ea-9326-ba3c24c3cebd', '1', '有限责任公司(自然人投资或控股)(1130)', '9', '1');
INSERT INTO `base_company_type` VALUES ('1da3bf27-f981-11ea-9326-ba3c24c4cebd', '9', '集体事业单位营业', '6', '6');
INSERT INTO `base_company_type` VALUES ('1da3cf58-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司(外商投资企业法人独资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('1da3cf9b-f981-11ea-9326-ba3c24c4cebd', NULL, NULL, NULL, NULL);
INSERT INTO `base_company_type` VALUES ('1da3d076-f981-11ea-9326-ba3c24c4cebd', '2', '有限责任公司分公司（自然人独资）', '9', '5');
INSERT INTO `base_company_type` VALUES ('1da3d231-f981-11ea-9326-ba3c24c4cebd', '1', '其他股份有限公司(非上市)', '7', '2');
INSERT INTO `base_company_type` VALUES ('1da3d250-f981-11ea-9326-ba3c24c4cebd', '1', '股份有限公司', '7', '2');
INSERT INTO `base_company_type` VALUES ('1da3d368-f981-11ea-9326-ba3c24c4cebd', '10', '有限责任公司(台港澳与境内合资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('1da3d578-f981-11ea-9326-ba3c24c4cebd', '1', '股份有限公司(非上市、自然人投资或控股)', '7', '1');
INSERT INTO `base_company_type` VALUES ('1da3d702-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司', '9', '1');
INSERT INTO `base_company_type` VALUES ('1da3db40-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司(国有独资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('1da3e142-f981-11ea-9326-ba3c24c4cebd', '1', '有限责任公司(自然人独资)(1151)', '9', '1');
INSERT INTO `base_company_type` VALUES ('1da3efd1-f981-11ea-9326-ba3c24c4cebd', '10', '有限责任公司(中外合资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('1da3f1d6-f981-11ea-9326-ba3c24c4cebd', '5', '有限合伙企业(4533)', '3', '3');
INSERT INTO `base_company_type` VALUES ('1da40136-f981-11ea-9326-ba3c24c4cebd', '10', '有限责任公司(台港澳自然人独资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('1da41881-f981-11ea-9326-ba3c24c4cebd', '1', '其他有限责任公司(1190)', '9', '2');
INSERT INTO `base_company_type` VALUES ('1da42075-f981-11ea-9326-ba3c24c4cebd', '1', '股份有限公司(非上市)', '7', '2');
INSERT INTO `base_company_type` VALUES ('4f947378-f980-11ea-8741-3497f625c24b', '2', '有限责任公司分公司(自然人独资)(2151)', '9', '5');
INSERT INTO `base_company_type` VALUES ('bc99c4e4-beb6-11eb-952c-ba3c24c4cebd', '10', '有限责任公司(台港澳法人独资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('bc99d621-beb6-11eb-952c-ba3c24c4cebd', '10', '有限责任公司(台港澳与境内合作)', '9', '2');
INSERT INTO `base_company_type` VALUES ('bc99dae2-beb6-11eb-952c-ba3c24c4cebd', '1', '有限责任公司(自然人投资或控股的法人独资)', '9', '1');
INSERT INTO `base_company_type` VALUES ('bc99ddd3-beb6-11eb-952c-ba3c24c4cebd', '10', '有限责任公司(外国法人独资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('bc99df6b-beb6-11eb-952c-ba3c24c4cebd', '10', '有限责任公司(港澳台法人独资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('bc99e80e-beb6-11eb-952c-ba3c24c4cebd', '2', '有限责任公司分公司(国有独资)', '9', '6');
INSERT INTO `base_company_type` VALUES ('bc99e854-beb6-11eb-952c-ba3c24c4cebd', '1', '股份有限公司(上市、国有控股)', '7', '2');
INSERT INTO `base_company_type` VALUES ('bc9a0d83-beb6-11eb-952c-ba3c24c4cebd', '10', '有限责任公司(外国自然人独资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('bc9a1185-beb6-11eb-952c-ba3c24c4cebd', '10', '有限责任公司(台港澳合资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('bc9a1255-beb6-11eb-952c-ba3c24c4cebd', '2', '股份有限公司分公司(非上市、自然人投资或控股)(2222)', '7', '5');
INSERT INTO `base_company_type` VALUES ('bc9a21e4-beb6-11eb-952c-ba3c24c4cebd', '2', '其他股份有限公司分公司(上市)(2219)', '7', '6');
INSERT INTO `base_company_type` VALUES ('bc9a2317-beb6-11eb-952c-ba3c24c4cebd', '1', '有限责任公司(外商投资企业与内资合资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('bc9a245f-beb6-11eb-952c-ba3c24c4cebd', '1', '有限责任公司(自然人投资或控股的法人独资)(1152)', '9', '1');
INSERT INTO `base_company_type` VALUES ('bc9a4a84-beb6-11eb-952c-ba3c24c4cebd', '4', '非公司企业法人分支机构（集体经济）', NULL, '6');
INSERT INTO `base_company_type` VALUES ('bc9a5079-beb6-11eb-952c-ba3c24c4cebd', '10', '有限责任公司(台港澳与外国投资者合资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('bc9ab08f-beb6-11eb-952c-ba3c24c4cebd', '1', '股份有限公司', '7', '1');
INSERT INTO `base_company_type` VALUES ('bc9ad4aa-beb6-11eb-952c-ba3c24c4cebd', '10', '有限责任公司(外商合资)', '9', '2');
INSERT INTO `base_company_type` VALUES ('bc9ae5b5-beb6-11eb-952c-ba3c24c4cebd', '1', '有限责任公司（非自然人投资或控股的法人独资）(1153)', '9', '2');
INSERT INTO `base_company_type` VALUES ('bc9af215-beb6-11eb-952c-ba3c24c4cebd', '3', '集体所有制(3200)', '6', '4');
INSERT INTO `base_company_type` VALUES ('bc9c1c62-beb6-11eb-952c-ba3c24c4cebd', '1', '股份有限公司(上市、自然人投资或控股)', '7', '1');
INSERT INTO `base_company_type` VALUES ('bc9c5261-beb6-11eb-952c-ba3c24c4cebd', '1', '其他股份有限公司(上市)', '7', '2');
INSERT INTO `base_company_type` VALUES ('c558c5be-fbb5-11ea-9326-ba3c24c4cebd', '7', '个人独资企业(4540)', '2', '3');
INSERT INTO `base_company_type` VALUES ('c559362d-fbb5-11ea-9326-ba3c24c4cebd', NULL, '联营', '1', NULL);







insert into JSON_TABLE_COLUMN (id,column_name,create_time,delete_flag,desc,json_name,pid,update_time) values(113,'company_type','2022-05-16',0,'公司类型','公司类型',1,'2022-05-16');

insert into JSON_TABLE_COLUMN (id,column_name,create_time,delete_flag,desc,json_name,pid,update_time) values(114,'reg_cap_cur','2022-05-16',0,'币种','币种',1,'2022-05-16');


update JSON_TABLE_COLUMN set column_name = 'company_type_CN'where id =14;
