package com.sywl.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sywl.support.BaseResponse;
import com.sywl.utils.DomainUtils;
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
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @ApiOperation(value="查询设备", notes="根据id查询设备详细信息")
    @ApiImplicitParam(name = "id", value = "设备id", required = true, dataType = "String")
    @RequestMapping(value="/queryDeviceById/{id}", method=RequestMethod.GET)
    public @ResponseBody Map<String, Object> queryDeviceById(@PathVariable String id) {
        Map<String,Object> map = new HashMap<String,Object>();

        //非空校验
        if(RequestParamVerifyUtils.isEmpty(id)){
            map.put("result", "error");
            map.put("message", "请求参数不能为空");
            return map;
        }

        DeviceDomain device = deviceService.queryDeviceById(id);

        map.put("result", "success");
        map.put("message", "新增成功");
        map.put("data", device);

        return map;
    }

    @ApiOperation(value="新增设备", notes="新增设备")
    @ApiImplicitParam(name = "device", value = "设备详细实体user", required = true, dataType = "DeviceDomain")
    @RequestMapping(value = "/addDevice", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> addDevice(@RequestBody DeviceDomain deviceDomain) {
        Map<String,Object> map = new HashMap<String,Object>();

        //非空校验
        if(deviceDomain == null){
            map.put("result", "error");
            map.put("message", "请求参数不能为空");
            return map;
        }

        deviceService.insert(deviceDomain);

        map.put("result", "success");
        map.put("message", "新增成功");

        return map;
    }

    @ApiOperation(value="修改设备", notes="修改设备")
    @ApiImplicitParam(name = "device", value = "设备详细实体user", required = true, dataType = "DeviceDomain")
    @RequestMapping(value = "/updateDevice", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateDevice(@RequestBody DeviceDomain requestDeviceDomain) {
        Map<String,Object> map = new HashMap<String,Object>();

        //非空校验
        if(requestDeviceDomain == null ){
            map.put("result", "error");
            map.put("message", "修改失败，请求参数不能为空");
            return map;
        }

        //查询对应设备是否存在
        DeviceDomain dbDeviceDomain = deviceService.queryDeviceById(requestDeviceDomain.getId());
        if (dbDeviceDomain == null) {
            map.put("result", "error");
            map.put("message", "修改失败，设备不存在");
            return map;
        }

        //将需要更新的属性更新到从数据库查出来的实体中,返回需要更新到数据库的新实体
        DeviceDomain newDeviceDomain = null;
        try {
            newDeviceDomain = (DeviceDomain) DomainUtils.updateField(requestDeviceDomain, dbDeviceDomain);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            map.put("result", "error");
            map.put("message", "修改失败，请求参数转换异常");
        }


        deviceService.update(newDeviceDomain);

        map.put("result", "success");
        map.put("message", "修改成功");
        return map;
    }

    @ApiOperation(value="删除设备", notes="根据id删除设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "ids", value = "设备id", required = true, dataType = "String[]")
    })
    @RequestMapping(value = "/deleteDevice", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> deleteDevice(@RequestBody String[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        //String userId = (String) redisUtil.get(token);
        if (ids == null) {
            map.put("result", "error");
            map.put("message", "请求参数不能为空");
        }

        deviceService.deleteDevice(Arrays.asList(ids));
        map.put("result", "success");
        map.put("message", "删除成功");

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
