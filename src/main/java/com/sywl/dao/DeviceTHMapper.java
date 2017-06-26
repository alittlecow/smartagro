package com.sywl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sywl.domain.Device;
import com.sywl.domain.DeviceRelayDomain;
import com.sywl.domain.DeviceTHDomain;
import com.sywl.domain.SysinfoBean;

public interface DeviceTHMapper {
    int deleteByPrimaryKey(String thId);

    int insert(DeviceTHDomain record);

    int insertSelective(DeviceTHDomain record);
    
    int updatesceneIdBysceneId(String sceneId);

    DeviceTHDomain selectByPrimaryKey(String thId);

    int updateByPrimaryKeySelective(DeviceTHDomain record);

    int updateByPrimaryKey(DeviceTHDomain record);
    
    List<Device> queryDeviceBysceneId(String sceneId);
    
    DeviceTHDomain queryDeviceByDeviceCode(String deviceCode);
    
    int updateDeviceBysceneId(DeviceTHDomain deviceTHDomain);

	void updateDeviceByDeviceCode(DeviceTHDomain deviceTHDomain);
	
    int updateSceneIdByDeviceCode(@Param("deviceCode")String deviceCode);

    int updateByDeviceCode(SysinfoBean record);
    
    List<String> selectByStatus();
}