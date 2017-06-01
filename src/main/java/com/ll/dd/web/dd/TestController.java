package com.ll.dd.web.dd;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.open.client.api.model.corp.CorpUserDetail;
import com.ll.dd.contas.Env;
import com.ll.dd.contas.Vars;
import com.ll.dd.domain.ConfigQueryParam;
import com.ll.dd.domain.DingConfig;
import com.ll.dd.domain.DingUserInfo;
import com.ll.dd.domain.UserInfoQueryParam;
import com.ll.dd.exception.OApiException;
import com.ll.dd.helper.AuthHelper;
import com.ll.dd.helper.UserHelper;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/login")
	public Object test(ConfigQueryParam configQueryParam) {
		ModelAndView mv = new ModelAndView("dd/test");
		DingConfig config = new DingConfig();
		long timeStamp = System.currentTimeMillis() / 1000;
		config.setCorpId(Env.CORP_ID);
		config.setTimeStamp(timeStamp);
		config.setNonceStr("arthur");
		config.setAgentid(Vars.AGENT_ID);
		try {
			config.setJsticket(AuthHelper.getJsapiTicket(AuthHelper.getAccessToken()));
			config.setSignature(AuthHelper.sign(config.getJsticket(), config.getNonceStr(), config.getTimeStamp(),
					configQueryParam.getUrl()));
			mv.addObject("configValue", config);
		} catch (OApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}

	// @RequestMapping("/main")
	// public Object main(HttpServletRequest request, String code, String
	// corpId) {
	// ModelAndView mv = new ModelAndView("dd/test2");
	// AuthHelper ah = new AuthHelper();
	// JSONObject jsonObject = JSONObject.parseObject(configValue);
	// mv.addObject("configValue", jsonObject);
	// return mv;
	// }
	@RequestMapping("/main")
	public Object getUserInfo(String code, String corpId) throws Exception {
		ModelAndView mv = new ModelAndView("dd/test2");
//		DingUserInfo userinfo = new DingUserInfo();
		String accessToken = AuthHelper.getAccessToken();
//		System.out.println("access token:" + accessToken);
		CorpUserDetail user = UserHelper.getUser(accessToken,
				UserHelper.getUserInfo(accessToken,code).getUserid());
//		UserHelper.getAgentUserInfo(Env.SSO_Secret, code);
		System.out.println(JSON.toJSONString(user));
		return mv;
	}
}
