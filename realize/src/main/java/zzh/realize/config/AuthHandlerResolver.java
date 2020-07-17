package zzh.realize.config;

import com.zzh.logic.annotation.AuthUser;
import com.zzh.logic.utils.jwt.JWTObject;
import com.zzh.logic.utils.jwt.JWTUtils;
import com.zzh.logic.utils.jwt.UserCache;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 登录验证处理拦截器
 */
@Component
public class AuthHandlerResolver implements HandlerMethodArgumentResolver {

    /**
     * 如果使用了@AuthUser注解，返回true进行处理，否则返回false，不处理
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(AuthUser.class);
    }

    @Nullable
    @Override
    public Object resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, @Nullable WebDataBinderFactory webDataBinderFactory) throws Exception {

        String token = nativeWebRequest.getHeader("token");
        if(StringUtils.isEmpty(token)) {
            return null;
        }

        JWTObject jwtObject = JWTUtils.parseToken(token);
        JWTObject.JWTPayLoad payLoad = jwtObject.getPayLoad();
        String id = payLoad.getkey("id");
        String AppId = payLoad.getkey("appId");
        String accountId = payLoad.getkey("accountId");
        UserCache userCache = new UserCache(Long.parseLong(id), accountId, AppId);

        return userCache;
    }
}
