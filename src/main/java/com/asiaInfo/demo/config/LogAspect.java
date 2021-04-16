package com.asiaInfo.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.*;

import java.util.Date;

/**
 * @description: spring的aop
 * @Aspect 表明是一个切面类
 * @Component 将当前类注入到Spring容器内
 * @Pointcut 切入点，其中execution用于使用切面的连接点。
 * 使用方法：execution(方法修饰符(可选) 返回类型 方法名 参数 异常模式(可选)) ，可以使用通配符匹配字符，*可以匹配任意字符。
 * execution（）	                    表达式的主体；
 * 第一个”*“符号	                    表示返回值的类型任意；
 * com.asiaInfo.demo.controller	    AOP所切的服务的包名，即，我们的业务部分
 * 包名后面的”..“	                    表示当前包及子包
 * 第二个”*“	                        表示类名，*即所有类。此处可以自定义，下文有举例
 * .*(..)	                        表示任何方法名，括号表示参数，两个点表示任何参数类型
 * @Before 在方法前执行
 * @After 在方法后执行
 * @AfterReturning 在方法执行后返回一个结果后执行
 * @AfterThrowing 在方法执行过程中抛出异常的时候执行
 * @Around 环绕通知，就是可以在执行前后都使用，这个方法参数必须为ProceedingJoinPoint，proceed()方法就是被切面的方法，上面四个方法可以使用JoinPoint，JoinPoint包含了类名，被切面的方法名，参数等信息。
 * @author: li.shuGuang
 * @dateTime: 2021/4/15
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
    //@Pointcut("execution(public * com.asiaInfo.demo.controller..*.*(..))")
    //public void LogAspect(){}

    //@Before("LogAspect()")
    //public void doBefore(JoinPoint joinPoint){
    //    log.info("doBefore");
    //}
    //
    //@After("LogAspect()")
    //public void doAfter(JoinPoint joinPoint){
    //    log.info("doAfter");
    //}
    //
    //@AfterReturning("LogAspect()")
    //public void doAfterReturning(JoinPoint joinPoint){
    //    log.info("doAfterReturning");
    //}
    //
    //@AfterThrowing("LogAspect()")
    //public void deAfterThrowing(JoinPoint joinPoint){
    //    log.info("deAfterThrowing");
    //}
    //
    //@Around("LogAspect()")
    //public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable{
    //    log.info("doAround");
    //    return joinPoint.proceed();
    //}


    //@Around("@annotation(logAnnotation)")
    //public Object around(ProceedingJoinPoint joinPoint, LogAnnotation logAnnotation) throws Throwable{
    //    //log.info("测试aop");
    //    System.out.println("方法开始时间是:"+new Date());
    //    Object o = joinPoint.proceed();
    //    System.out.println("方法结束时间是:"+new Date()) ;
    //    return o;
    //}


    //---------------------------------------






    @Pointcut("@annotation(LogAnnotation)")
    public void sendLog() {
    }

    @Before("sendLog() && @annotation(logAnnotation)")
    public void doAfterReturning(JoinPoint joinPoint,LogAnnotation logAnnotation) {
        String methodName = logAnnotation.prefixMark();
        log.info("进入：{} 方法！",methodName);
    }

    @AfterReturning(returning = "ret", pointcut = "sendLog() && @annotation(logAnnotation)")
    public void afterReturning(JoinPoint joinPoint, Object ret, LogAnnotation logAnnotation) {
        Object[] args = joinPoint.getArgs();
        StringBuilder argsStr = new StringBuilder();
        for (Object arg : args) {
            argsStr.append(arg.toString()).append(',');
        }
        String methodName = logAnnotation.prefixMark();

        if (ret != null) {
            log.info("方法：{}执行完毕，参数：{}，返回值：{}",methodName,argsStr.toString(),ret);
        } else {
            log.info("方法：{}执行完毕，参数:{},无返回值！",methodName,argsStr.toString());
        }
    }


}

