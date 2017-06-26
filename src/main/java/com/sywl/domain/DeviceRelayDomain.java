package com.sywl.domain;

import java.util.Date;

public class DeviceRelayDomain {
    private String relayId;

    private String deviceCode;

    private String deviceName;

    private String deviceATypeId;

    private String deviceBTypeId;

    private Date cTime;

    private Date uTime;

    private String sceneId;

    private String bat;

    private String lac;

    private String cell;

    private String ccid;

    private String deviceVersion;

    private String type;
    
    private String status;
    
    
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRelayId() {
        return relayId;
    }

    public void setRelayId(String relayId) {
        this.relayId = relayId == null ? null : relayId.trim();
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceATypeId() {
        return deviceATypeId;
    }

    public void setDeviceATypeId(String deviceATypeId) {
        this.deviceATypeId = deviceATypeId == null ? null : deviceATypeId.trim();
    }

    public String getDeviceBTypeId() {
        return deviceBTypeId;
    }

    public void setDeviceBTypeId(String deviceBTypeId) {
        this.deviceBTypeId = deviceBTypeId == null ? null : deviceBTypeId.trim();
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public Date getuTime() {
        return uTime;
    }

    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId == null ? null : sceneId.trim();
    }

    public String getBat() {
        return bat;
    }

    public void setBat(String bat) {
        this.bat = bat == null ? null : bat.trim();
    }

    public String getLac() {
        return lac;
    }

    public void setLac(String lac) {
        this.lac = lac == null ? null : lac.trim();
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell == null ? null : cell.trim();
    }

    public String getCcid() {
        return ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid == null ? null : ccid.trim();
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion == null ? null : deviceVersion.trim();
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
    
}