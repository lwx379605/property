package com.lmc.property.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author 李敏成
 *
 */
public class Community extends OrderedEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5675818518232598723L;
	
	/**
	 * 小区名称，最长不超过32个字符。
	 */
	@NotEmpty
	@Length(max=32)
	@Column(nullable = false)
	private String communityName; 
	
	/**
	 * 小区主要详细地址，不需要包含省市区名称。
	 */
	@NotEmpty
	@Length(max=128)
	@Column(nullable = false)
	private String communityAddress; 
	
	/**
	 * 区县编码
	 */
	@NotEmpty
	@Length(max=10)
	@Column(nullable = false)
	private String district; 
	
	/**
	 * 地级市编码
	 */
	@NotEmpty
	@Length(max=10)
	@Column(nullable = false)
	private String city; 
	
	/**
	 * 省份编码
	 */
	@NotEmpty
	@Length(max=10)
	@Column(nullable = false)
	private String province;
	
	/**
	 * 社区位置点
	 */
	@NotEmpty
	@Column(nullable = false)
	private List<Location> communityLocations;
	
	/**
	 * 关联点
	 */
	private List<String> associatedPois;
	
	/**
	 * 阿里系统分配社区ID
	 */
	@NotEmpty
	@Length(max=10)
	@Column(unique=true	,nullable = false)
	private String aliCommunityId;
	
	/**
	 * 小区热线
	 */
	@NotEmpty
	@Length(max=20)
	@Column(nullable = false)
	private String hotline;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="community")
	private Set<Room> rooms;
	
	
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getCommunityAddress() {
		return communityAddress;
	}
	public void setCommunityAddress(String communityAddress) {
		this.communityAddress = communityAddress;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public List<Location> getCommunityLocations() {
		return communityLocations;
	}
	public void setCommunityLocations(List<Location> communityLocations) {
		this.communityLocations = communityLocations;
	}
	public List<String> getAssociatedPois() {
		return associatedPois;
	}
	public void setAssociatedPois(List<String> associatedPois) {
		this.associatedPois = associatedPois;
	}
	public String getHotline() {
		return hotline;
	}
	public void setHotline(String hotline) {
		this.hotline = hotline;
	}
	public Set<Room> getRooms() {
		return rooms;
	}
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
	public String getAliCommunityId() {
		return aliCommunityId;
	}
	public void setAliCommunityId(String aliCommunityId) {
		this.aliCommunityId = aliCommunityId;
	}
}
