package zzh.realize.demo.controller;

import com.zzh.logic.annotation.AuthUser;
import com.zzh.logic.utils.jwt.JWTObject;
import com.zzh.logic.utils.jwt.JWTUtils;
import com.zzh.logic.utils.jwt.UserCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zzh.realize.demo.Vo.UserVo;

@RestController
public class DemoController {

    @GetMapping("/login")
    public UserVo Login(String username, String password) {
        UserVo userVo = new UserVo();
        if(username.equals(password)) {
            JWTObject.JWTPayLoad payLoad = new JWTObject.JWTPayLoad(3);
            payLoad.put("id", "1");
            payLoad.put("appId", "PC");
            payLoad.put("accountId","accountId");
            String token = JWTUtils.getToken(payLoad);
            userVo.setId(1L);
            userVo.setAppId("PC");
            userVo.setAccountId("accountId");
            userVo.setToken(token);
        }
        return userVo;
    }

    @GetMapping("/user")
    public String authUser(@AuthUser UserCache userCache) {
        if(userCache == null) {
            return "验证失败";
        }
        return "验证成功";
    }
}
