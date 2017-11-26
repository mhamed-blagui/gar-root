package com.gar.resource.domain;

import static com.gar.resource.constants.GarConstants.GAR_SCHEMA;
import static com.gar.resource.constants.GarConstants.GAR_TASK_HISTORY_TABLE_NAME;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = GAR_TASK_HISTORY_TABLE_NAME,schema = GAR_SCHEMA)
public class GarTaskHistory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5295473567821324654L;

	@GeneratedValue
	@Id
	private Long id;

	@Column(name = "TASK_NAME")
	private String taskName;

	@Column(name = "START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Column(name = "END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Column(name = "IS_UP")
	private Boolean isUp;
	
	@Column(name = "DESCRIPTION")
	private String description;
}
