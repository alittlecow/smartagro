package com.sywl.web.domain;


public class DeviceDomain {
	private String id;
	private String code;
	private byte useStatus;
	private	 byte isBreakdown;
	private double totalMoney;
	private long totalTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public byte getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(byte useStatus) {
		this.useStatus = useStatus;
	}

	public byte getIsBreakdown() {
		return isBreakdown;
	}

	public void setIsBreakdown(byte isBreakdown) {
		this.isBreakdown = isBreakdown;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}
}
