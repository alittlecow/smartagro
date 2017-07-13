package com.sywl.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sywl.support.BaseResponse;
import com.sywl.utils.RequestParamVerifyUtils;
import com.sywl.utils.UUIDUtil;
import com.sywl.web.domain.DeviceBindDomain;
import com.sywl.web.domain.DeviceDataHistoryDomain;
import com.sywl.web.domain.DeviceDomain;
import com.sywl.web.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/13.
 */
@Api("设备相关")
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    /**
     * 查询所有设备
     */
    @ApiOperation(value="查询所有设备", notes="根据条件查询所有设备")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "请求参数", required = true, dataType = "Map")
    })
    @RequestMapping(value = "/queryListDevice",method = RequestMethod.GET)
    public BaseResponse queryListDevice(@RequestParam Map params) {
        //查询列表分页数据
        int pageNo = MapUtils.getInteger(params, "pageNo", 1);
        int onePageNum = MapUtils.getInteger(params, "onePageNum", 10);
        PageHelper.startPage(pageNo, onePageNum);
        Page<DeviceDomain> menuList = (Page<DeviceDomain>) deviceService.queryListDevice(params);
        return new BaseResponse(menuList);
    }

    @ApiOperation(value="新增设备", notes="新增设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "code", value = "设备编号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addDevice", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> addDevice(@RequestParam(value = "code",required = true) String code) {
        Map<String,Object> map = new HashMap<String,Object>();

        //非空校验
        if(RequestParamVerifyUtils.isEmpty(code)){
            map.put("result", "error");
            map.put("message", "设备编号不能为空");
            return map;
        }
        String id = UUIDUtil.getUUId();
        DeviceDomain deviceDomain = new DeviceDomain();
        deviceDomain.setId(id);
        deviceDomain.setCode(code);
        deviceService.insert(deviceDomain);

        map.put("result", "success");
        map.put("message", "新增成功");
        //map.put("token", jwtString);
        //redisUtil.set(jwtString, id);
        return map;
    }

    @ApiOperation(value="修改设备", notes="修改设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "id", value = "设备id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "code", value = "设备code", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "useStatus", value = "使用状态", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "isBreakdown", value = "是否故障", required = false, dataType = "String")
    })
    @RequestMapping(value = "/updateDevice", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateDevice(@RequestParam(value = "id",required = true) String id,
                                                          @RequestParam(value = "code",required = false) String code,
                                                          @RequestParam(value = "useStatus",required = false) String useStatus,
                                                          @RequestParam(value = "isBreakdown",required = false) String isBreakdown,
                                                              @RequestParam(value = "totalMoney",required = false) String totalMoney,
                                                          @RequestParam(value = "totalTime",required = false) String totalTime) {
        Map<String,Object> map = new HashMap<String,Object>();

        //非空校验
        if(RequestParamVerifyUtils.isEmpty(id)){
            map.put("result", "error");
            map.put("message", "设备id不能为空");
            return map;
        }

        DeviceDomain deviceDomain = deviceService.queryDeviceById(id);
        if (deviceDomain == null) {
            map.put("result", "error");
            map.put("message", "修改失败，设备不存在");
            return map;
        }

        if (!(code==null) && !code.equals("")) {
            deviceDomain.setCode(code);
        }
        if (!(useStatus==null) && !useStatus.equals("")) {
            deviceDomain.setUseStatus(Byte.parseByte(useStatus));
        }
        if (!(isBreakdown==null) && !isBreakdown.equals("")) {
            deviceDomain.setIsBreakdown(Byte.parseByte(isBreakdown));
        }
        if (!(totalMoney==null) && !totalMoney.equals("")) {
            deviceDomain.setTotalMoney(Double.parseDouble(totalMoney));
        }
        if (!(totalTime==null) && !totalTime.equals("")) {
            deviceDomain.setTotalTime(Long.parseLong(totalTime));
        }
        deviceService.update(deviceDomain);

        map.put("result", "success");
        map.put("message", "修改成功");
        return map;
    }


    @ApiOperation(value="设备绑定", notes="绑定设备到分销商")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "deviceId", value = "设备id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "dealerId", value = "分销商id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/bindDeviceToDistributor", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> bindDeviceToDistributor(@RequestParam(value = "deviceId",required = true) String deviceId,
                                                                      @RequestParam(value = "dealerId",required = true) String dealerId) {
        Map<String,Object> map = new HashMap<String,Object>();

        //非空校验
        if(RequestParamVerifyUtils.isEmpty(deviceId)){
            map.put("result", "error");
            map.put("message", "绑定失败，设备id不能为空");
            return map;
        }
        if(RequestParamVerifyUtils.isEmpty(deviceId)){
            map.put("result", "error");
            map.put("message", "绑定失败，分销商id不能为空");
            return map;
        }
        //查看设备是否已绑定
        boolean isBounded = deviceService.isDeviceBounded(deviceId);
        if(isBounded) {
            map.put("result", "error");
            map.put("message", "绑定失败，设备已绑定其他分销商，您需要先解绑给设备");
            return map;
        }

        String id = UUIDUtil.getUUId();
        DeviceBindDomain deviceBindDomain = new DeviceBindDomain();
        deviceBindDomain.setId(id);
        deviceBindDomain.setDeviceId(deviceId);
        deviceBindDomain.setDealerId(dealerId);
        deviceBindDomain.setBindTime(new Date());
        //绑定设备
        deviceService.bindDevice(deviceBindDomain);

        map.put("result", "success");
        map.put("message", "绑定成功");
        //map.put("token", jwtString);
        //redisUtil.set(jwtString, id);
        return map;
    }


    @ApiOperation(value="设备解绑", notes="解绑设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "deviceId", value = "设备id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/unbindDevice", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> unbindDevice(@RequestParam(value = "deviceId",required = true) String deviceId) {
        Map<String,Object> map = new HashMap<String,Object>();

        //非空校验
        if(RequestParamVerifyUtils.isEmpty(deviceId)){
            map.put("result", "error");
            map.put("message", "绑定失败，设备id不能为空");
            return map;
        }
        //解绑设备
        deviceService.unbindDevice(deviceId);

        map.put("result", "success");
        map.put("message", "设备解绑成功");
        return map;
    }


    /**
     * 查询设备历史数据
     */
    @ApiOperation(value="查询设备历史数据", notes="查询设备历史数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "请求参数", required = true, dataType = "Map")
    })
    @RequestMapping(value = "/queryListDeviceData",method = RequestMethod.GET)
    public BaseResponse queryListDeviceData(@RequestParam Map params) {
        //查询列表分页数据
        int pageNo = MapUtils.getInteger(params, "pageNo", 1);
        int onePageNum = MapUtils.getInteger(params, "onePageNum", 10);
        PageHelper.startPage(pageNo, onePageNum);
        Page<DeviceDataHistoryDomain> menuList = (Page<DeviceDataHistoryDomain>) deviceService.queryListDeviceData(params);
        return new BaseResponse(menuList);
    }

}
