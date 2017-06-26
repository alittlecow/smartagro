package com.sywl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sywl.dao.DeviceRelayMapper;
import com.sywl.dao.DeviceTHMapper;
import com.sywl.dao.SceneMapper;
import com.sywl.dao.THRelayMapper;
import com.sywl.domain.Device;
import com.sywl.domain.DeviceResp;
import com.sywl.domain.SceneDomain;
import com.sywl.mtqq.Mqttutils;

@Service
@Transactional
public class SceneService {

	@Autowired
	private SceneMapper sceneMapper;
	
	@Autowired
	private DeviceTHMapper deviceTHMapper;
	
	@Autowired
	private DeviceRelayMapper deviceRelayMapper;
	
	@Autowired
	private THRelayMapper tHRelayMapper;
	
    public int insert(SceneDomain sceneDomain){
    	return sceneMapper.insert(sceneDomain);
    };
    
    public int update(String sceneId,String sceneName,String sceneMemo){
    	return sceneMapper.update(sceneId, sceneName, sceneMemo);
    };
    
    public int delete(String sceneId){
    	deviceTHMapper.updatesceneIdBysceneId(sceneId);
    	deviceRelayMapper.updatesceneIdBysceneId(sceneId);
    	tHRelayMapper.deleteBySceneId(sceneId);
        sceneMapper.delete(sceneId);
        return 0;
    }

	public List<DeviceResp> queryDeviceByUserId(String userId) {
		List<SceneDomain> sceneDomains = sceneMapper.querySceneByUserId(userId);
		List<DeviceResp> deviceResps = new ArrayList<DeviceResp>();
		for(int i = 0;i<sceneDomains.size();i++){
			DeviceResp deviceResp = new DeviceResp();
			deviceResp.setSceneId(sceneDomains.get(i).getSceneId());
			deviceResp.setSceneName(sceneDomains.get(i).getSceneName());
			List<Device> thDevice = deviceTHMapper.queryDeviceBysceneId(sceneDomains.get(i).getSceneId());
			List<Device> relayDevice = deviceRelayMapper.queryDeviceBysceneId(sceneDomains.get(i).getSceneId());
			thDevice.addAll(relayDevice);
			deviceResp.setDevice(thDevice);
			deviceResps.add(deviceResp);
		}
		return deviceResps;
	};
	
//    public UserDomain queryUserByUserName(String userMobile,String userPwd){
//    	UserDomain userDomain = userMapper.queryUserByUserName(userMobile, userPwd);
//    	return userDomain;
//	};
//	
//	public UserDomain queryUserByMobile(String userMobile){
//		return userMapper.queryUserByMobile(userMobile);
//	};
	
}
