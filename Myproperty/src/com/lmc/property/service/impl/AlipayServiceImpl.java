package com.lmc.property.service.impl;

import org.springframework.stereotype.Component;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayEcoCplifeBasicserviceInitializeRequest;
import com.alipay.api.request.AlipayEcoCplifeBillBatchUploadRequest;
import com.alipay.api.request.AlipayEcoCplifeBillBatchqueryRequest;
import com.alipay.api.request.AlipayEcoCplifeCommunityBatchqueryRequest;
import com.alipay.api.request.AlipayEcoCplifeCommunityCreateRequest;
import com.alipay.api.request.AlipayEcoCplifeCommunityDetailsQueryRequest;
import com.alipay.api.request.AlipayEcoCplifeCommunityModifyRequest;
import com.alipay.api.request.AlipayEcoCplifePayResultQueryRequest;
import com.alipay.api.request.AlipayEcoCplifeRoominfoDeleteRequest;
import com.alipay.api.request.AlipayEcoCplifeRoominfoQueryRequest;
import com.alipay.api.request.AlipayEcoCplifeRoominfoUploadRequest;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayEcoCplifeBasicserviceInitializeResponse;
import com.alipay.api.response.AlipayEcoCplifeBillBatchUploadResponse;
import com.alipay.api.response.AlipayEcoCplifeBillBatchqueryResponse;
import com.alipay.api.response.AlipayEcoCplifeCommunityBatchqueryResponse;
import com.alipay.api.response.AlipayEcoCplifeCommunityCreateResponse;
import com.alipay.api.response.AlipayEcoCplifeCommunityDetailsQueryResponse;
import com.alipay.api.response.AlipayEcoCplifeCommunityModifyResponse;
import com.alipay.api.response.AlipayEcoCplifePayResultQueryResponse;
import com.alipay.api.response.AlipayEcoCplifeRoominfoDeleteResponse;
import com.alipay.api.response.AlipayEcoCplifeRoominfoQueryResponse;
import com.alipay.api.response.AlipayEcoCplifeRoominfoUploadResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.lmc.property.Setting;
import com.lmc.property.entity.Bill;
import com.lmc.property.entity.Community;
import com.lmc.property.entity.Constants;
import com.lmc.property.entity.Room;
import com.lmc.property.entity.Status;
import com.lmc.property.utils.SystemUtils;


/**
 * 
 * @author 李敏成
 *
 */
@Component
public class AlipayServiceImpl {
	
	public AlipayEcoCplifeCommunityCreateResponse creat(Community community) throws AlipayApiException {
		Setting setting = SystemUtils.getSetting();
		String appid = setting.getAppID();
		String privateKey = setting.getPrivateKey();
		String publicKey = setting.getPublicKey();
		AlipayClient alipayClient = new DefaultAlipayClient(Constants.REQUEST_URL,appid,privateKey,"json","UTF-8",publicKey,"RSA2");
		AlipayEcoCplifeCommunityCreateRequest request = new AlipayEcoCplifeCommunityCreateRequest();
		request.setBizContent("{" +
				"\"community_name\":\""+community.getCommunityName()+"\"," +
				"\"community_address\":\""+community.getCommunityAddress()+"\"," +
				"\"district_code\":\""+community.getDistrict()+"\"," +
				"\"city_code\":"+community.getCity()+"," +
				"\"province_code\":\""+community.getProvince()+"\"," +
				"      \"community_locations\":[" +
				"        \"116.678611|29.006537\"" +
				"      ]," +
//				"      \"associated_pois\":[" +
//				"        \"B0FFF4A30C\"
//				"      ]," +
				"\"hotline\":\""+community.getHotline()+"\"," +
				"\"out_community_id\":\""+community.getId()+"\""+
				"  }");
		return alipayClient.execute(request);
	}
	
	public static  AlipayEcoCplifeCommunityModifyResponse modify(Community community) throws AlipayApiException {
		Setting setting = SystemUtils.getSetting();
		String appid = setting.getAppID();
		String privateKey = setting.getPrivateKey();
		String publicKey = setting.getPublicKey();
		AlipayClient alipayClient = new DefaultAlipayClient(Constants.REQUEST_URL,appid,privateKey,"json","UTF-8",publicKey,"RSA2");
		AlipayEcoCplifeCommunityModifyRequest request = new AlipayEcoCplifeCommunityModifyRequest();
		// 如果是开发者代物业公司账号调用接口，必须传入物业给开发者授权的令牌
//		request.putOtherTextParam("app_auth_token","请传入实际的第三方授权令牌值");

		// 示例中通过string拼接json，实际项目建议用json库生成Json请求报文
		request.setBizContent("{" +
		"    \"community_id\":\""+community.getAliCommunityId()+"\"," +
		"    \"community_name\":\""+community.getCommunityName()+"\"," +
		"    \"community_address\":\""+community.getCommunityAddress()+"\"," +
		"	\"district_code\":\""+community.getDistrict()+"\"," +
		"	\"city_code\":\""+community.getCity()+"\"," +
		"	\"province_code\":\""+community.getProvince()+"\"," +
		"      \"community_locations\":[" +
		"        \"116.678611|29.006537\"" +
		"      ]," +
		"      \"associated_pois\":[" +
		"        \"B0FFF4A30C\"" +
		"      ]," +
		"    \"hotline\":\""+community.getHotline()+"\"," +
		"    \"out_community_id\":\""+community.getId()+"\"" +
		"  }");
		return  alipayClient.execute(request);
	}
	
	public static AlipayEcoCplifeRoominfoUploadResponse uploadRoominfo(String community_id,Room... rooms ) throws AlipayApiException {
		Setting setting = SystemUtils.getSetting();
		String appid = setting.getAppID();
		String privateKey = setting.getPrivateKey();
		String publicKey = setting.getPublicKey();
		AlipayClient alipayClient = new DefaultAlipayClient(Constants.REQUEST_URL,appid,privateKey,"json","UTF-8",publicKey,"RSA2");
		AlipayEcoCplifeRoominfoUploadRequest request = new AlipayEcoCplifeRoominfoUploadRequest();
		StringBuffer sb =new StringBuffer();
		for(Room room:rooms){
			sb.append("{" +
				"        \"out_room_id\":\""+room.getId()+"\"," +
				"\"group\":\"一期\"," +
				"\"building\":\"1栋\"," +
				"\"unit\":\"1单元\"," +
				"\"room\":\"1102室\"," +
				"\"address\":\"一期1栋2单元2204室\"" +
				"        },");
		}
		if(sb.length()==0){
			return null;
		}
		request.setBizContent("{" +
		"\"batch_id\":\"201609201730123756\"," +
		"\"community_id\":\""+community_id+"\"," +
		"      \"room_info_set\":["+sb.substring(0, sb.length()-1)+"]" +
		"  }");
		return alipayClient.execute(request);
	}
	
	public static AlipayEcoCplifeBasicserviceInitializeResponse initBasicservice( ) throws AlipayApiException {
		Setting setting = SystemUtils.getSetting();
		String appid = setting.getAppID();
		String privateKey = setting.getPrivateKey();
		String publicKey = setting.getPublicKey();
		AlipayClient alipayClient = new DefaultAlipayClient(Constants.REQUEST_URL,appid,privateKey,"json","UTF-8",publicKey,"RSA2");
		AlipayEcoCplifeBasicserviceInitializeRequest request = new AlipayEcoCplifeBasicserviceInitializeRequest();
		request.setBizContent("{" +
		"\"community_id\":\"AWFWH94M33611\"," +
		"\"service_type\":\"PROPERTY_PAY_BILL_MODE\"," +
		//对于PROPERTY_PAY_BILL_MODE服务类型，该地址表示用户缴费支付完成后开发者系统接受支付结果通知的回调地址。
		"\"external_invoke_address\":\"https://www.baidu.com\"," +
//		"\"account_type\":\"ALIPAY_PARTNER_ID\"," +
//		"\"account\":\"mxphsk4050@sandbox.com\"," +
		"\"service_expires\":\"2017-12-31 23:59:59\"" +
		"  }");
		return  alipayClient.execute(request);
	}
	
	public static AlipayEcoCplifeRoominfoDeleteResponse deleteRoominfo(String community_id ,Long... out_room_id_set) throws AlipayApiException {
		Setting setting = SystemUtils.getSetting();
		String appid = setting.getAppID();
		String privateKey = setting.getPrivateKey();
		String publicKey = setting.getPublicKey();
		AlipayClient alipayClient = new DefaultAlipayClient(Constants.REQUEST_URL,appid,privateKey,"json","UTF-8",publicKey,"RSA2");
		AlipayEcoCplifeRoominfoDeleteRequest request = new AlipayEcoCplifeRoominfoDeleteRequest();
		StringBuffer sb =new StringBuffer();
		for(Long out_room_id:out_room_id_set){
			sb.append(out_room_id+",");
		}
		if(sb.length()==0){
			return null;
		}
		request.setBizContent("{" +
		"\"batch_id\":\"201609201730123754\"," +
		"\"community_id\":\""+community_id+"\"," +
		"      \"out_room_id_set\":[" +
		"        \""+sb.substring(0, sb.length()-1)+"\"" +
		"      ]" +
		"  }");
		return  alipayClient.execute(request);
	}

	public static AlipayEcoCplifeBillBatchUploadResponse batchUploadBill(String community_id,Bill... bills) throws AlipayApiException {
		Setting setting = SystemUtils.getSetting();
		String appid = setting.getAppID();
		String privateKey = setting.getPrivateKey();
		String publicKey = setting.getPublicKey();
		AlipayClient alipayClient = new DefaultAlipayClient(Constants.REQUEST_URL,appid,privateKey,"json","UTF-8",publicKey,"RSA2");
		AlipayEcoCplifeBillBatchUploadRequest request = new AlipayEcoCplifeBillBatchUploadRequest();
		StringBuffer sb = new StringBuffer();
		for (Bill bill:bills){
			sb.append("{" +
				"        \"bill_entry_id\":\""+bill.getId()+"\"," +
				"\"out_room_id\":\""+bill.getRoom().getId()+"\"," +
				//"\"room_address\":\"一期1栋2单元2204室\"," +
				"\"cost_type\":\""+bill.getType()+"\"," +
				"\"bill_entry_amount\":\"300.00\"," +
				"\"acct_period\":\"2017年07月\"," +
				"\"release_day\":\"20170701\"," +
				"\"deadline\":\"20171231\"," +
				//"\"relate_id\":\"1234\"," +
				//"\"remark_str\":\"王*五\"" +
				"},");
		}
		if(sb.length()==0){
			return null;
		}
		request.setBizContent("{" +
		"\"batch_id\":\""+null+"\"," +
		"\"community_id\":\""+community_id+"\"," +
		"      \"bill_set\":["+sb.substring(0, sb.length()-1)+"]" +
		"  }");
		return  alipayClient.execute(request);
	}
	
	public static AlipayEcoCplifeBillBatchqueryResponse batchqueryBill(String community_id,Long out_room_id,Bill.Type type, int pageNum,int pageSize) throws AlipayApiException {
		Setting setting = SystemUtils.getSetting();
		String appid = setting.getAppID();
		String privateKey = setting.getPrivateKey();
		String publicKey = setting.getPublicKey();
		AlipayClient alipayClient = new DefaultAlipayClient(Constants.REQUEST_URL,appid,privateKey,"json","UTF-8",publicKey,"RSA2");
		AlipayEcoCplifeBillBatchqueryRequest request = new AlipayEcoCplifeBillBatchqueryRequest();
		 
//		// 如果是开发者代物业公司账号调用接口，必须传入物业给开发者授权的令牌
//		request.putOtherTextParam("app_auth_token","请传入实际的第三方授权令牌值");
		 
		// 示例中通过string拼接json，实际项目建议用json库生成Json请求报文
		request.setBizContent("{" +
		"    \"community_id\":\""+community_id+"\"," +
//		"    \"bill_status\":\"WAIT_PAYMENT \"," +
		"    \"out_room_id\":\""+out_room_id+"\"," +
		"    \"cost_type\":\""+type+"\"," +
//		"	 \"acct_period\":\"2017年07月\"," +
//		"	 \"release_day\":\"20170701\"," +
//		"    \"batch_id\":\"201607192119100001\"," +
		"    \"page_num\":"+pageNum+"," +
		"    \"page_size\":"+pageSize+"" +
		"  }");
		return alipayClient.execute(request);
	}

	
	public static AlipayFundTransToaccountTransferResponse pay() throws AlipayApiException{
		Setting setting = SystemUtils.getSetting();
		String appid = setting.getAppID();
		String privateKey = setting.getPrivateKey();
		String publicKey = setting.getPublicKey();
		AlipayClient alipayClient = new DefaultAlipayClient(Constants.REQUEST_URL,appid,privateKey,"json","UTF-8",publicKey,"RSA2");
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		request.setBizContent("{" +
		"    \"out_biz_no\":\"314232142366\"," +
		"    \"payee_type\":\"ALIPAY_LOGONID\"," +
		"    \"payee_account\":\"17682344359\"," +
		"    \"amount\":\"0.01\"," +
		"    \"payer_show_name\":\"上海交通卡退款\"," +
		"    \"payee_real_name\":\"李敏成\"," +
		"    \"remark\":\"转账备注\"," +
		"  }");
		return alipayClient.execute(request);
	}
	
	//ok
	
	public static AlipayEcoCplifeCommunityDetailsQueryResponse queryCommunityDetails(String community_id) throws AlipayApiException {
		Setting setting = SystemUtils.getSetting();
		String appid = setting.getAppID();
		String privateKey = setting.getPrivateKey();
		String publicKey = setting.getPublicKey();
		AlipayClient alipayClient = new DefaultAlipayClient(Constants.REQUEST_URL,appid,privateKey,"json","UTF-8",publicKey,"RSA2");
		AlipayEcoCplifeCommunityDetailsQueryRequest request = new AlipayEcoCplifeCommunityDetailsQueryRequest();
		request.setBizContent("{" +
		"\"community_id\":\""+community_id+"\"," +
		"  }");
		return alipayClient.execute(request);
	}
	
	public static AlipayEcoCplifePayResultQueryResponse  resultQuery(String trade_no) throws AlipayApiException {
		Setting setting = SystemUtils.getSetting();
		String appid = setting.getAppID();
		String privateKey = setting.getPrivateKey();
		String publicKey = setting.getPublicKey();
		AlipayClient alipayClient = new DefaultAlipayClient(Constants.REQUEST_URL,appid,privateKey,"json","UTF-8",publicKey,"RSA2");
		AlipayEcoCplifePayResultQueryRequest request = new AlipayEcoCplifePayResultQueryRequest();
		request.setBizContent("{" +
		"\"trade_no\":\""+trade_no+"\"," +//
//		"\"query_token\":\"2GjV8Z/WuK/GgLdt1Lgd0DLcdhfBYzR8G16bm6wqY9o=\"" +
		"}");
		return alipayClient.execute(request);
	}
	
	public static  AlipayEcoCplifeCommunityBatchqueryResponse batchQuerySn(Status status,int pageNum,int pageSize) throws AlipayApiException {
		Setting setting = SystemUtils.getSetting();
		String appid = setting.getAppID();
		String privateKey = setting.getPrivateKey();
		String publicKey = setting.getPublicKey();
		AlipayClient alipayClient = new DefaultAlipayClient(Constants.REQUEST_URL,appid,privateKey,"json","UTF-8",publicKey,"RSA2");
		AlipayEcoCplifeCommunityBatchqueryRequest request = new AlipayEcoCplifeCommunityBatchqueryRequest();
		request.setBizContent("{" +
		"\"status\":\""+status+"\"," + //ONLINE、PENDING_ONLINE、MAINTAIN 、OFFLINE 
		"\"page_num\":"+pageNum+"," +
		"\"page_size\":"+pageSize+"" +
		"  }");
		return alipayClient.execute(request);
	}
	
	public static AlipayEcoCplifeRoominfoQueryResponse queryRoominfo(String community_id,int pageNum,int pageSize) throws AlipayApiException {
		Setting setting = SystemUtils.getSetting();
		String appid = setting.getAppID();
		String privateKey = setting.getPrivateKey();
		String publicKey = setting.getPublicKey();
		AlipayClient alipayClient = new DefaultAlipayClient(Constants.REQUEST_URL,appid,privateKey,"json","UTF-8",publicKey,"RSA2");
		AlipayEcoCplifeRoominfoQueryRequest request = new AlipayEcoCplifeRoominfoQueryRequest();
		request.setBizContent("{" +
		"\"community_id\":\""+community_id+"\"," +
		"\"page_num\":"+pageNum+"," +
		"\"page_size\":"+pageSize+"" +
		"  }");
		return  alipayClient.execute(request);
	}
	
//	public static void main(String[] args) throws AlipayApiException {
//	AlipayClient alipayClient = new DefaultAlipayClient(REQUEST_URL,APPID,PRIVATE_KEY,"json","UTF-8",PUBLIC_KEY,"RSA2");
//	//creat(alipayClient);
//	//modify(alipayClient);
//	//batchQuerySn(alipayClient);
//	//queryRoominfo(alipayClient);
//	//uploadRoominfo(alipayClient);
//	//initBasicservice(alipayClient);
//	//queryCommunityDetails(alipayClient);
//	//deleteRoominfo(alipayClient);
//	//batchUploadBill( alipayClient);
//	batchqueryBill(alipayClient);
//	//resultQuery( alipayClient);
//	//pay();
//}
}
