package zzh.realize.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;

/**
 * @author snail
 */
public class JedisConfig {

    @Autowired(required = false)
    private static Jedis jedis;

    private static JedisPool jedisPool;

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(JedisConfig.class);

    public static Jedis getJedis() {
        jedis = new Jedis("192.168.157.131", 6379);
        return jedis;
    }

    public static void closeJedis() {
        jedis.close();
    }

    public static void setRedisObjExpire(String key, String value, int expireSeconds) {
        if(StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        }
        try{
            jedis = getJedis();
            jedis.set(key, value);
            jedis.expire(key, expireSeconds);
        }catch (Exception e) {}
        finally {
            closeJedis();
        }
    }

    @Test
    public void test3() throws InterruptedException {
        setRedisObjExpire("token2", "to", 20);
        while((jedis.ttl("token2")) >= 0) {
            String token2 = jedis.get("token2");
            System.out.println("token2 : " + token2);
            System.out.println("有效期为 ：" + jedis.ttl("token2") + "秒");
            Thread.sleep(2000);
        }
    }


    @Test
    public void test() throws InterruptedException {
        jedis.set("name", "张三");
        String name = jedis.get("name");
        Long t = jedis.ttl("name");
        System.out.println(t);
        //对已经存在的key设置过期时间
        jedis.expire("name", 5);
        while(true) {
            String name2 = jedis.get("name");
            System.out.println(name2);
            System.out.println("有效期为 : " + jedis.ttl("name") + "秒");
            Thread.sleep(2000);
        }
    }

    @Test
    public void test2() throws InterruptedException {
        jedis.set("token", "token令牌");
        String token = jedis.get("token");
        Long t = jedis.ttl("token");
        System.out.println("t : " + t);
        jedis.expire("token", 60*30);
        while(true) {
            String token1 = jedis.get("token");
            System.out.println("token1 : " + token1);
            System.out.println("有效期为 : " + jedis.ttl("token") + "秒");
            Thread.sleep(2000);
        }
    }



}
