package com.lmc.property.service;

import com.alipay.api.AlipayClient;
import com.lmc.property.entity.Community;

/**
 * 
 * @author 李敏成
 *
 */
public interface AlipayService {
	
	public void creat(Community community) ;
	
	public void modify(Community community) ; 
	
	public void batchQuerySn(AlipayClient alipayClient);
	
	public void queryRoominfo(AlipayClient alipayClient);
	
	public void queryCommunityDetails(AlipayClient alipayClient);
	
	public void uploadRoominfo(AlipayClient alipayClient);
	
	public void initBasicservice(AlipayClient alipayClient);
	
	public void deleteRoominfo(AlipayClient alipayClient);
	
	public void batchUploadBill(AlipayClient alipayClient);
	
	public void batchqueryBill(AlipayClient alipayClient);
	
	public void  resultQuery(AlipayClient alipayClient);
	
	public void pay();
}
