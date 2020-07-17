package com.zzh.logic.utils.jwt;

import java.util.HashMap;
import java.util.Map;

/**
 * token中的3段数据
 */
public class JWTObject {

    private JWTObject.JWTHead jwtHead;
    private JWTObject.JWTPayLoad payLoad;
    private JWTObject.JWTSign sign;

    /**
     * token中的头部
     */
    public static class JWTHead {
        //类型(JWT)
        private String typ;
        //加密算法（HS256）
        private String alg;

        public JWTHead(String typ, String alg) {
            this.typ = typ;
            this.alg = alg;
        }

        public String getTyp() {
            return typ;
        }

        public void setTyp(String typ) {
            this.typ = typ;
        }

        public String getAlg() {
            return alg;
        }

        public void setAlg(String alg) {
            this.alg = alg;
        }
    }

    /**
     * token中数据有效载体
     */
    public static class JWTPayLoad {
       private Map<String, String> payLoad;

       public JWTPayLoad() {}

       public JWTPayLoad(int caps) {
           this.payLoad = new HashMap(caps);
       }

       public JWTPayLoad(Map<String,String> payLoad) {
           this.payLoad = payLoad;
       }

       public String getkey(String key) {
           return payLoad.get(key);
       }

       public void put(String key, String value) {
           this.payLoad.put(key, value);
       }

        public Map<String, String> getPayLoad() {
            return payLoad;
        }

        public void setPayLoad(Map<String, String> payLoad) {
            this.payLoad = payLoad;
        }
    }

    /**
     * token中最后部分的标识签证
     */
    public static class JWTSign {
        private String sign;

        public JWTSign() {}

        public JWTSign(String sign) {
            this.sign = sign;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }



    public JWTHead getJwtHead() {
        return jwtHead;
    }

    public void setJwtHead(JWTHead jwtHead) {
        this.jwtHead = jwtHead;
    }

    public JWTPayLoad getPayLoad() {
        return this.payLoad;
    }

    public void setPayLoad(JWTPayLoad payLoad) {
        this.payLoad = payLoad;
    }


    public JWTSign getSign() {
        return sign;
    }

    public void setSign(JWTSign sign) {
        this.sign = sign;
    }

}
