package com.lmc.property.service;

import com.lmc.property.entity.Community;

public interface CommunityService extends BaseService<Community, Long> {

	void createCommunity(Community community);

	void deleteCommunity(Long id);

}
