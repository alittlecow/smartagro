package com.sywl.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class HttpUtils {
	
	private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
	
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int SO_TIMEOUT = 10000;

    private HttpClient httpClient;

    public HttpUtils() {
        this(SO_TIMEOUT,CONNECTION_TIMEOUT);
    }

    public HttpUtils(int connectTimeout, int readTimeout) {
        httpClient = new HttpClient();
        httpClient.getParams().setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, readTimeout);
        httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT, connectTimeout);
    }

    /**
     * 向一个http地址发送一个post请求
     *
     * @param uri        http地址
     * @param paramsMap  参数信息
     * @param headersMap 头部信息
     * @return 返回相应内容
     */
    public String sendPost(String uri, Map<String, String> headersMap, Map<String, String> paramsMap) throws Exception {

        PostMethod method = new PostMethod(uri);
        method.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");

        if (paramsMap != null && !paramsMap.isEmpty()) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                method.setParameter(entry.getKey(), entry.getValue());
            }
        }

        //向请求中加入头部信息
        if (headersMap != null && !headersMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                method.addRequestHeader(entry.getKey(), entry.getValue());
            }
        }

        return sendMethod(method);
    }


    public String sendPost(String uri, Map<String, String> headersMap, String contentType, String body) {

        PostMethod method = new PostMethod(uri);

        //向请求中加入头部信息
        if (headersMap != null && !headersMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                method.addRequestHeader(entry.getKey(), entry.getValue());
            }
        }
        try {
            method.setRequestEntity(new StringRequestEntity(body,contentType,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return  sendMethod(method);
        
    }

    /**
     * 发送http请求
     *
     * @param method HttpMethodBase
     * @return 返回相应内容
     */
    private String sendMethod(HttpMethodBase method) {
    	Long start = System.currentTimeMillis();
    	log.info("send post start url:{} ", method.getHostConfiguration().toString());
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        try {
            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("http status is:"+ method.getStatusLine());
            }
            return org.apache.commons.io.IOUtils.toString(method.getResponseBodyAsStream(), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
        	Long end = System.currentTimeMillis();
            log.info("send post end url:{},host time:{}ms", method.getHostConfiguration().toString() , end - start );
            // 释放连接
            method.releaseConnection();
        }

    }

    public static void main(String[] args) {
		System.out.println(876/1000);
	}
}
