package com.sywl.web.domain;

import java.util.Date;

public class UserDomain {
	
	private String id;
	
	private String userName;
	
	private String password;
	
	private String role;
	
	private double sharingProportion;

	private byte sex;

	private Date birthday;

	private String realName;

	private String mobile;

	private String email;

	private String address;

	private byte isWithdrawCash;

	private double accountBalance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getSharingProportion() {
		return sharingProportion;
	}

	public void setSharingProportion(double sharingProportion) {
		this.sharingProportion = sharingProportion;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte getIsWithdrawCash() {
		return isWithdrawCash;
	}

	public void setIsWithdrawCash(byte isWithdrawCash) {
		this.isWithdrawCash = isWithdrawCash;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
}
