package com.sywl.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sywl.support.BaseResponse;
import com.sywl.web.domain.PayRuleDomain;
import com.sywl.web.service.PayRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by zhanglj on 2017/7/17.
 */
@Api("设备使用收费规则相关")
@RestController
@RequestMapping("/payRule")
public class PayRuleController {

    @Autowired
    PayRuleService payRuleService;

    @ApiOperation(value="查询所有收费规则", notes="查询所有收费规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "请求参数", required = true, dataType = "Map")
    })
    @RequestMapping(value = "/queryListDevice",method = RequestMethod.GET)
    public BaseResponse queryListDevice(@RequestParam Map params) {
        //查询列表分页数据
        int pageNo = MapUtils.getInteger(params, "pageNo", 1);
        int onePageNum = MapUtils.getInteger(params, "onePageNum", 10);
        PageHelper.startPage(pageNo, onePageNum);
        Page<PayRuleDomain> menuList = (Page<PayRuleDomain>) payRuleService.queryListRule(params);
        return new BaseResponse(menuList);
    }
}
