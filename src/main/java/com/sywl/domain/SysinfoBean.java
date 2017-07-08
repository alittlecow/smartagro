package com.sywl.domain;

public class SysinfoBean {
	/**
     * VER : 1.0.0
     * BAT : 4348
     * LAC : 51DE
     * CELL : 8750
     * CCID : 898602B41116C0830825
     */

    private String VER;
    private String BAT;
    private String LAC;
    private String CELL;
    private String CCID;
    private String deviceCode;
    
    public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getVER() {
        return VER;
    }

    public void setVER(String VER) {
        this.VER = VER;
    }

    public String getBAT() {
        return BAT;
    }

    public void setBAT(String BAT) {
        this.BAT = BAT;
    }

    public String getLAC() {
        return LAC;
    }

    public void setLAC(String LAC) {
        this.LAC = LAC;
    }

    public String getCELL() {
        return CELL;
    }

    public void setCELL(String CELL) {
        this.CELL = CELL;
    }

    public String getCCID() {
        return CCID;
    }

    public void setCCID(String CCID) {
        this.CCID = CCID;
    }
}
