package com.asiaInfo.demo.controller;

import com.asiaInfo.demo.common.aspect.LogAnnotation;
import com.asiaInfo.demo.common.bean.ErrorCode;
import com.asiaInfo.demo.common.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 从pulsar的topic中获取日志，经过指定规则区分后存放到不同topic中
 * @author: li.shuGuang
 * @dateTime: 2021/4/14
 */
@RestController
@RequestMapping("/asiaInfo")
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);


    //@Autowired
    //DistinctionLogService distinctionLogService;

    /**
     * @Description: 1. 测试AOP功能
     * @Author: li.shuguang
     * @Date: 2021/4/15
     */
    @LogAnnotation(methodName = "测试AOP-doBefore功能")
    @GetMapping("/index")
    public String index(int data) {

        if (data / 2 == 0) {
            throw new MyException("抛出自定异常");
        }else if(data == 2){
            //抛出运行时异常
            int res = 3 / 0;
        }
        return "hello aop";
    }

    @GetMapping("/index2")
    public String index2() {
        System.out.println("方法2执行");
        return "hello aop2";
    }


    ///**
    // * @Description:
    // * @Param: * @param null :
    // * @Return: * @return : null
    // * @Author: li.shuguang
    // * @Date: 2021/4/14
    // */
    //@PostMapping("/demo")
    //public String demo(String data) {
    //    log.info("进入：区分日志方法，参数：{}",data);
    //    distinctionLogService.distinctionLogComplete(data);
    //    return "";
    //}


}