package com.sywl.domain;

public class THRelayDomain {
    private String id;

    private String thId;

    private String relayId;

    private String why;

    private String action;

    private Integer enable;

    private String sceneId;
    
    private String type;
    
    private String deviceCode;
    
    private String thCode;
    
    private String relayCode;
    
    private String deviceName;
    
    

    public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getThId() {
        return thId;
    }

    public void setThId(String thId) {
        this.thId = thId == null ? null : thId.trim();
    }

    public String getRelayId() {
        return relayId;
    }

    public void setRelayId(String relayId) {
        this.relayId = relayId == null ? null : relayId.trim();
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why == null ? null : why.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId == null ? null : sceneId.trim();
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getThCode() {
		return thCode;
	}

	public void setThCode(String thCode) {
		this.thCode = thCode;
	}

	public String getRelayCode() {
		return relayCode;
	}

	public void setRelayCode(String relayCode) {
		this.relayCode = relayCode;
	}
    
	
    
}