package com.maan.common.login;

import nl.captcha.Captcha;

public class CommonBean{
	private String loginId;
	private String pwd;
	private String utype;
	private String userId;
	private String userPwd;
	private String status;
	private String newpwd;
	private String repwd;
	private String mailId;
	private String pwdMsg;
	private int swidth;
	private String captchavalue;
	private Captcha captcha;
	
	public String getDefaultSearch(){
		return "Policy No";
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getRepwd() {
		return repwd;
	}
	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getPwdMsg() {
		return pwdMsg;
	}
	public void setPwdMsg(String pwdMsg) {
		this.pwdMsg = pwdMsg;
	}
	public int getSwidth() {
		return swidth;
	}
	public void setSwidth(int swidth) {
		this.swidth = swidth;
	}
	public String getCaptchavalue() {
		return captchavalue;
	}
	public void setCaptchavalue(String captchavalue) {
		this.captchavalue = captchavalue;
	}
	public Captcha getCaptcha() {
		return captcha;
	}
	public void setCaptcha(Captcha captcha) {
		this.captcha = captcha;
	}
}
