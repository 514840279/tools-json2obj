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
@Table(name = "json_table_conf", uniqueConstraints = @UniqueConstraint(columnNames = { "type" }))
public class JsonTableCofig {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer	id;
	
	// 业务类型
	private String	type;

	// 对应的表名称
	private String	tableName;

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

	public JsonTableCofig() {
		super();
	}

	public JsonTableCofig(String type) {
		super();
		this.type = type;
		this.deleteFlag = 0;
	}

}
