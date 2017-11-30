package com.lmc.property.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

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
import com.lmc.property.utils.SystemUtils;


/**
 * 
 * @author 李敏成
 *
 */
@Component
public class AlipayServiceImpl {
	
	@Inject
	private FreeMarkerConfigurer freeMarkerConfigurer;
	public final static String REQUEST_URL="https://openapi.alipaydev.com/gateway.do";
	public final static String PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDKpE8Gx7XC/uhBMA4FVo26LgpxU/5/F5rgiUKhCL8/aq4tSg9iIt8I+v58HUenVddu0LvF4IQDJwzj4rGIGp1aP6PnAgEo2Y+VsnFuyqhS9zDAWi45bD1Pgu5H4OM6UOZ9CHPwvdJ1f/ERyAC+HK7t7WS7yOmjehuoyoNtFzKnV3rdbOBwTGQqKWKVOXDBy6/oRRMY/GNclNJbErEtpFpZmzSaKj9zMOQ1YYBCJsQ7dFlRDak5zN2jdKDPF/p1sVaKPDIC7V3+tld4RGmqEcVVoE4IZ90DqbMGgHOCMhPwLPt2CcY1Q2/sw5bjS7RUOjpTOBoHmAF90FaDiJ1XwNa7AgMBAAECggEACOSFA0soUtJU5udw9hMCTTVQCAEjnfgvN8NORTrZCbNoyVwCqM/W8dHx8Qhn6JP/zEEkvF5J7Ml961I7cDmaxicczjpsSpqhjnQyPiZulJV8cjSC6soxNpgMVOMtlZWPR9VjISFzZuyia6FXRwCDPLW6QfNjLWViaVnZwqPN+UHtiKUxqD5aUB7rcoM3SBRlGkXPdRYuTf8PTX2U/g4APErjyAuG7DISkqtHk3tuy60emUS/AYYugUjfs550U3iI4hYlNdZFWaw2qJN12zZ4O+p+UOm7fCpxIICfDvzYtin6sV0enK3VuqEy5suY1meyvcPQeFUnwjMBC0gMh+OfAQKBgQDlozNxoi3u1Per0TnWKKmF+gMQfBDQ75imu8RoQ6zOIX+Zfptgp5oQWLsIRMdMLRu1Bz/bKHq5tyweeojry5XFmHs0SBU8sb/1Zh8kg/+cSCkYe8tWNFTEVs5j3HVPZHv18iamyG9LM1p6bkWa1r33xBv78EN3bbv85DGbKulP2wKBgQDh57vl9DcnfmLjJPaK/qLy3I1WvSzIh5E9YDTIxKvce3qqKBTWRlZ9/OLMILXJUJRBcyn/W0YIMtYhFbO8PtwoY2uaCZPXP0sX8TNLrPwibmyvXwhf1NtnezNYrCecC0YhWHc94qQ36NxVyB380/U3CQHtTJfTHEua7qRJvyo6oQKBgGMQNHEg9Y/Cb9W/PKQWhos1f3ztCJQZyFClT+VqzHFgIhulcoIPy3ah0I2QnilOjFVb+0AgBlF3o6DCW67c9I95aCz1q7OzOowysdye1PYmaxQU03MswCSzDfYmdLT7ND61Z9crQFfAn5eRWpWhbfIkt63HyFMNE7vU9VFrAZthAoGBAOD6XFLjKJqYb/svMKm0dqvPoqMju5nqCWdKjMb1twLZpowXZskPO8w1MdmBKgnXyg50lvrM963qkv5YEqwDHlbKzLqXlzjiu/KYMDHJkUDpICx3RGvMHMWuGLDSoo/xWmKTLA3hzRIxQl3b8qF8pdWtjtG5WerRXyppSAx56aCBAoGAf7GtwIk1ImjZlBUMr0aS2aTX6uPa0M46boV4mXagcFAmw9QQOrl+5LT1kP8rxMw7GfGRLAI35Iz1f1rCmMVbpsA+niEV/sE8mBlDBaHpbZCZGGngRJZmCwlO8MS8iCeZhRGH3+KZps4ARjoWxBqsc5E8GiAkREIbIrqyYvd0zaA=";
	public final static String PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2ZYAd8lM33BklFX3RVjwlR3jd+vGMqndnrgqH5W29Wpvj4N4COzBLxVIZnanNz9iVOqWB7IA8h1pThisSHeCjY47VIW7G/rg+Odbz1CUUjbKwSszu9Wgd3OOMKYk7f6g/MLmRS7JQJ5XKXh8RHDrjMSaxpo3UBcfr5I6+HqqX2hO+NrLysAvqSttkCQq54C6uWfAfbvuaJCaBJt6SNGulOy4fj+/pH5pHuavEFy8guDptPXtH1ynK9ES4tdg7cxMdwMGS4DWO8wn2oQUuNT3IbISg7XE7VOz0Nj5p6wzQuaPAsz4jqrdPcgRthQeeVDAeRrKzKM3oESqeXdxpMmMrwIDAQAB";
	public final static String APPID="2016082500309468";
	
	public static void main(String[] args) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(REQUEST_URL,APPID,PRIVATE_KEY,"json","UTF-8",PUBLIC_KEY,"RSA2");
		//creat(alipayClient);
		//modify(alipayClient);
		//batchQuerySn(alipayClient);
		//queryRoominfo(alipayClient);
		//uploadRoominfo(alipayClient);
		//initBasicservice(alipayClient);
		//queryCommunityDetails(alipayClient);
		//deleteRoominfo(alipayClient);
		//batchUploadBill( alipayClient);
		batchqueryBill(alipayClient);
		//resultQuery( alipayClient);
		//pay();
	}
	
	public void creat(String communityName, String communityAddress, int district, int city, int province, String phone,String out_community_id) throws AlipayApiException {
		Setting setting = SystemUtils.getSetting();
		String appid = setting.getAppID();
		String privateKey = setting.getPrivateKey();
		String publicKey = setting.getPublicKey();
		AlipayClient alipayClient = new DefaultAlipayClient(REQUEST_URL,appid,privateKey,"json","UTF-8",publicKey,"RSA2");
		AlipayEcoCplifeCommunityCreateRequest request = new AlipayEcoCplifeCommunityCreateRequest();
		request.setBizContent("{" +
				"\"community_name\":\""+communityName+"\"," +
				"\"community_address\":\""+communityAddress+"\"," +
				"\"district_code\":\""+district+"\"," +
				"\"city_code\":"+city+"," +
				"\"province_code\":\""+province+"\"," +
				"      \"community_locations\":[" +
				"        \"116.678611|29.006537\"" +
				"      ]," +
//				"      \"associated_pois\":[" +
//				"        \"B0FFF4A30C\"
//				"      ]," +
				"\"hotline\":\""+phone+"\"," +
				"\"out_community_id\":\""+out_community_id+"\""+
				"  }");
		AlipayEcoCplifeCommunityCreateResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
			System.out.println(response.getBody());
		} else {
			System.out.println("调用失败");
		}
	}
	
	public static  void modify(AlipayClient alipayClient) throws AlipayApiException {
		AlipayEcoCplifeCommunityModifyRequest request = new AlipayEcoCplifeCommunityModifyRequest();
		// 如果是开发者代物业公司账号调用接口，必须传入物业给开发者授权的令牌
//		request.putOtherTextParam("app_auth_token","请传入实际的第三方授权令牌值");

		// 示例中通过string拼接json，实际项目建议用json库生成Json请求报文
		request.setBizContent("{" +
		"    \"community_id\":\"AWFWH94M33611\"," +
		"    \"community_name\":\"鄱阳富康小区\"," +
		"    \"community_address\":\"芝阳路46号\"," +
		"	\"district_code\":\"361128\"," +
		"	\"city_code\":\"361100\"," +
		"	\"province_code\":\"360000\"," +
		"      \"community_locations\":[" +
		"        \"116.678611|29.006537\"" +
		"      ]," +
		"      \"associated_pois\":[" +
		"        \"B0FFF4A30C\"" +
		"      ]," +
		"    \"hotline\":\"0793-87654321\"," +
		"    \"out_community_id\":\"12345\"" +
		"  }");
		try {
		    AlipayEcoCplifeCommunityModifyResponse response = alipayClient.execute(request);
		    if(response.isSuccess()){
				System.out.println("调用成功");
				System.out.println(response.getBody());
			} else {
				System.out.println("调用失败");
			}
		} catch (AlipayApiException e) {
		    // 异常处理
		}
	}
	
	public static  void batchQuerySn(AlipayClient alipayClient) throws AlipayApiException {
		AlipayEcoCplifeCommunityBatchqueryRequest request = new AlipayEcoCplifeCommunityBatchqueryRequest();
		request.setBizContent("{" +
		"\"status\":\"ONLINE\"," + //ONLINE、PENDING_ONLINE、MAINTAIN 、OFFLINE 
		"\"page_num\":1," +
		"\"page_size\":300" +
		"  }");
		AlipayEcoCplifeCommunityBatchqueryResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
			System.out.println(response.getBody());
		} else {
			System.out.println("调用失败");
		}
	}
	
	public static void queryRoominfo(AlipayClient alipayClient) throws AlipayApiException {
		AlipayEcoCplifeRoominfoQueryRequest request = new AlipayEcoCplifeRoominfoQueryRequest();
		request.setBizContent("{" +
		"\"community_id\":\"AWFWH94M33611\"," +
		"\"page_num\":1," +
		"\"page_size\":100" +
		"  }");
		AlipayEcoCplifeRoominfoQueryResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
			System.out.println(response.getBody());
		} else {
			System.out.println("调用失败");
		}
	}
	
	public static void queryCommunityDetails(AlipayClient alipayClient) throws AlipayApiException {
		AlipayEcoCplifeCommunityDetailsQueryRequest request = new AlipayEcoCplifeCommunityDetailsQueryRequest();
		request.setBizContent("{" +
		"\"community_id\":\"AWFWH94M33611\"" +
		"  }");
		AlipayEcoCplifeCommunityDetailsQueryResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
			System.out.println(response.getBody());
		} else {
			System.out.println("调用失败");
		}
	}
	
	public static void uploadRoominfo(AlipayClient alipayClient) throws AlipayApiException {
		AlipayEcoCplifeRoominfoUploadRequest request = new AlipayEcoCplifeRoominfoUploadRequest();
		request.setBizContent("{" +
		"\"batch_id\":\"201609201730123756\"," +
		"\"community_id\":\"AWFWH94M33611\"," +
		"      \"room_info_set\":[{" +
		"        \"out_room_id\":\"20160427110001006778440714\"," +
		"\"group\":\"一期\"," +
		"\"building\":\"1栋\"," +
		"\"unit\":\"1单元\"," +
		"\"room\":\"1102室\"," +
		"\"address\":\"一期1栋2单元2204室\"" +
		"        }]" +
		"  }");
		AlipayEcoCplifeRoominfoUploadResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
			System.out.println(response.getBody());
		} else {
			System.out.println("调用失败");
		}
	}
	
	public static void initBasicservice(AlipayClient alipayClient) throws AlipayApiException {
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
		AlipayEcoCplifeBasicserviceInitializeResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
			System.out.println(response.getBody());
		} else {
			System.out.println("调用失败");
		}
	}
	
	public static void deleteRoominfo(AlipayClient alipayClient) throws AlipayApiException {
		AlipayEcoCplifeRoominfoDeleteRequest request = new AlipayEcoCplifeRoominfoDeleteRequest();
		request.setBizContent("{" +
		"\"batch_id\":\"201609201730123754\"," +
		"\"community_id\":\"AWFWH94M33611\"," +
		"      \"out_room_id_set\":[" +
		"        \"20160427110001006778440713\"" +
		"      ]" +
		"  }");
		AlipayEcoCplifeRoominfoDeleteResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
			System.out.println(response.getBody());
		} else {
			System.out.println("调用失败");
		}
	}

	public static void batchUploadBill(AlipayClient alipayClient) throws AlipayApiException {
		AlipayEcoCplifeBillBatchUploadRequest request = new AlipayEcoCplifeBillBatchUploadRequest();
		request.setBizContent("{" +
		"\"batch_id\":\"201607192119100002\"," +
		"\"community_id\":\"AWFWH94M33611\"," +
		"      \"bill_set\":[{" +
		"        \"bill_entry_id\":\"15000120160702\"," +
		"\"out_room_id\":\"20160427110001006778440714\"," +
		//"\"room_address\":\"一期1栋2单元2204室\"," +
		"\"cost_type\":\"物业管理费\"," +
		"\"bill_entry_amount\":\"300.00\"," +
		"\"acct_period\":\"2017年07月\"," +
		"\"release_day\":\"20170701\"," +
		"\"deadline\":\"20171231\"," +
		//"\"relate_id\":\"1234\"," +
		//"\"remark_str\":\"王*五\"" +
		"        }]" +
		"  }");
		AlipayEcoCplifeBillBatchUploadResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
			System.out.println(response.getBody());
		} else {
		System.out.println("调用失败");
		}
	}
	
	public static void batchqueryBill(AlipayClient alipayClient) throws AlipayApiException {
		AlipayEcoCplifeBillBatchqueryRequest request = new AlipayEcoCplifeBillBatchqueryRequest();
		 
//		// 如果是开发者代物业公司账号调用接口，必须传入物业给开发者授权的令牌
//		request.putOtherTextParam("app_auth_token","请传入实际的第三方授权令牌值");
		 
		// 示例中通过string拼接json，实际项目建议用json库生成Json请求报文
		request.setBizContent("{" +
		"    \"community_id\":\"AWFWH94M33611\"," +
//		"    \"bill_status\":\"WAIT_PAYMENT \"," +
		"    \"out_room_id\":\"20160427110001006778440714\"," +
		"    \"cost_type\":\"物业管理费\"," +
//		"	 \"acct_period\":\"2017年07月\"," +
//		"	 \"release_day\":\"20170701\"," +
//		"    \"batch_id\":\"201607192119100001\"," +
		"    \"page_num\":1," +
		"    \"page_size\":300" +
		"  }");
		try {
		    AlipayEcoCplifeBillBatchqueryResponse response = alipayClient.execute(request);
		    if(response.isSuccess()){
				System.out.println("调用成功");
				System.out.println(response.getBody());
			} else {
				System.out.println("调用失败");
			}
		} catch (AlipayApiException e) {
		    // 异常处理
		}
	}
	public static void  resultQuery(AlipayClient alipayClient) throws AlipayApiException {
		AlipayEcoCplifePayResultQueryRequest request = new AlipayEcoCplifePayResultQueryRequest();
		request.setBizContent("{" +
		"\"trade_no\":\"2017112521001004690200384719\"," +//
//		"\"query_token\":\"2GjV8Z/WuK/GgLdt1Lgd0DLcdhfBYzR8G16bm6wqY9o=\"" +
		"}");
		AlipayEcoCplifePayResultQueryResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
			System.out.println(response.getBody());
		} else {
			System.out.println("调用失败");
		}
	}
	
	public static void pay() throws AlipayApiException{
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do","2017112400128339",PRIVATE_KEY,"json","UTF-8",PUBLIC_KEY,"RSA2");
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
		AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
			System.out.println(response.getBody());
		} else {
			System.out.println("调用失败");
		}
	}

}
