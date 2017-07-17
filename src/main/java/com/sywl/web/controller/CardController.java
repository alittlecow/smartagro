package com.sywl.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sywl.support.BaseResponse;
import com.sywl.web.domain.CardDomain;
import com.sywl.web.service.CardService;
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

import javax.smartcardio.Card;
import java.util.Map;

/**
 * Created by zhanglj on 2017/7/17.
 */
@Api("用户信息")
@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;


    @ApiOperation(value = "获取ID卡详情", notes = "获取ID卡详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "id", required = true, dataType = "String")
    })
    @RequestMapping(value = "queryCardById", method = {RequestMethod.GET})
    public BaseResponse<CardDomain> queryCardById(String id) {
        CardDomain card = cardService.queryCardById(id);
        return new BaseResponse<>(card);
    }

    /**
     * 查询所有设备
     */
    @ApiOperation(value="查询所有ID用户", notes="根据条件查询所有ID用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "请求参数", required = true, dataType = "Map")
    })
    @RequestMapping(value = "/queryListCard",method = RequestMethod.GET)
    public BaseResponse queryListCard(@RequestParam Map params) {
        //查询列表分页数据
        int pageNo = MapUtils.getInteger(params, "pageNo", 1);
        int onePageNum = MapUtils.getInteger(params, "onePageNum", 10);
        PageHelper.startPage(pageNo, onePageNum);
        Page<CardDomain> menuList = (Page<CardDomain>) cardService.queryListCard(params);
        return new BaseResponse(menuList);
    }
}
