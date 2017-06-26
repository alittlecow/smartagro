package com.sywl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sywl.dao.ClientVersionMapper;
import com.sywl.domain.ClientVersion;

@Service
@Transactional
public class VersionService {

	@Autowired
	private ClientVersionMapper clientVersionMapper;
	
    public ClientVersion selectByVersionCode(String versionCode){
    	return clientVersionMapper.selectByVersionCode(versionCode);
    };
	
	
}
