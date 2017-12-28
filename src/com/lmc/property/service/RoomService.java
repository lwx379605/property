package com.lmc.property.service;

import com.lmc.property.entity.Room;

public interface RoomService extends BaseService<Room, Long> {

	void createRoom(Room room);
	
	void deleteRoom(Long id);
	
}
