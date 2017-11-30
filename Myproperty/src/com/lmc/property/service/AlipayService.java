package com.lmc.property.service;

import org.springframework.stereotype.Component;

import com.alipay.api.AlipayClient;

/**
 * 
 * @author 李敏成
 *
 */
@Component
public interface AlipayService {
	
	public final static String REQUEST_URL="https://openapi.alipaydev.com/gateway.do";
	
	public void creat(AlipayClient alipayClient) ;
	
	public void modify(AlipayClient alipayClient) ; 
	
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
