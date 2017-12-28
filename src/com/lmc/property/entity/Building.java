package com.lmc.property.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@ManyToOne
	private Community community;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="building")
	private Set<Room> rooms;
	
	public Set<Room> getRooms() {
		return rooms;
	}
	
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

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

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}
	
}
