package com.sywl.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sywl.domain.DeviceDomain;
import com.sywl.domain.SceneDomain;
import com.sywl.service.SceneService;
import com.sywl.utils.DateUtils;
import com.sywl.utils.RedisUtil;
import com.sywl.utils.UUIDUtil;

@RestController
@EnableAutoConfiguration
@RequestMapping("scene")
public class SceneController {
	Logger logger = Logger.getLogger(getClass());
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private SceneService sceneService;

	@RequestMapping(value = "/addScene", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addScene(@RequestParam String sceneName, String sceneMemo,
			@RequestParam String token) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = (String) redisUtil.get(token);
		String id = UUIDUtil.getUUId();
		if (userId == null || userId.equals("")) {
			map.put("result", "error");
			map.put("message", "登录已失效");
			map.put("resultCode", "0001");
		} else {
			SceneDomain sceneDomain = new SceneDomain();
			sceneDomain.setUserId(userId);
			sceneDomain.setSceneId(id);
			sceneDomain.setcTime(DateUtils.today());
			sceneDomain.setuTime(DateUtils.today());
			sceneDomain.setSceneName(sceneName);
			sceneDomain.setSceneMemo(sceneMemo);
			sceneService.insert(sceneDomain);
			map.put("result", "success");
			map.put("resultCode", "0000");
			map.put("message", "");
			map.put("sceneId", id);
		}
		return map;
	}
	
	@RequestMapping(value = "/editScene", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> editScene(@RequestParam String sceneName, String sceneMemo,
			@RequestParam String token,@RequestParam String sceneId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = (String) redisUtil.get(token);
		if (userId == null || userId.equals("")) {
			map.put("result", "error");
			map.put("message", "登录已失效");
			map.put("resultCode", "0001");
		} else {
			sceneService.update(sceneId, sceneName, sceneMemo);
			map.put("result", "success");
			map.put("resultCode", "0000");
			map.put("message", "");
		}
		return map;
	}
	
	@RequestMapping(value = "/deleteScene", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> editScene(@RequestParam String token,@RequestParam String sceneId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = (String) redisUtil.get(token);
		if (userId == null || userId.equals("")) {
			map.put("result", "error");
			map.put("message", "登录已失效");
			map.put("resultCode", "0001");
		} else {
			sceneService.delete(sceneId);
			map.put("result", "success");
			map.put("resultCode", "0000");
			map.put("message", "");
		}
		return map;
	}
}
