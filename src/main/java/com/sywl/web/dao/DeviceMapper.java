package com.sywl.web.dao;

import com.sywl.web.domain.DeviceBindDomain;
import com.sywl.web.domain.DeviceDataHistoryDomain;
import com.sywl.web.domain.DeviceDomain;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanglj on 2017/7/8.
 */

public interface DeviceMapper {

	List<DeviceDomain> queryListDevice(Map<String, Object> map);

	int insert(DeviceDomain userDomain);

	int update(@Param("id")String id, @Param("code")String code, @Param("useStatus")Byte useStatus,
					   @Param("isBreakdown")Byte isBreakdown, @Param("totalMoney")double totalMoney, @Param("totalTime")double totalTime);

	DeviceDomain queryDeviceById(@Param("id") String id);

	DeviceBindDomain queryBoundedDeviceByDeviceId(@Param("deviceId") String deviceId);

	int bindDevice(DeviceBindDomain deviceBindDomain);

	int unbindDevice(@Param("deviceId") String deviceId);

	List<DeviceDataHistoryDomain> queryListDeviceData(Map<String, Object> map);

}
