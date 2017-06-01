package com.ll.dd.domain;

public class DingConfig {
	private String jsticket;

	private String signature;

	private String nonceStr;

	private long timeStamp;

	private String corpId;

	public String getJsticket() {
		return jsticket;
	}

	public void setJsticket(String jsticket) {
		this.jsticket = jsticket;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	private String agentid;

}
