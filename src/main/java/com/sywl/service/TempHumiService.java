package com.sywl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sywl.dao.TempHumiMapper;
import com.sywl.domain.TempHumiDomain;

@Service
@Transactional
public class TempHumiService {

	@Autowired
	private TempHumiMapper tempHumiMapper;
	
    public List<TempHumiDomain> selectBythId(String startTime, String endTime, String deviceCode){
    	return tempHumiMapper.selectBythId(startTime, endTime, deviceCode);
    };
    
    public int insertBatch(List<TempHumiDomain> records){
    	return tempHumiMapper.insertBatch(records);
    }
	
}
