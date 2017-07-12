package com.sywl.web.dao;


import com.sywl.web.domain.SysMenuDomain;

import java.util.List;
import java.util.Map;

public interface SysMenuMapper {

    int insert(SysMenuDomain record);


    SysMenuDomain selectByPrimaryKey(Long menuId);


    int updateByPrimaryKeySelective(SysMenuDomain record);


    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuDomain> queryListParentId(Long parentId);


    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);


    List<SysMenuDomain> queryList(Map<String, Object> map);

    /**
     * 总条数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuDomain> queryNotButtonList();


    void deleteBatch(Long[] menuIds);
}