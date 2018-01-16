package com.lmc.property.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.CPCommunitySet;
import com.lmc.property.Page;
import com.lmc.property.Pageable;
import com.lmc.property.entity.Community;
import com.lmc.property.entity.Status;

public interface CommunityService extends BaseService<Community, Long> {

	void createCommunity(Community community) throws AlipayApiException;

	Page<CPCommunitySet> queryCommunitySns(Status status,Pageable pageable) throws AlipayApiException;

	void modifyCommunity(Community community) throws AlipayApiException;

}
