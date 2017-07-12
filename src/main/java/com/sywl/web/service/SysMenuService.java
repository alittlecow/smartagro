package com.sywl.web.service;


import com.sywl.common.enums.Constants;
import com.sywl.web.dao.SysMenuMapper;
import com.sywl.web.domain.SysMenuDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;


    public List<SysMenuDomain> queryListParentId(Long parentId) {
        return sysMenuMapper.queryListParentId(parentId);
    }


    public List<SysMenuDomain> getUserMenuList(Long userId) {
        //用户菜单列表
        List<Long> menuIdList = queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }


    public List<Long> queryAllMenuId(Long userId) {
        return sysMenuMapper.queryAllMenuId(userId);
    }


    public List<SysMenuDomain> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuDomain> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }
        List<SysMenuDomain> userMenuList = new ArrayList<SysMenuDomain>();
        for (SysMenuDomain menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }


    public List<SysMenuDomain> queryList(Map<String, Object> map) {
        return sysMenuMapper.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return sysMenuMapper.queryTotal(map);
    }


    public void save(SysMenuDomain sysMenuDomain) {
        sysMenuMapper.insert(sysMenuDomain);
    }


    public void update(SysMenuDomain sysMenuDomain) {
        sysMenuMapper.updateByPrimaryKeySelective(sysMenuDomain);
    }


    public SysMenuDomain queryObject(Long menuId) {
        return sysMenuMapper.selectByPrimaryKey(menuId);
    }


    public List<SysMenuDomain> queryNotButtonList() {
        return sysMenuMapper.queryNotButtonList();
    }


    public void deleteBatch(Long[] menuIds) {
        sysMenuMapper.deleteBatch(menuIds);
    }


    /**
     * 获取所有菜单列表
     */
    private List<SysMenuDomain> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenuDomain> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuDomain> getMenuTreeList(List<SysMenuDomain> menuList, List<Long> menuIdList) {
        List<SysMenuDomain> subMenuList = new ArrayList<SysMenuDomain>();

        for (SysMenuDomain entity : menuList) {
            if (entity.getType() == Constants.MenuType.CATALOG.getValue()) {//目录
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
