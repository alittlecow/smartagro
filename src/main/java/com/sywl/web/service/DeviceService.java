package com.sywl.web.service;

import com.sywl.utils.UUIDUtil;
import com.sywl.web.dao.DeviceMapper;
import com.sywl.web.domain.DeviceBindDomain;
import com.sywl.web.domain.DeviceDataHistoryDomain;
import com.sywl.web.domain.DeviceDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DeviceService {

	@Autowired
	private DeviceMapper deviceMapper;

	public List<String> getBindDevice(){
		return null;
	}

	public List<DeviceDomain> queryListDevice(Map<String, Object> map) {
		return deviceMapper.queryListDevice(map);
	}

	public int insert(DeviceDomain deviceDomain){
		String id = UUIDUtil.getUUId();
		deviceDomain.setId(id);
		return deviceMapper.insert(deviceDomain);
	};

	public int update(DeviceDomain deviceDomain){
		return deviceMapper.update(deviceDomain.getId(),deviceDomain.getCode(),deviceDomain.getUseStatus(),
				deviceDomain.getIsBreakdown(),deviceDomain.getTotalMoney(),deviceDomain.getTotalTime());
	};

	public int deleteDevice(List<String> ids){
		deviceMapper.deleteDevice(ids);
		return 0;
	}

	public DeviceDomain queryDeviceById(String id){
		return deviceMapper.queryDeviceById(id);
	};

	public boolean isDeviceBounded(String deviceId){
		boolean isBounded;
		DeviceBindDomain deviceBindDomain = deviceMapper.queryBoundedDeviceByDeviceId(deviceId);
		if (deviceBindDomain == null) {
			isBounded = false;
		}else {
			isBounded = true;
		}
		return isBounded;
	};

	public int bindDevice(DeviceBindDomain deviceBindDomain){
		return deviceMapper.bindDevice(deviceBindDomain);
	};

	public int unbindDevice(String deviceId){
		return deviceMapper.unbindDevice(deviceId);
	};

	public List<DeviceDataHistoryDomain> queryListDeviceData(Map<String, Object> map) {
		return deviceMapper.queryListDeviceData(map);
	}



}
