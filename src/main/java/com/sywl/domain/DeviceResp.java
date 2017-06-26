package com.sywl.domain;

import java.util.List;

public class DeviceResp {

	private String sceneId;

	private String sceneName;

	private List<Device> device;

	
	
	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public void setDevice(List<Device> device) {
		this.device = device;
	}

	public List<Device> getDevice() {
		return this.device;
	}

}
