package com.lmc.property.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author 李敏成
 *
 */
@Entity
public class Room extends OrderedEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8065563429169907019L;
	
	@NotEmpty
	@ManyToOne
	private Building building;
	
	@OneToMany(cascade=CascadeType.ALL)
	private Set<Bill> bills;
	
	/**
	 * 房门号(eg:1101室)
	 */
	@NotEmpty
	@Length(max=64)
	@Column(nullable = false)
	private String roomName;
	
	/**
	 * 物业系统分配社区ID
	 */
	@NotEmpty
	@Length(max=32)
	@Column(unique=true	,nullable = false)
	private String outRoomId;
	
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
	
	@ManyToMany(mappedBy="rooms")
	private Set<Renter> renters=new HashSet<>();;

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

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public String getOutRoomId() {
		return outRoomId;
	}

	public void setOutRoomId(String outRoomId) {
		this.outRoomId = outRoomId;
	}

}
