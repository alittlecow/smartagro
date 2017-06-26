package com.sywl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sywl.dao.THRelayMapper;
import com.sywl.domain.THRelayDomain;

@Service
@Transactional
public class THRelayService {

	@Autowired
	private THRelayMapper thRelayMapper;
	
    public int insert(THRelayDomain thRelayDomain){
    	return thRelayMapper.insert(thRelayDomain);
    };
    
    public List<THRelayDomain> selectByThId(String thId){
    	return thRelayMapper.selectDeviceByThId(thId);
    };
    
    public List<THRelayDomain> selectDeviceByIdAndType(String thId,String type){
    	return thRelayMapper.selectDeviceByIdAndType(thId,type);
    };
	
}
