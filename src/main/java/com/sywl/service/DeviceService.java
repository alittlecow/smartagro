package com.sywl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sywl.dao.DeviceMapper;
import com.sywl.dao.DeviceRelayMapper;
import com.sywl.dao.DeviceTHMapper;
import com.sywl.dao.THRelayMapper;
import com.sywl.domain.DeviceDomain;
import com.sywl.domain.DeviceRelayDomain;
import com.sywl.domain.DeviceTHDomain;
import com.sywl.domain.THRelayDomain;
import com.sywl.domain.UserDomain;

@Service
@Transactional
public class DeviceService {

	@Autowired
	private THRelayMapper thRelayMapper;
	
	@Autowired
	private DeviceTHMapper deviceTHMapper;
	
	@Autowired
	private DeviceRelayMapper deviceRelayMapper;

	public int insertRelayDevice(DeviceRelayDomain deviceRelayDomain, THRelayDomain thRelayDomain) {
		// TODO Auto-generated method stub
		DeviceRelayDomain deviceRelay = deviceRelayMapper.queryDeviceByDeviceCode(deviceRelayDomain.getDeviceCode());
		if(deviceRelay==null){
			return 1;
		}
		if(deviceRelay.getSceneId()!=null&&!deviceRelay.getSceneId().equals("")){
			return 2;
		}
		thRelayDomain.setRelayId(deviceRelay.getRelayId());
		deviceRelayMapper.updateDeviceByDeviceCode(deviceRelayDomain);
		thRelayMapper.insert(thRelayDomain);
		return 0;   
	}

	public int insertTHDevice(DeviceTHDomain deviceTHDomain) {
		DeviceTHDomain deviceTH = deviceTHMapper.queryDeviceByDeviceCode(deviceTHDomain.getDeviceCode());
		if(deviceTH==null){
			return 1;
		}
		if(deviceTH.getSceneId()!=null&&!deviceTH.getSceneId().equals("")){
			return 2;
		}
		deviceTHMapper.updateDeviceByDeviceCode(deviceTHDomain);
		return 0;
	};
	
	public int unbindDevice(String deviceCode, String deviceId) {
		if (deviceCode.contains("relay")) {
			deviceRelayMapper.updateSceneIdByDeviceCode(deviceCode);
			thRelayMapper.deleteByRelayId(deviceId);
		}
		if (deviceCode.contains("th")) {
			deviceTHMapper.updateSceneIdByDeviceCode(deviceCode);
			thRelayMapper.deleteBythId(deviceId);
		}
		return 0;
	};
	
	public List<String> getBindDevice(){
		
		List<String> th = deviceTHMapper.selectByStatus();
		
		List<String> relay = deviceRelayMapper.selectByStatus();
		
		th.addAll(relay);
		
		return th;
	}
	
}
