package com.sywl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sywl.domain.Device;
import com.sywl.domain.DeviceRelayDomain;
import com.sywl.domain.SysinfoBean;

public interface DeviceRelayMapper {
    int deleteByPrimaryKey(String relayId);

    int insert(DeviceRelayDomain record);

    int insertSelective(DeviceRelayDomain record);

    DeviceRelayDomain selectByPrimaryKey(String relayId);

    int updateByPrimaryKeySelective(DeviceRelayDomain record);

    int updateByPrimaryKey(DeviceRelayDomain record);
    
    int updatesceneIdBysceneId(String sceneId);
    
    List<Device> queryDeviceBysceneId(String sceneId);
    
    DeviceRelayDomain queryDeviceByDeviceCode(String deviceCode);
    
    int updateDeviceBysceneId(DeviceRelayDomain deviceRelayDomain);
    
    int updateDeviceByDeviceCode(DeviceRelayDomain deviceRelayDomain);
    
    int updateSceneIdByDeviceCode(@Param("deviceCode")String deviceCode);
    
    int updateByDeviceCode(SysinfoBean record);
    
    List<String> selectByStatus();
}