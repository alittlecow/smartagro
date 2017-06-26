package com.sywl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sywl.domain.TempHumiDomain;

public interface TempHumiMapper {
    int insert(TempHumiDomain record);

    int insertSelective(TempHumiDomain record);
    
    List<TempHumiDomain> selectBythId(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("deviceCode")String deviceCode);
    
    int insertBatch(List<TempHumiDomain> records);
}