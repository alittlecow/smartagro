package com.sywl.dao;

import com.sywl.domain.DeviceVersion;

public interface DeviceVersionMapper {

    DeviceVersion selectByDeviceCode(String deviceCode);

}