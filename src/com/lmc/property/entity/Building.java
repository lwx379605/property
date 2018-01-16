package com.lmc.property.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Building extends OrderedEntity<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -302709274627125749L;

	/**
	 * 分类(例如“一期”，“东区”，“香桂苑”等)
	 */
	@Length(max=32)
	private String  group;
	
	/**
	 * 幢/栋
	 */
	@NotNull
	@Length(max=32)
	private int NO;
	
	/**
	 * 单元
	 */
	@Length(max=32)
	private int unit;
	
	@NotEmpty
	private String outCommunityId;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getNO() {
		return NO;
	}

	public void setNO(int nO) {
		NO = nO;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public String getOutCommunityId() {
		return outCommunityId;
	}

	public void setOutCommunityId(String outCommunityId) {
		this.outCommunityId = outCommunityId;
	}
	
}
