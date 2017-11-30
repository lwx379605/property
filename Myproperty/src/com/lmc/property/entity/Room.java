package com.lmc.property.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.engine.internal.Cascade;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author 李敏成
 *
 */
public class Room extends OrderedEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8065563429169907019L;
	
	@NotEmpty
	@ManyToOne
	private Community community;
	
	@OneToMany(cascade=CascadeType.ALL)
	private Set<Bill> bills;
	
	/**
	 * 分类(例如“一期”，“东区”，“香桂苑”等)
	 */
	@Length(max=32)
	private String group;
	
	/**
	 * 幢
	 */
	@NotEmpty
	@Length(max=32)
	@Column(nullable = false)
	private String building;
	
	/**
	 * 单元
	 */
	@Length(max=32)
	private String unit;
	
	/**
	 * 房门号(eg:1101室)
	 */
	@NotEmpty
	@Length(max=64)
	@Column(nullable = false)
	private String roomName;
	
	/**
	 * 阿里系统分配房间id
	 */
	@NotEmpty
	@Length(max=64)
	@Column(unique=true,nullable=false)
	private String aliRoomID;
	
	/**
	 * 房间的完整门牌地址(eg:一期1栋2单元2202室)
	 */
	@NotEmpty
	@Length(max=128)
	@Column(nullable = false)
	private String address;
	
	@ManyToMany
	private Set<Renter> renters;

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Bill> getBills() {
		return bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

	public String getAliRoomID() {
		return aliRoomID;
	}

	public void setAliRoomID(String aliRoomID) {
		this.aliRoomID = aliRoomID;
	}

	public Set<Renter> getRenters() {
		return renters;
	}

	public void setRenters(Set<Renter> renters) {
		this.renters = renters;
	}
	
}
