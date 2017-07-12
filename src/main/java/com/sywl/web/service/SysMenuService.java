package com.sywl.web.service;


import com.sywl.common.enums.Constants;
import com.sywl.web.dao.SysMenuMapper;
import com.sywl.web.domain.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;


    public List<SysMenu> queryListParentId(Long parentId) {
        return sysMenuMapper.queryListParentId(parentId);
    }


    public List<SysMenu> getUserMenuList(Long userId) {
        //用户菜单列表
        List<Long> menuIdList = queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }


    public List<Long> queryAllMenuId(Long userId) {
        return sysMenuMapper.queryAllMenuId(userId);
    }


    public List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenu> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }
        List<SysMenu> userMenuList = new ArrayList<SysMenu>();
        for (SysMenu menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }


    public List<SysMenu> queryList(Map<String, Object> map) {
        return sysMenuMapper.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return sysMenuMapper.queryTotal(map);
    }


    public void save(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
    }


    public void update(SysMenu sysMenu) {
        sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
    }


    public SysMenu queryObject(Long menuId) {
        return sysMenuMapper.selectByPrimaryKey(menuId);
    }


    public List<SysMenu> queryNotButtonList() {
        return sysMenuMapper.queryNotButtonList();
    }


    public void deleteBatch(Long[] menuIds) {
        sysMenuMapper.deleteBatch(menuIds);
    }


    /**
     * 获取所有菜单列表
     */
    private List<SysMenu> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenu> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList) {
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();

        for (SysMenu entity : menuList) {
            if (entity.getType() == Constants.MenuType.CATALOG.getValue()) {//目录
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
