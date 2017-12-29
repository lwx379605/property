package com.lmc.property.dao;

import com.lmc.property.entity.Community;

public interface CommunityDao extends BaseDao<Community, Long> {
	
	public void save(Community community);
	
	public void update(Community community);
	
	public void delete(Community community);
}
