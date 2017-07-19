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
    //用户id
    public static final String USER_ID = "userId";

    //有效
    public static final String ENABLE = "1";
    //失效
    public static final String DISABLE = "0";

    public static final String SUCCESS = "success";

    public static final String ERROR = "error";





    //枚举
    public enum BasicType {
        //有效
        ENABLE(new Byte("1")),
        //失效
        DISABLE(new Byte("0"));

        private Byte value;

        private BasicType(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }


    //角色类型
    public enum Role {
        ROLE_ROOT("1"), ROLE_DEALER("2"), ROLE_SHOP_KEEPER("3"), ROLE_IDCARD_USER("4"), ROLE_ORDINARY_USER("5");
        private String value;

        private Role(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

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
        //设备使用次数类型
        DEVICE_USE_COUNT(new Byte("1")),
        //账户充值类型
        ACCOUNT_RECHARGE_TIME(new Byte("2"));

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


    public enum PayStatus {
        //待支付
        NEED_PAY(new Byte("0")),
        //支付中
        IN_PAY(new Byte("1")),
        //支付成功
        SUCCESS(new Byte("20")),
        //支付失败
        FAIL(new Byte("21"));

        private Byte value;

        private PayStatus(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }

    public enum PayType {
        ZHIFUBAO(new Byte("0")),
        WECHAT(new Byte("1")),
        YINLIAN(new Byte("2")),
        ACCOUNT(new Byte("3"));

        private Byte value;

        private PayType(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }

    public enum AccountAdjustType {
        //用户充值
        USER_RECHARGE(new Byte("0")),
        //用户消费
        USER_CONSUME(new Byte("1")),
        //分销商结算
        DEALER_SETTLEMENT(new Byte("2"));

        private Byte value;

        private AccountAdjustType(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }


    public enum ApplyCardStatus{
        //提交申请
        INIT(new Byte("0")),
        //1审批失败
        APPLY_FAIL(new Byte("1")),
        //审批成功
        APPLY_SUCCESS(new Byte("2"));

        private Byte value;

        private ApplyCardStatus(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }
    public static void main(String[] args) {
        Byte b1 = new Byte("20");
        Byte b2 = new Byte("20");
    }
}
