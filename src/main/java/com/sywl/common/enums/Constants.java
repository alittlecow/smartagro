package com.sywl.common.enums;

/**
 * @author pengxiao
 * @date 2017/7/11
 * 常量类
 */
public class Constants {
    //常量
    public static String PASSWORD_REGEX = "^[a-zA-Z0-9]{6,20}$"; //密码规则正则表达式

    public static String MOBILE_REGEX = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"; //手机号格式正则表达式

    public static String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  //email格式正则表达式

    public static String DATE_FORMAT_STR = "yyyy-MM-dd";    //日期格式

    public static String DATETIME_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";   //时间格式





    //枚举
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum GoodsType {
        //设备使用
        DEVICE_USE(new Byte("1")),
        //账户充值
        ACCOUNT_RECHARGE(new Byte("2"));

        private Byte value;

        private GoodsType(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }

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
    public enum  RoleEnum {
        ADMIN(1,"admin"),DISTRIBUTOR(2,"distrubutor"),USER(3,"shoper"),FINANCIAL(4,"financial"),
        ID_USER(5,"ID用户"),COMMON_USER(6,"普通用户");

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


}
