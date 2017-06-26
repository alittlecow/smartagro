package com.sywl.dao;

import com.sywl.domain.ClientVersion;

public interface ClientVersionMapper {

    ClientVersion selectByVersionCode(String versionCode);

}