package zzh.realize.config;

import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;


/**
 * webMvc配置器
 */
@Component
public class MyWebConfigurer implements WebMvcConfigurer {

    @Resource
    AuthHandlerResolver authHandlerResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        //处理token数据
        resolvers.add(authHandlerResolver);
    }
}