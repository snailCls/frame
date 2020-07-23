package com.zzh.logic.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;

/**
 * Jedis缓存操作
 */
@Component
public class JedisCache {

    private static JedisPool jedisPool = null;

    @Autowired(required = false)
    private JedisConfig jedisConfig;

    private static final Logger logger = LoggerFactory.getLogger(JedisCache.class);


    @PostConstruct
    public void init() {
        if(jedisConfig == null) {
            logger.info("jedisConfig为空");
        }else {
            jedisPool = new JedisPool(jedisConfig, jedisConfig.getAddress(), jedisConfig.getPort(), jedisConfig.getTimeout(),jedisConfig.getAuth());
            logger.info("jedis初始化完成");
        }
    }



    /**
     * 上传到jedis
     */
    public static void setRedisObj(String key, String value, int expiredTime) {
        if(StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return ;
        }
        Jedis jedis = JedisCache.getJedis();
       try{
           jedis.set(key, value);
           jedis.expire(key, expiredTime);
       }catch (Exception e ) {
           logger.error("缓存一个对象 并且设置缓存时间异常：" + e);
       } finally {
           jedis.close();
       }
    }

    /**
     * 获取redis上的数据
     */
    public static String getRedisObj(String key) {
        Jedis jedis = JedisCache.getJedis();
        String value = null;
        try{
             value = jedis.get(key);
        }catch (Exception e) {
            logger.error("redis查询异常 : " + e);
        }finally {
            jedis.close();
        }
        return value;
    }

    /**
     * 删除redis上指定key的值
     */
    public static boolean deleteRedisObj(String key) {
        if(StringUtils.isEmpty(key)) {
            return false;
        }
        Jedis jedis = JedisCache.getJedis();
        try{
            Long count = jedis.del(key);
            return (count != null && count > 0);
        }catch (Exception e) {
            logger.error("redis删除异常 : " + e);
        }finally {
            jedis.close();
        }
        return false;
    }


     /*。。。。。。。。。。。。。。。。分布式锁代码。。。。。。。。。。。。。。。。*/

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * 尝试获取分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间 单位(秒)
     * @return 是否获取成功
     * 第一个为key，我们使用key来当锁，因为key是唯一的。
    第二个为value，我们传的是requestId，很多童鞋可能不明白，有key作为锁不就够了吗，为什么还要用到value？原因就是我们在上面讲到可靠性时，分布式锁要满足第四个条件解铃还须系铃人，通过给value赋值为requestId，我们就知道这把锁是哪个请求加的了，在解锁的时候就可以有依据。requestId可以使用UUID.randomUUID().toString()方法生成。
    第三个为nxxx，这个参数我们填的是NX，意思是SET IF NOT EXIST，即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；
    第四个为expx，这个参数我们传的是PX，意思是我们要给这个key加一个过期的设置，具体时间由第五个参数决定。
    第五个为time，与第四个参数相呼应，代表key的过期时间。暂时不提供手动解锁的代码，请把过期时间设置短一点
     */

    /*
    public static boolean tryGetDistributedLock( String lockKey, String requestId, int expireTime) {
        Jedis jedis = getJedis();
        try {
            String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            logger.error("redis异常 : ", e);
        } finally {
            jedis.close();
        }
        return false;
    }
    */


    public static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            }
        } catch (Exception e) {
            logger.error("获取redis资料连接异常 : ", e);
        }
        return null;
    }

}
