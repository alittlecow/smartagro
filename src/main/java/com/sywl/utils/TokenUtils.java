package com.sywl.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class TokenUtils {

    public static String getJWTString(String tel, Date expires, Key key) {
        if (tel == null) {
            throw new NullPointerException("null username is illegal");
        }

        if (expires == null) {
            throw new NullPointerException("null expires is illegal");
        }
        if (key == null) {
            throw new NullPointerException("null key is illegal");
        }
        //用签名算法HS256和私钥key生成token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        String jwtString = Jwts.builder()
                .setIssuer("Jersey-Security-Basic")//设置发行人
                .setSubject(tel)//设置抽象主题
                .setAudience("user")//设置角色
                .setExpiration(expires)//过期时间
                .setIssuedAt(new Date())//设置现在时间
                .setId("1")//版本1
                .signWith(signatureAlgorithm, key)
                .compact();
        return jwtString;
    }

    public static void parseJWT(String jwt, Key key) {
        Claims claims = Jwts.parser().setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
    }


    public static void main(String[] args) {
        Key key = MacProvider.generateKey(SignatureAlgorithm.HS512);
        Key key2 = MacProvider.generateKey(SignatureAlgorithm.HS512);
        System.out.println(key == key2);
        String tel = "13776540149";
        Date expiry = DateUtils.getExpiryDate(100);

        String jwt = getJWTString(tel, expiry, key);
        System.out.println(jwt);
        parseJWT(jwt, key);

    }
}
