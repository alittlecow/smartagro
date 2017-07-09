package com.sywl.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/9.
 */
public enum  RoleEnum {
    ADMIN(1,"admin"),DISTRIBUTOR(2,"distrubutor"),USER(3,"user"),FINANCIAL(4,"financial");

    private int index ;
    private String name ;

    private RoleEnum( int index, String name){
        this.index = index ;
        this.name = name ;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    //是否包含
    public static boolean contain(String role) {
        for (RoleEnum element : RoleEnum.values()) {
            if (element.getName().equals(role)) {
                return true;
            }
        }
        return false;
    }

}
