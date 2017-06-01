package com.ll.dd;

import java.util.HashMap;
import java.util.Map;

import com.ll.dd.exception.OApiException;
import com.ll.dd.helper.AuthHelper;
import com.ll.dd.utils.HttpRequestProxy;

public class DdTest {
	private static String AccessTokenHttp = "https://oapi.dingtalk.com/gettoken?1=1";
	private static String corpid = "dingb7d498ea1a413bde35c2f4657eb6378f";
	private static String corpsecret = "0sy8lqy1yP-DTlj4u8SXzJsEC7hbJm7Tqrkb2iAOy7dmm0z5qqJDEY2qBRDbn171";
	private static String http11 = "https://oapi.dingtalk.com/gettoken?1=1&corpid=dingb7d498ea1a413bde35c2f4657eb6378f&corpsecret=0sy8lqy1yP-DTlj4u8SXzJsEC7hbJm7Tqrkb2iAOy7dmm0z5qqJDEY2qBRDbn171";

	public String getAccessToken(String corpid1, String corpsecret1) {
		HttpRequestProxy hrp = new HttpRequestProxy();
		Map parameters = new HashMap<>();
		parameters.put("corpid", corpid1);
		parameters.put("corpsecret", corpsecret1);
//		String strReturnInfo = hrp.doGet(AccessTokenHttp, parameters, "UTF-8");
		String strReturnInfo1 = hrp.doGet(http11, "UTF-8");
//		System.out.println(strReturnInfo);
		System.out.println(strReturnInfo1);
		return null;
	}

	public static void main(String[] args) throws OApiException {
		AuthHelper dd = new AuthHelper();
		String s = dd.getAccessToken();
		System.out.println(s);
	}
}
