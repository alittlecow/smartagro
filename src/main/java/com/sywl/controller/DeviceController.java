package com.sywl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sywl.domain.DeviceRelayDomain;
import com.sywl.domain.DeviceResp;
import com.sywl.domain.DeviceTHDomain;
import com.sywl.domain.THRelayDomain;
import com.sywl.domain.TempHumiDomain;
import com.sywl.mtqq.Mqttutils;
import com.sywl.service.DeviceRelayService;
import com.sywl.service.DeviceService;
import com.sywl.service.DeviceTHService;
import com.sywl.service.SceneService;
import com.sywl.service.THRelayService;
import com.sywl.service.TempHumiService;
import com.sywl.utils.DateUtils;
import com.sywl.utils.RedisUtil;
import com.sywl.utils.UUIDUtil;

@RestController
@EnableAutoConfiguration
@RequestMapping("device")
public class DeviceController {

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DeviceRelayService deviceRelayService;

	@Autowired
	private THRelayService thRelayService;

	@Autowired
	private DeviceTHService deviceTHService;

	@Autowired
	private Mqttutils mqttutils;

	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private SceneService sceneService;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private TempHumiService tempHumiService;
	
	@RequestMapping(value = "/addDevice", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addDevice(String action, @RequestParam String deviceCode,
			@RequestParam String deviceName, @RequestParam String sceneId, @RequestParam String token,
			String parentId,String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = (String) redisUtil.get(token);
		if (userId == null || userId.equals("")) {
			map.put("result", "error");
			map.put("message", "登录已失效");
			map.put("resultCode", "0001");
		} else {
			String id = UUIDUtil.getUUId();
			String code[] = deviceCode.split("/");
			if (deviceCode.contains("relay")) {
				DeviceRelayDomain deviceRelayDomain = new DeviceRelayDomain();
				deviceRelayDomain.setSceneId(sceneId);
				deviceRelayDomain.setDeviceCode(deviceCode);
				deviceRelayDomain.setDeviceName(deviceName);
				deviceRelayDomain.setDeviceATypeId(code[0]);
				deviceRelayDomain.setDeviceBTypeId(code[1]);
				deviceRelayDomain.setType(type);
				deviceRelayDomain.setStatus("1");
				deviceRelayDomain.setcTime(DateUtils.today());
				deviceRelayDomain.setRelayId(id);
				THRelayDomain thRelayDomain = new THRelayDomain();
				thRelayDomain.setId(UUIDUtil.getUUId());
				thRelayDomain.setAction(action);
				thRelayDomain.setRelayId(id);
				thRelayDomain.setSceneId(sceneId);
				thRelayDomain.setThId(parentId);
				thRelayDomain.setType(type);
				int result = deviceService.insertRelayDevice(deviceRelayDomain,thRelayDomain);
				if(result==0){
					map.put("result", "success");
					map.put("resultCode", "0000");
					map.put("message", "");
					mqttutils.subscribe(2, deviceCode);
				}else if(result==1){
					map.put("result", "error");
					map.put("resultCode", "0002");
					map.put("message", "该设备不存在");
				}else if(result==2){
					map.put("result", "error");
					map.put("resultCode", "0003");
					map.put("message", "设备已被绑定");
				}
			}
			if (deviceCode.contains("th")) {
				DeviceTHDomain deviceTHDomain = new DeviceTHDomain();
				deviceTHDomain.setcTime(DateUtils.today());
				deviceTHDomain.setDeviceCode(deviceCode);
				deviceTHDomain.setDeviceName(deviceName);
				deviceTHDomain.setDeviceATypeId(code[0]);
				deviceTHDomain.setDeviceBTypeId(code[1]);
				deviceTHDomain.setSceneId(sceneId);
				deviceTHDomain.setStatus("1");
				int result = deviceService.insertTHDevice(deviceTHDomain);
				if(result==0){
					map.put("result", "success");
					map.put("resultCode", "0000");
					map.put("message", "");
					mqttutils.subscribe(2, deviceCode);
				}else if(result==1){
					map.put("result", "error");
					map.put("resultCode", "0002");
					map.put("message", "该设备不存在");
				}else if(result==2){
					map.put("result", "error");
					map.put("resultCode", "0003");
					map.put("message", "设备已被绑定");
				}
			}
			
		}
		return map;
	}

	@RequestMapping(value = "/queryDeviceList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> queryDeviceList(@RequestParam String token) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = (String) redisUtil.get(token);
		if (userId == null || userId.equals("")) {
			map.put("result", "error");
			map.put("message", "登录已失效");
			map.put("resultCode", "0001");
		} else {
			List<DeviceResp> list = sceneService.queryDeviceByUserId(userId);
			map.put("result", "success");
			map.put("resultCode", "0000");
			map.put("message", "");
			map.put("body", list);
		}
		// if(re)
		return map;
	}
	
	@RequestMapping(value = "/unbindDevice", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> unbindDevice(@RequestParam String token,@RequestParam String deviceCode,@RequestParam String deviceId,@RequestParam String sceneId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = (String) redisUtil.get(token);
		if (userId == null || userId.equals("")) {
			map.put("result", "error");
			map.put("message", "登录已失效");
			map.put("resultCode", "0001");
		} else {
			deviceService.unbindDevice(deviceCode, deviceId);
			map.put("result", "success");
			map.put("message", "");
			map.put("resultCode", "0000");
		}
		return map;
	}
	
	@RequestMapping(value = "/queryHistory", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> queryHistory(@RequestParam String token,@RequestParam String startTime,@RequestParam String deviceCode,@RequestParam String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = (String) redisUtil.get(token);
		if (userId == null || userId.equals("")) {
			map.put("result", "error");
			map.put("message", "登录已失效");
			map.put("resultCode", "0001");
		} else {
			List<TempHumiDomain> list = tempHumiService.selectBythId(startTime, endTime, deviceCode);
			map.put("result", "success");
			map.put("message", "");
			map.put("resultCode", "0000");
			map.put("body", list);
		}
		return map;
	}
	
	@RequestMapping(value = "/queryBindDevice", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> queryBindDevice(@RequestParam String token,@RequestParam String deviceId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = (String) redisUtil.get(token);
		if (userId == null || userId.equals("")) {
			map.put("result", "error");
			map.put("message", "登录已失效");
			map.put("resultCode", "0001");
		} else {
			List<THRelayDomain> thRelayDomains =  thRelayService.selectByThId(deviceId);
			map.put("result", "success");
			map.put("message", "");
			map.put("resultCode", "0000");
			map.put("body", thRelayDomains);
		}
		return map;
	}
}
