package com.sywl.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;
/**
 * Created by zhanglj on 2017/7/8.
 */

public class TokenUtils {
	
	public static String getJWTString(String userName,Date expires,Key key){
		if (userName == null) {
		throw new NullPointerException("null username is illegal");
		}

		if (expires == null) {
		throw new NullPointerException("null expires is illegal");
		}
		if (key == null) {
		throw new NullPointerException("null key is illegal");
		}
		//用签名算法HS256和私钥key生成token
		SignatureAlgorithm signatureAlgorithm =SignatureAlgorithm.HS256;
		String jwtString = Jwts.builder()
		.setIssuer("Jersey-Security-Basic")//设置发行人
		.setSubject(userName)//设置抽象主题
		.setAudience("user")//设置角色
		.setExpiration(expires)//过期时间
		.setIssuedAt(new Date())//设置现在时间
		.setId("1")//版本1
		.signWith(signatureAlgorithm,key)
		.compact();
		return jwtString;
		}

}
