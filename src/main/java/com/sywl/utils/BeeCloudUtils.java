package com.sywl.utils;


import cn.beecloud.BCCache;
import cn.beecloud.BeeCloud;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * beecloud 签名验证工具类
 * 用于验证beecloud支付回调的合法性
 */
public class BeeCloudUtils {
    public static final String BEECLOUD_APP_ID = "ce7e9291-1d25-4941-b926-367604b4ff8c";
    public static final String BEECLOUD_TEST_SECRET = "89d77472-ecbd-488b-8c91-1a1b0129158c";
    private static Logger log = LoggerFactory.getLogger(BeeCloudUtils.class);

    public static void initBeeCloud(){
        // TODO: 2017/7/13  暂时没有开通对应的支付渠道,无法获取appSecret 和 MasterSecret
        // 仅能用于测试环境
        BeeCloud.registerApp(BeeCloudUtils.BEECLOUD_APP_ID,BeeCloudUtils.BEECLOUD_TEST_SECRET, null,null);
        BeeCloud.setSandbox(true);
        log.info("BeeCloud init success app_id = {}",BCCache.getAppID());
    }
    public static boolean verifySign(String text,String masterKey,String signature) {

        boolean isVerified = verify(text, signature, masterKey, "UTF-8");
        if (!isVerified) {
            return false;
        }
        return true;
    }


    public static boolean verify(String text, String sign, String key, String inputCharset) {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, inputCharset));
        return mysign.equals(sign);
    }

    public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }




}
