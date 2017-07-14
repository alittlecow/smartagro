package com.sywl.common.enums;

/**
 * @author pengxiao
 * @date 2017/7/11
 * 常量类
 */
public class Constants {
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
        //设备使用时间类型
        DEVICE_USE_TIME(new Byte("2")),
        //账户充值类型
        ACCOUNT_RECHARGE_TIME(new Byte("3"));

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
