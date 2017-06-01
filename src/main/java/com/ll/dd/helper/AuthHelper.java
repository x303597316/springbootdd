package com.ll.dd.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.open.client.ServiceFactory;
import com.dingtalk.open.client.api.model.corp.JsapiTicket;
import com.dingtalk.open.client.api.service.corp.CorpConnectionService;
import com.dingtalk.open.client.api.service.corp.JsapiService;
import com.dingtalk.open.client.common.SdkInitException;
import com.dingtalk.open.client.common.ServiceException;
import com.dingtalk.open.client.common.ServiceNotExistException;
import com.ll.dd.contas.Env;
import com.ll.dd.exception.OApiException;
import com.ll.dd.exception.OApiResultException;
import com.ll.dd.utils.FileUtils;
import com.ll.dd.utils.HttpHelper;

public class AuthHelper {

	// public static String jsapiTicket = null;
	// public static String accessToken = null;
	public static Timer timer = null;
	// 调整到1小时50分钟
	public static final long cacheTime = 1000 * 60 * 55 * 2;
	public static long currentTime = 0 + cacheTime + 1;
	public static long lastTime = 0;
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/*
	 * 在此方法中，为了避免频繁获取access_token， 在距离上一次获取access_token时间在两个小时之内的情况，
	 * 将直接从持久化存储中读取access_token
	 * 
	 * 因为access_token和jsapi_ticket的过期时间都是7200秒
	 * 所以在获取access_token的同时也去获取了jsapi_ticket
	 * 注：jsapi_ticket是在前端页面JSAPI做权限验证配置的时候需要使用的 具体信息请查看开发者文档--权限验证配置
	 */
	public static String getAccessToken() throws OApiException {
		long curTime = System.currentTimeMillis();
		JSONObject accessTokenValue = (JSONObject) FileUtils.getValue("accesstoken", Env.CORP_ID);
		String accToken = "";
		String jsTicket = "";
		JSONObject jsontemp = new JSONObject();
		if (accessTokenValue == null || curTime - accessTokenValue.getLong("begin_time") >= cacheTime) {
			try {
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				CorpConnectionService corpConnectionService = serviceFactory
						.getOpenService(CorpConnectionService.class);
				accToken = corpConnectionService.getCorpToken(Env.CORP_ID, Env.CORP_SECRET);
				// save accessToken
				JSONObject jsonAccess = new JSONObject();
				jsontemp.clear();
				jsontemp.put("access_token", accToken);
				jsontemp.put("begin_time", curTime);
				jsonAccess.put(Env.CORP_ID, jsontemp);
				FileUtils.write2File(jsonAccess, "accesstoken");

				if (accToken.length() > 0) {

					JsapiService jsapiService = serviceFactory.getOpenService(JsapiService.class);
					JsapiTicket JsapiTicket = jsapiService.getJsapiTicket(accToken, "jsapi");
					jsTicket = JsapiTicket.getTicket();
					JSONObject jsonTicket = new JSONObject();
					jsontemp.clear();
					jsontemp.put("ticket", jsTicket);
					jsontemp.put("begin_time", curTime);
					jsonTicket.put(Env.CORP_ID, jsontemp);
					FileUtils.write2File(jsonTicket, "jsticket");
				}
			} catch (SdkInitException e) {

				e.printStackTrace();
			} catch (ServiceException e) {

				e.printStackTrace();
			} catch (ServiceNotExistException e) {

				e.printStackTrace();
			}

		} else {
			return accessTokenValue.getString("access_token");
		}

		return accToken;
	}

	public static void writeFiles() {

		long curTime = System.currentTimeMillis();
		String accToken = "";
		String jsTicket = "";
		JSONObject jsontemp = new JSONObject();

		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			CorpConnectionService corpConnectionService = serviceFactory.getOpenService(CorpConnectionService.class);
			accToken = corpConnectionService.getCorpToken(Env.CORP_ID, Env.CORP_SECRET);
			// save accessToken
			JSONObject jsonAccess = new JSONObject();
			jsontemp.clear();
			jsontemp.put("access_token", accToken);
			jsontemp.put("begin_time", curTime);
			jsonAccess.put(Env.CORP_ID, jsontemp);
			FileUtils.write2File(jsonAccess, "accesstoken");

			if (accToken.length() > 0) {

				JsapiService jsapiService = serviceFactory.getOpenService(JsapiService.class);
				JsapiTicket JsapiTicket = jsapiService.getJsapiTicket(accToken, "jsapi");
				jsTicket = JsapiTicket.getTicket();
				JSONObject jsonTicket = new JSONObject();
				jsontemp.clear();
				jsontemp.put("ticket", jsTicket);
				jsontemp.put("begin_time", curTime);
				jsonTicket.put(Env.CORP_ID, jsontemp);
				FileUtils.write2File(jsonTicket, "jsticket");
			}
		} catch (SdkInitException e) {

			e.printStackTrace();
		} catch (ServiceException e) {

			e.printStackTrace();
		} catch (ServiceNotExistException e) {

			e.printStackTrace();
		}
	}

	// 正常的情况下，jsapi_ticket的有效期为7200秒，所以开发者需要在某个地方设计一个定时器，定期去更新jsapi_ticket
	public static String getJsapiTicket(String accessToken) throws OApiException {
		JSONObject jsTicketValue = (JSONObject) FileUtils.getValue("jsticket", Env.CORP_ID);
		long curTime = System.currentTimeMillis();
		String jsTicket = "";

		if (jsTicketValue == null || curTime - jsTicketValue.getLong("begin_time") >= cacheTime) {
			ServiceFactory serviceFactory;
			try {
				serviceFactory = ServiceFactory.getInstance();
				JsapiService jsapiService = serviceFactory.getOpenService(JsapiService.class);

				JsapiTicket JsapiTicket = jsapiService.getJsapiTicket(accessToken, "jsapi");
				jsTicket = JsapiTicket.getTicket();

				JSONObject jsonTicket = new JSONObject();
				JSONObject jsontemp = new JSONObject();
				jsontemp.clear();
				jsontemp.put("ticket", jsTicket);
				jsontemp.put("begin_time", curTime);
				jsonTicket.put(Env.CORP_ID, jsontemp);
				FileUtils.write2File(jsonTicket, "jsticket");
			} catch (SdkInitException e) {

				e.printStackTrace();
			} catch (ServiceException e) {

				e.printStackTrace();
			} catch (ServiceNotExistException e) {

				e.printStackTrace();
			}
			return jsTicket;
		} else {
			return jsTicketValue.getString("ticket");
		}
	}

	public static String sign(String ticket, String nonceStr, long timeStamp, String url) throws OApiException {
		String plain = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + String.valueOf(timeStamp)
				+ "&url=" + url;
		try {
			MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
			sha1.reset();
			sha1.update(plain.getBytes("UTF-8"));
			return bytesToHex(sha1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new OApiResultException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			throw new OApiResultException(e.getMessage());
		}
	}

	private static String bytesToHex(byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	@SuppressWarnings("deprecation")
	public static String getConfig(HttpServletRequest request) {
		String urlString = request.getRequestURL().toString();
		String queryString = request.getQueryString();

		String queryStringEncode = null;
		String url;
		if (queryString != null) {
			queryStringEncode = URLDecoder.decode(queryString);
			url = urlString + "?" + queryStringEncode;
		} else {
			url = urlString;
		}

		String nonceStr = "arthur";
		long timeStamp = System.currentTimeMillis() / 1000;
		String signedUrl = url;
		String accessToken = null;
		String ticket = null;
		String signature = null;
		String agentid = null;

		try {
			accessToken = AuthHelper.getAccessToken();

			ticket = AuthHelper.getJsapiTicket(accessToken);
			signature = AuthHelper.sign(ticket, nonceStr, timeStamp, signedUrl);
			agentid = "97589432";

		} catch (OApiException e) {

			e.printStackTrace();
		}
		String configValue = "{jsticket:'" + ticket + "',signature:'" + signature + "',nonceStr:'" + nonceStr
				+ "',timeStamp:'" + timeStamp + "',corpId:'" + Env.CORP_ID + "',agentid:'" + agentid + "'}";
		System.out.println(configValue);
		return configValue;
	}

	public static String getSsoToken() throws OApiException {
		String url = "https://oapi.dingtalk.com/sso/gettoken?corpid=" + Env.CORP_ID + "&corpsecret=" + Env.SSO_Secret;
		JSONObject response = HttpHelper.httpGet(url);
		String ssoToken;
		if (response.containsKey("access_token")) {
			ssoToken = response.getString("access_token");
		} else {
			throw new OApiResultException("Sso_token");
		}
		return ssoToken;

	}

}
