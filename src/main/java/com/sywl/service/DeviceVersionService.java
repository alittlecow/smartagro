package com.sywl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sywl.dao.DeviceVersionMapper;
import com.sywl.dao.UserMapper;
import com.sywl.domain.DeviceVersion;
import com.sywl.domain.UserDomain;

@Service
@Transactional
public class DeviceVersionService {

	@Autowired
	private DeviceVersionMapper deviceVersionMapper;
	
    public DeviceVersion selectByDeviceCode(String deviceCode){
    	DeviceVersion deviceVersion = deviceVersionMapper.selectByDeviceCode(deviceCode);
    	return deviceVersion;
	};

	
}
