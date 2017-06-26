package com.sywl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sywl.domain.SceneDomain;
import com.sywl.domain.UserDomain;

public interface SceneMapper {
	
	int insert(SceneDomain sceneDomain);
	
	int update(@Param("sceneId")String sceneId,@Param("sceneName")String sceneName,@Param("sceneMemo")String sceneMemo);
	
	int delete(@Param("sceneId")String sceneId);
	
	List<SceneDomain> querySceneByUserId(@Param("userId")String userId);

}
