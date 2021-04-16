package com.asiaInfo.demo.common.exception;

import com.asiaInfo.demo.common.bean.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * @author li.shuGuang
 * @date 2021/04/16
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 捕获自定义异常
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public CommonResult handleIapException(MyException e) {
        logger.error("error",e);
        if (e.getErrorMsg() != null) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.failed(e.getMessage());
    }


    /**
     * 捕获程序运行异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult handle(Exception e) {
        logger.error("error",e);
        return CommonResult.failed(e.toString());
    }
}
