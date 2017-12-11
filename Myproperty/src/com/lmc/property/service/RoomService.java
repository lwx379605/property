package com.lmc.property.service;

import com.lmc.property.entity.Room;

public interface RoomService extends BaseService<Room, Long> {

	void create(Room room);
	
}
