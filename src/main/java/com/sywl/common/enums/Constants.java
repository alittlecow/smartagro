package com.sywl.common.enums;

/**
 * @author pengxiao
 * @date 2017/7/11
 * 常量类
 */
public class Constants {
    //用户id
    public static final String USER_ID = "userId";

    //有效
    public static final String ENABLE = "1";
    //失效
    public static final String DISABLE = "0";

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

    public static void main(String[] args) {
        Byte b1 = new Byte("20");
        Byte b2 = new Byte("20");
    }
}
