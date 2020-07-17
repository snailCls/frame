package zzh.realize.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@ControllerAdvice
public class GlobalExceptionhandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionhandler.class);

    public @ResponseBody String jsonErrorHandler(Exception e) throws Exception {
        logger.error("异常信息 : ", e);
        if(e instanceof BindException) {
            BindException be = (BindException) e;
            if(be.getErrorCount() > 0) {
                return new String("请求地址不存在");
            }else if(e instanceof HttpRequestMethodNotSupportedException) {
                return new String("不支持[" + ((HttpRequestMethodNotSupportedException) e).getMethod() + "]");
            }else if(e instanceof MissingServletRequestParameterException) {
                return new String("参数[" + ((MissingServletRequestParameterException) e).getParameterName() + "]不能为空");
            }else if(e instanceof MethodArgumentTypeMismatchException) {
                String exName = ((MethodArgumentTypeMismatchException) e).getRequiredType().getName();
                return new String("参数[" + ((MethodArgumentTypeMismatchException) e).getName() + "]类型转换出错," +
                        "需要类型为" + exName.substring(exName.lastIndexOf(".") + 1) + ",值为 : " + ((MethodArgumentTypeMismatchException) e).getValue());
            }
        }
        return new String("系统繁忙，请稍后再试");
    }

}
