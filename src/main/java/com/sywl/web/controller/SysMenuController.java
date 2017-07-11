package com.sywl.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sywl.support.BaseResponse;
import com.sywl.web.domain.SysMenu;
import com.sywl.web.service.SysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        List<SysMenu> menuList = sysMenuService.getUserMenuList(1l);
        return new BaseResponse(menuList);
    }


    /**
     * 所有菜单列表
     */
    @RequestMapping("/menu/list")
    public BaseResponse list(@RequestParam Map<String, Object> params) {
        logger.info("SysMenuController | list  method enter");
        //查询列表数据
        PageHelper.startPage(1, 10);
        Page<SysMenu> menuList = (Page<SysMenu>) sysMenuService.queryList(params);
        logger.info("SysMenuController | list  method quit");
        return new BaseResponse(menuList);
    }

    /**
     * 保存
     */
    @RequestMapping("/menu/save")
    public BaseResponse save(@RequestBody SysMenu menu) {
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
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenu root = new SysMenu();
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
        SysMenu menu = null;
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
    public BaseResponse update(@RequestBody SysMenu menu) {
        logger.info("SysMenuController | update method enter | menu:{}", menu);

        sysMenuService.update(menu);

        logger.info("SysMenuController | update method quit");
        return new BaseResponse();
    }

}
