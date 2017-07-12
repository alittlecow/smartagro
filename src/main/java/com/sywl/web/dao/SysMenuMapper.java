package com.sywl.web.dao;


import com.sywl.web.domain.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuMapper {

    int insert(SysMenu record);


    SysMenu selectByPrimaryKey(Long menuId);


    int updateByPrimaryKeySelective(SysMenu record);


    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(Long parentId);


    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);


    List<SysMenu> queryList(Map<String, Object> map);

    /**
     * 总条数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();


    void deleteBatch(Long[] menuIds);
}