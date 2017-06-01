package com.ll.dd.domain;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class DingUserInfo {

	public String userid;

	public String name;

	public boolean active;

	public String avatar;

	public List<Long> department;

	public String position;

	public String mobile;
	public String tel;
	public String workPlace;
	public String remark;
	public String email;
	public String jobnumber;
	public JSONObject extattr;
	public boolean isAdmin;
	public boolean isBoss;
	public String dingId;

	public DingUserInfo() {
	}

	public DingUserInfo(String userid, String name) {
		this.userid = userid;
		this.name = name;
	}

	@Override
	public String toString() {

		return "User[userid:" + userid + ", name:" + name + ", active:" + active + ", " + "avatar:" + avatar
				+ ", department:" + department + ", position:" + position + ", mobile:" + mobile + ", email:" + email
				+ ", extattr:" + extattr;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<Long> getDepartment() {
		return department;
	}

	public void setDepartment(List<Long> department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobnumber() {
		return jobnumber;
	}

	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}

	public JSONObject getExtattr() {
		return extattr;
	}

	public void setExtattr(JSONObject extattr) {
		this.extattr = extattr;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isBoss() {
		return isBoss;
	}

	public void setBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}

	public String getDingId() {
		return dingId;
	}

	public void setDingId(String dingId) {
		this.dingId = dingId;
	}

}
