package com.sywl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sywl.dao.DeviceRelayMapper;
import com.sywl.domain.DeviceRelayDomain;
import com.sywl.domain.SysinfoBean;

@Service
@Transactional
public class DeviceRelayService {

	@Autowired
	private DeviceRelayMapper deviceRelayMapper;
	
    public int insert(DeviceRelayDomain deviceRelayDomain){
    	return deviceRelayMapper.insert(deviceRelayDomain);
    };
    
    public int updateByDeviceCode(SysinfoBean record){
    	return deviceRelayMapper.updateByDeviceCode(record);
    };
    
	
}
