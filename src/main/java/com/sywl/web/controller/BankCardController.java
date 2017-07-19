package com.sywl.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sywl.support.BaseResponse;
import com.sywl.web.domain.UserBankCardDomain;
import com.sywl.web.service.BankCardService;
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
 * @author Wangjp
 * @version 1.0.0
 */
@RestController
@RequestMapping("/bankCard")
public class BankCardController {
    @Autowired
    private BankCardService bankCardService;
    /**
     * 查询用户绑定的所有银行卡
     */
    @ApiOperation(value="查询所有绑定的银行卡", notes="查询用户绑定的银行卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "请求参数", required = true, dataType = "Map")
    })
    @RequestMapping(value = "/queryListCard",method = RequestMethod.GET)
    public BaseResponse queryListCard(@RequestParam Map params) {
        //查询列表分页数据
        int pageNo = MapUtils.getInteger(params, "pageNo", 1);
        int onePageNum = MapUtils.getInteger(params, "onePageNum", 10);
        PageHelper.startPage(pageNo, onePageNum);
        String userId = MapUtils.getString(params,"userId","1");
        Page<UserBankCardDomain> menuList = (Page<UserBankCardDomain>) bankCardService.queryBankCardList(userId);
        return new BaseResponse(menuList);
    }

    /**
     * 更新银行卡
     */
    @ApiOperation(value="更新银行卡", notes="更新银行卡")
    @ApiImplicitParam(name = "bankCard", value = "银行卡实体类", dataType = "UserBankCardDomain")
    @RequestMapping(value = "/updateBankCard",method = RequestMethod.POST)
    public BaseResponse updateBankCard(@RequestParam UserBankCardDomain bankCard) {
        bankCardService.updateBankCard(bankCard);
        return new BaseResponse();
    }

    @ApiOperation(value="新增银行卡", notes="更新银行卡")
    @ApiImplicitParam(name = "bankCard", value = "银行卡实体类", dataType = "UserBankCardDomain")
    @RequestMapping(value = "/createBankCard",method = RequestMethod.POST)
    public BaseResponse createBankCard(@RequestParam UserBankCardDomain bankCard,String userId) {
        bankCardService.createBankCard(bankCard,userId);
        return new BaseResponse();
    }
}
