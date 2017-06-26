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

import com.sywl.domain.ClientVersion;
import com.sywl.service.VersionService;

@RestController
@EnableAutoConfiguration
@RequestMapping("version")
public class VersionController {
	
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private VersionService versionService;
	
	@RequestMapping(value = "/queryVersion", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> queryVersion(@RequestParam String versionCode) {
        Map map = new HashMap();
        ClientVersion clientVersion = versionService.selectByVersionCode(versionCode);
        map.put("result", "success");
		map.put("message", "");
		map.put("resultCode", "0000");
		map.put("body", clientVersion);
        return map;
    }
}
