package com.sywl.domain;

import java.util.Date;

public class SceneDomain {
	
	private String  sceneId;
	
	private String sceneName;
	
	private String sceneMemo;
	
	private Date cTime;
	
	private Date uTime;

	private String userId;

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

	public String getSceneMemo() {
		return sceneMemo;
	}

	public void setSceneMemo(String sceneMemo) {
		this.sceneMemo = sceneMemo;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
