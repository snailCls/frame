package zzh.realize.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author snail
 */
public class MyHandlerInterceptor implements HandlerInterceptor {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(MyHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long reqTime = System.currentTimeMillis();
        setLogtraceId(request, reqTime);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void setLogtraceId(HttpServletRequest request, long reqTime) {
        String traceId = "";
        String outTradeNo = request.getParameter("outTradeNo");
        String tradeNo = request.getParameter("tradeNo");
        String id = request.getParameter("id");
        if(!StringUtils.isEmpty(traceId)) {
            traceId = outTradeNo + "" + "abc";
        }else if(!StringUtils.isEmpty(id)) {
            traceId = reqTime + "-" + id;
        }else {
            traceId = 1234 + "";
        }
        MDC.put("traceId", traceId);
    }
}
