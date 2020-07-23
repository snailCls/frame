package com.zzh.logic.utils.redis;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis配置类
 * @Author:snail
 */
public class JedisConfig extends JedisPoolConfig {

    private String address = "127.0.0.1";
    private int port = 6379;
    private int timeout = 10000;
    private String auth;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public JedisConfig() {
        super();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
