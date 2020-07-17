package com.zzh.logic.utils.jwt;


import com.alibaba.fastjson.JSONObject;
import com.zzh.logic.utils.Base64Utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;

/**
 * jwt验证工具
 */
public class JWTUtils {

    private static final String JWT_TOKEN_KEY = "token_sha256_secret";

    /**
     * 获取token值
     */
    public static String getToken(JWTObject.JWTPayLoad payLoad) {
        if( payLoad == null ) {
            payLoad = new JWTObject.JWTPayLoad(1);
        }
        String header = Base64Utils.encode_JDK18(JSONObject.toJSONBytes(new JWTObject.JWTHead("JWT", "HS256")));
        String payload = Base64Utils.encode_JDK18(JSONObject.toJSONBytes(payLoad));
        StringBuilder token = new StringBuilder(header);
        token.append(".").append(payload);
        //HS256加密
        String sign = encodeHS256(token.toString());
        token.append(".").append(sign);
        return token.toString();
    }

    /**
     * 解析token
     */
    public static JWTObject parseToken(String token) {
        System.out.println("token : " + token);
        String[] tokenArray = token.split("\\.");
        if(tokenArray.length != 3) {
            System.out.println("tokenArray 为空");
            return null;
        }else {
            JWTObject obj = new JWTObject();
            try{
                String header = Base64Utils.decode_JDK18(tokenArray[0]);
                System.out.println("head : " + header);
                obj.setJwtHead(JSONObject.parseObject(header, JWTObject.JWTHead.class));
                String payload = Base64Utils.decode_JDK18(tokenArray[1]);
                System.out.println("payload : " + payload);
                obj.setPayLoad(JSONObject.parseObject(payload, JWTObject.JWTPayLoad.class));
                obj.setSign(new JWTObject.JWTSign(tokenArray[2]));
                return obj;
            }catch (IOException e) {
            return null;
            }
        }
    }

    /**
     * 获取(1)header、(2)payload、(3)sign
     * @param token
     * @param id
     * @return
     */
    public static String getTokenInfoById(String token, int id) {
        String[] tokenArray = token.split("\\.");
        if(tokenArray.length != 3) {
            return null;
        }
        return tokenArray[id];
    }

//    /**
//     * 获取header
//     */
//    public static String getHeader(String token) {
//        String[] tokenArray = token.split("\\.");
//        if(tokenArray.length != 3) {
//            return null;
//        }
//        return tokenArray[0];
//    }
//
//    /**
//     * 获取payload
//     */
//    public static String getPayLoad(String token) {
//       String[] tokenArray = token.split("\\.");
//        if(tokenArray.length != 3) {
//            return null;
//        }
//        return tokenArray[1];
//    }
//
//    /**
//     * 获取sign
//     */
//    public static String getSign(String token) {
//        String[] tokenArray = token.split("\\.");
//        if(tokenArray.length != 3) {
//            return null;
//        }
//        return tokenArray[2];
//    }


    /**
     * HS256加密
     */
    public static String encodeHS256(String data) {
        try{
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(JWT_TOKEN_KEY.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secretKey);
            String hash = byteArrayToHexString(sha256_HMAC.doFinal(data.getBytes()));
            return hash;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字节数组转换成16进制字符串(小写）
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        String stmp;
        for(int i = 0; bytes!=null&&i<bytes.length;i++) {
            stmp = Integer.toHexString(bytes[i] & 0XFF);
            if(stmp.length() == 1) {
                builder.append('0');
            }
            builder.append(stmp);
        }
        return builder.toString().toLowerCase();
    }

}
