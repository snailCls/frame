package com.zzh.logic.utils;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Base64;

public class Base64Utils {


    /**
     * Base64编码(JDK1.8以后才能使用)
     * @param data 要加密的字符数组
     * @return String 加密后的16进制字符串
     */
    public static String encode_JDK18(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * Base64解码(JDK1.8以后才能使用)
     * @param data 要解密的字符串
     * @return String 解密后的字符串
     * @throws IOException
     */
    public static String  decode_JDK18(String data) throws IOException {
        if(StringUtils.isEmpty(data)){
            return null;
        }
        String encodeToString = new String(Base64.getDecoder().decode(data))  ;
        return URLDecoder.decode(encodeToString,"UTF-8");
    }

    /**
     * 解密
     * @param data
     * @return  返回字节数组
     * @throws IOException
     */
    public static byte[]  decode(String data) throws IOException {
        if(StringUtils.isEmpty(data)){
            return null;
        }
        return Base64.getDecoder().decode(data)  ;
    }

    public static void main(String[] args) throws IOException {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJhcHBJZCI6IlBDIiwiYWNjb3VudCI6IjEifQ==.c6fdd498185eeabf99c85f21a3bc9b4d838cd38a5bda53dfec87b4feb47ed171";
        String[] arr = token.split("\\.");
//        String s = "eyjhbgcioijiuzi1niisinr5cci6ikpxvcj9";
//        String decode = Base64Utils.decode_JDK18(s);
        for(String str:arr) {
            System.out.println(Base64Utils.decode_JDK18(str));
        }
//        System.out.println(decode);
        String image = "{\"id\":\"1\",\"appId\":\"PC\",\"account\":\"1\"}";
        String s1 = Base64Utils.encode_JDK18(image.getBytes());
        System.out.println(s1);

    }

}
