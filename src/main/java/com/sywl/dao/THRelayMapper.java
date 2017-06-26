package com.sywl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sywl.domain.THRelayDomain;

public interface THRelayMapper {
    int deleteByPrimaryKey(String id);

    int insert(THRelayDomain record);

    int insertSelective(THRelayDomain record);
    
    int deleteBySceneId(String sceneId);
    
    THRelayDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(THRelayDomain record);

    int updateByPrimaryKey(THRelayDomain record);
    
    int deleteByRelayId(@Param("relayId")String relayId);
    
    int deleteBythId(@Param("thId")String thId);
    
    List<THRelayDomain> selectDeviceByThId(@Param("thId")String thId);
    
    List<THRelayDomain> selectDeviceByIdAndType(@Param("thId")String thId,@Param("type")String type);
}