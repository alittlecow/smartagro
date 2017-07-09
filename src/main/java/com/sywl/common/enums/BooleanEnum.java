package com.sywl.common.enums;

/**
 * Created by Administrator on 2017/7/9.
 */
public enum BooleanEnum {
    FALSE(1,"0"),TRUE(2,"1");

    private int index ;
    private String name ;

    private BooleanEnum(int index, String name){
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
    public static boolean contain(String str) {
        for (BooleanEnum element : BooleanEnum.values()) {
            if (element.getName().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
