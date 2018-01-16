package com.lmc.property.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.CPCommunitySet;
import com.alipay.api.response.AlipayEcoCplifeCommunityBatchqueryResponse;
import com.alipay.api.response.AlipayEcoCplifeCommunityModifyResponse;
import com.lmc.property.Page;
import com.lmc.property.Pageable;
import com.lmc.property.entity.Community;
import com.lmc.property.entity.Status;
import com.lmc.property.service.CommunityService;

@Service
public class CommunityServiceImpl extends BaseServiceImpl<Community, Long> implements CommunityService{

	@Override
	public void createCommunity(Community community) throws AlipayApiException {
		AlipayServiceImpl.creat(community);	
	}
	
	/**
	 * 变更物业小区信息
	 * @param community
	 * @throws AlipayApiException
	 */
	@Override
	public void modifyCommunity(Community community) throws AlipayApiException {
		AlipayEcoCplifeCommunityModifyResponse apliResponse = AlipayServiceImpl.modify(community);
		if(!apliResponse.isSuccess()){
			throw new AlipayApiException(apliResponse.getMsg());
		}
	}

	/**
	 * 批量查询支付宝小区
	 */
	@Override
	public Page<CPCommunitySet> queryCommunitySns(Status status,Pageable pageable) throws AlipayApiException {
		AlipayEcoCplifeCommunityBatchqueryResponse apliResponse = AlipayServiceImpl.batchQuerySn(status,pageable.getPageNumber(), pageable.getPageSize());
		if(!apliResponse.isSuccess()){
			throw new AlipayApiException(apliResponse.getMsg());
		}
		List<CPCommunitySet> communityList = apliResponse.getCommunityList();
		return new Page<CPCommunitySet>(communityList, apliResponse.getTotalCommunityCount(), pageable);
	}

}
