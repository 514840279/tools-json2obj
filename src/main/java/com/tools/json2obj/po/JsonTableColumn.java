package com.tools.json2obj.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "json_table_column", uniqueConstraints = @UniqueConstraint(columnNames = { "pid", "jsonName" }))
public class JsonTableColumn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer	id;
	
	// conf 的id
	private Integer	pid;

	// json字段的名称
	private String	jsonName;
	
	// 字段中对应的名称
	private String	columnName;
	
	// 名称
	private String	desc;
	
	// 元数据类型
	private String	type;
	
	// 插入时间
	@Column(name = "create_time", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@org.hibernate.annotations.CreationTimestamp
	@DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date	createTime;

	// 更新时间
	@Column(name = "update_time", updatable = false, insertable = false) // 这里应用数据库更行策略 ON UPDATE CURRENT_TIMESTAMP 所以无需jpa插座
	@Temporal(TemporalType.TIMESTAMP)
	@org.hibernate.annotations.UpdateTimestamp
	@LastModifiedDate
	@DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date	updateTime;

	private Integer	deleteFlag;
	
	public JsonTableColumn() {
		super();
	}
	
	public JsonTableColumn(Integer pid) {
		super();
		this.pid = pid;
		this.deleteFlag = 0;
	}
	
}
