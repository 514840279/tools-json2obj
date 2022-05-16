package com.tools.json2obj.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tools.json2obj.po.BaseCompanyType;

@Repository
public interface BaseCompanyTypeDao extends JpaRepository<BaseCompanyType, Serializable>, JpaSpecificationExecutor<BaseCompanyType> {
	
}
