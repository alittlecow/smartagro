package com.sywl.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sywl.common.enums.Constants;
import com.sywl.support.BaseResponse;
import com.sywl.utils.RedisUtil;
import com.sywl.web.domain.GoodsDomain;
import com.sywl.web.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author pengxiao
 * @date 2017/7/16
 * 商品管理
 */
@Api("商品管理")
@RestController
@RequestMapping(value = "goods")
public class GoodsController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "商品列表", notes = "商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "用户令牌", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "商品类型", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "商品名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pageNo", value = "页码", required = false, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "onePageNum", value = "每页数量", required = false, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "beginTime", value = "查询开始时间", required = false, dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "endTime", value = "查询结束时间", required = false, dataType = "string")
    })
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public BaseResponse list(Map params) {
        int pageNo = MapUtils.getInteger(params, "pageNo", 1);
        int onePageNum = MapUtils.getInteger(params, "onePageNum", 10);
        PageHelper.startPage(pageNo, onePageNum);
        Page<GoodsDomain> goodsDomainList = (Page<GoodsDomain>) goodsService.queryGoodsByCondition(params);
        return new BaseResponse(goodsDomainList);
    }


    @ApiOperation(value = "新增商品", notes = "新增商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "用户令牌", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "商品类型", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "商品名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "value", value = "商品数值", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "money", value = "商品价格", required = true, dataType = "String"),
    })
    @RequestMapping(value = "add", method = {RequestMethod.POST, RequestMethod.GET})
    public BaseResponse save(@RequestBody Map params) {
        String type = MapUtils.getString(params, "type");
        String name = MapUtils.getString(params, "name");
        String value = MapUtils.getString(params, "value");
        String money = MapUtils.getString(params, "money");
        goodsService.save(type, name, value, money);
        return new BaseResponse();
    }

    @ApiOperation(value = "修改商品", notes = "修改商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "用户令牌", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "商品类型", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "商品名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "value", value = "商品数值", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "money", value = "商品价格", required = false, dataType = "String")
    })
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public BaseResponse update(@RequestBody GoodsDomain goodsDomain) {
        goodsService.update(goodsDomain);
        return new BaseResponse();
    }

    @ApiOperation(value = "删除商品", notes = "删除商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "用户令牌", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "商品id", required = false, dataType = "String")
    })
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResponse delete(@RequestBody String[] ids) {
        List<String> idList = Arrays.asList(ids);
        goodsService.deleteBatch(idList);
        return new BaseResponse();
    }


    @RequestMapping("/info/{id}")
    public BaseResponse info(@PathVariable("id") String id) {
        GoodsDomain goodsDomain = null;
        if (id != null) {
            goodsDomain = goodsService.queryGoodsById(id);
        }
        return new BaseResponse(goodsDomain);
    }
}
