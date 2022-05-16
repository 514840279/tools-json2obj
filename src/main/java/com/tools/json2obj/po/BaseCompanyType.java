package com.tools.json2obj.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "base_company_type")
public class BaseCompanyType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer	id;

	private String	companyTypeCode;		// 公司类型编号
	private String	companyTypeName;		// 公司类型名称
	private String	companyTypeRemark;		// 备注
	private String	companyTypeSecondary;	// 二层类型

	public BaseCompanyType() {
		super();
	}

	public BaseCompanyType(String companyTypeName) {
		super();
		this.companyTypeName = companyTypeName;
	}

}
