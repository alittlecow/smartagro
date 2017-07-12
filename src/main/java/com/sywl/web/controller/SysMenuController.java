package com.sywl.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sywl.support.BaseResponse;
import com.sywl.web.domain.SysMenuDomain;
import com.sywl.web.service.SysMenuService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author pengxiao
 * @date 2017/7/12
 *  用户菜单管理
 */
@RestController
@RequestMapping("/sys")
public class SysMenuController {
    private static final Logger logger = LoggerFactory.getLogger(SysMenuController.class);
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 用户菜单列表
     */
    @RequestMapping("/menu/user")
    public BaseResponse user() {
        List<SysMenuDomain> menuList = sysMenuService.getUserMenuList(1l);
        return new BaseResponse(menuList);
    }


    /**
     * 所有菜单列表
     */
    @RequestMapping("/menu/list")
    public BaseResponse list(@RequestParam Map params) {
        logger.info("SysMenuController | list  method enter");

        //查询列表分页数据
        int pageNo = MapUtils.getInteger(params, "pageNo", 1);
        int onePageNum = MapUtils.getInteger(params, "onePageNum", 10);
        PageHelper.startPage(pageNo, onePageNum);
        Page<SysMenuDomain> menuList = (Page<SysMenuDomain>) sysMenuService.queryList(params);
        logger.info("SysMenuController | list  method quit");
        return new BaseResponse(menuList);
    }

    /**
     * 保存
     */
    @RequestMapping("/menu/save")
    public BaseResponse save(@RequestBody SysMenuDomain menu) {
        logger.info("SysMenuController | save  method enter | menu:{}", menu);

        sysMenuService.save(menu);

        logger.info("SysMenuController | save  method quit");
        return new BaseResponse();
    }


    /**
     * 选择菜单(添加、修改菜单)
     */
    @RequestMapping("/menu/select")
    public BaseResponse select() {
        logger.info("SysMenuController | select  method enter");
        //查询列表数据
        List<SysMenuDomain> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenuDomain root = new SysMenuDomain();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        logger.info("SysMenuController | select  method quit");

        return new BaseResponse(menuList);
    }

    /**
     * 菜单信息
     */
    @RequestMapping("/menu/info/{menuId}")
    public BaseResponse info(@PathVariable("menuId") Long menuId) {
        logger.info("SysMenuController | info  method enter | menuId:{}", menuId);
        SysMenuDomain menu = null;
        if (menuId != null) {
            menu = sysMenuService.queryObject(menuId);
        }
        logger.info("SysMenuController | info  method quit");

        return new BaseResponse(menu);
    }


    /**
     * 删除
     */
    @RequestMapping("/menu/delete")
    public BaseResponse delete(@RequestBody Long[] menuIds) {
        logger.info("SysMenuController | delete method enter | menuIds:{}", menuIds);

        sysMenuService.deleteBatch(menuIds);

        logger.info("SysMenuController | delete method quit");
        return new BaseResponse();
    }

    /**
     * 修改
     */
    @RequestMapping("/menu/update")
    public BaseResponse update(@RequestBody SysMenuDomain menu) {
        logger.info("SysMenuController | update method enter | menu:{}", menu);

        sysMenuService.update(menu);

        logger.info("SysMenuController | update method quit");
        return new BaseResponse();
    }

}
