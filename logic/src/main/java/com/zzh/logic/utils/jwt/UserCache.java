package com.zzh.logic.utils.jwt;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCache implements Serializable {

    private Long id;
    private String AppId;
    private String AccountId;

    public UserCache() {}

    public UserCache(Long id, String AppId, String AccountId) {
        this.id = id;
        this.AppId = AppId;
        this.AccountId = AccountId;
    }

}
