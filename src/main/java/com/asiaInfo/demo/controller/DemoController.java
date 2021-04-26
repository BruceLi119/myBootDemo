package com.asiaInfo.demo.controller;

import com.asiaInfo.demo.common.aspect.LogAnnotation;
import com.asiaInfo.demo.common.bean.ErrorCode;
import com.asiaInfo.demo.common.exception.MyException;
import com.asiaInfo.demo.listener.MyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.Map;

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
    @Value("${application.message:Hello World}")
    private String message ;

    @GetMapping("/login")
    public String welcome(String username, String password) {
        log.info("登录方法，用户名：{}，密码：{}",username,password);
        return "welcome";
    }

    @RequestMapping("/login")
    public Object foo() {
        log.info("打印日志----------------------");
        return  "login";
    }

    @RequestMapping("/index")
    public Object index(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("zxc", "zxc");
        return  "index";
    }

    @RequestMapping("/online")
    public Object online() {
        return  "当前在线人数：" + MyListener.online + "人";
    }

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
        log.info("方法2执行");
        return "hello aop2";
    }

    /**
     * @Description: 测试给指定文件移动位置
     * @Author: li.shuGuang
     * @dateTime: 2021/4/26
     */
    //public static void main(String[] args) {
    //    File source=new File("D:\\test.txt");
    //
    //    File target=new File("D:\\test\\test.txt");
    //
    //    source.renameTo(target);
    //}

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