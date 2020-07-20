package zzh.realize.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import zzh.realize.result.JsonResponseResult;
import org.springframework.validation.BindException;

/**
 * 全局异常报错信息处理器
 * @author snail
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public @ResponseBody JsonResponseResult jsonErrorHandler(Exception e) throws Exception {
        logger.error("异常信息 : " , e);
        if (e instanceof BindException) {
            BindException be = (BindException) e;
            if(be.getErrorCount() > 0 ) {
                return JsonResponseResult.errorResult(be.getBindingResult().getFieldError().getDefaultMessage());
            } else if(e instanceof NoHandlerFoundException) {
                return JsonResponseResult.errorResult("请求的地址不存在");
            }else {
                return JsonResponseResult.errorResult("系统繁忙，请稍后再试");
            }
        }
        return JsonResponseResult.errorResult("系统繁忙，请稍后再试");
    }

}
