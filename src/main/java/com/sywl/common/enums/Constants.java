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
        DEVICE_USE(1),
        //账户充值
        ACCOUNT_CHARGE(2);

        private int value;

        private GoodsType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
