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
}
