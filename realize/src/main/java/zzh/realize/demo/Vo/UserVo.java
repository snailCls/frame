package zzh.realize.demo.Vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {

    private Long id;
    private String appId;
    private String accountId;
    private String token;

}
