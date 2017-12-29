package com.lmc.property.dao;

import com.lmc.property.entity.Community;
import com.lmc.property.entity.Room;

public interface RoomDao extends BaseDao<Room, Long> {
	
	public void save(Community community);
	
	public void update(Community community);
	
	public void delete(Community community);
}
