package com.sywl.dao;

import java.util.List;

import com.sywl.domain.DeviceDomain;

public interface DeviceMapper {
	
	int insert(DeviceDomain deviceDomain);
	
	List<DeviceDomain> queryDeviceByUserName(String userToken);

}
