package com.lmc.property.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayEcoCplifeCommunityCreateRequest;
import com.alipay.api.response.AlipayEcoCplifeCommunityCreateResponse;
import com.lmc.property.Setting;
import com.lmc.property.dao.CommunityDao;
import com.lmc.property.entity.Community;
import com.lmc.property.entity.Constants;
import com.lmc.property.service.AlipayService;
import com.lmc.property.service.CommunityService;
import com.lmc.property.utils.SystemUtils;

@Service
public class CommunityServiceImpl extends BaseServiceImpl<Community, Long> implements CommunityService{
	@Inject 
	private CommunityDao communityDao;
	@Inject 
	AlipayService alipayService;
	@Override
	public void createCommunity(Community community) {

		
	}

	@Override
	public void deleteCommunity(Long id) {
		// TODO Auto-generated method stub
		
	}

}
