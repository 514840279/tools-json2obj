package com.tools.json2obj.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tools.json2obj.po.JsonTableColumn;

@Repository
public interface JsonTableColumnDao extends JpaRepository<JsonTableColumn, Serializable>, JpaSpecificationExecutor<JsonTableColumn> {
	
}
