package com.sywl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sywl.dao.DeviceTHMapper;
import com.sywl.domain.DeviceTHDomain;
import com.sywl.domain.SysinfoBean;

@Service
@Transactional
public class DeviceTHService {

	@Autowired
	private DeviceTHMapper deviceTHMapper;
	
    public int insert(DeviceTHDomain deviceTHDomain){
    	return deviceTHMapper.insert(deviceTHDomain);
    };
    public int updateByDeviceCode(SysinfoBean record){
    	return deviceTHMapper.updateByDeviceCode(record);
    };
    
    public DeviceTHDomain queryDeviceByDeviceCode(String deviceCode){
    	return deviceTHMapper.queryDeviceByDeviceCode(deviceCode);
    }
}
