package com.gar.resource.domain;

import static com.gar.resource.constants.GarConstants.GAR_OPERATION_HISTORY_TABLE_NAME;
import static com.gar.resource.constants.GarConstants.GAR_SCHEMA;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gar.resource.enums.GarOperationEnum;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = GAR_OPERATION_HISTORY_TABLE_NAME,schema = GAR_SCHEMA)
@Getter
@Setter
public class GarOperationHistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -412526516941251615L;

	@Id
	@GeneratedValue
	@Column(name = "OPERATION_PID")
	private Long operationPid;
	
	@Column(name = "OPERATION_NAME")
	@Enumerated(EnumType.STRING)
	private GarOperationEnum operationName;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name = "USER_PID")
	private GarUser user;
}
