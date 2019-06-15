package com.bdth.traceplus.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.bdth.traceplus.annotation.CheckParams;
import com.bdth.traceplus.service.impl.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author weiming.zhu
 * @date 2019/6/15 15:07
 */
@Aspect
@Slf4j
@Component
public class CheckParamsInterceptor {

    private AccountServiceImpl accountService;

    public CheckParamsInterceptor(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    /**
     * 此处的切点是注解的方式，也可以用包名的方式达到相同的效果
     * '@Pointcut("execution(* com.wwj.springboot.service.impl.*.*(..))")'
     */
    @Pointcut("@annotation(com.bdth.traceplus.annotation.CheckParams)")
    public void handle() {
    }

    /**
     * 对参数做验证
     * @param point
     * @return
     * @throws Exception
     */
    @Before("handle()")
    public boolean doBefore(JoinPoint point) throws Exception {
        log.info("======= start checkParam =======");
        Method method = getMethod(point);
        if (null == method) {
            log.info("======= cant get checkParam value =======");
            return false;
        }

        CheckParams logMessge = method.getAnnotation(CheckParams.class);

        Object[] args = point.getArgs();//获取请求参数
        StringBuffer sb = new StringBuffer();
        if (ArrayUtils.isNotEmpty(args)) {
            for (Object arg : args) {
                sb.append(convent(arg));
            }
        }

        String params = sb.toString();//获取所有的参数
        log.info("======= params is " + params + " =======");
        return true;
    }

    @After("handle()")
    public boolean doAfter(JoinPoint point) {
        log.info("======= finish checkParam =======");
        return true;
    }

//    /**
//     * 通过反射获取类的方法后，通过方法名和参数个数来确认方法
//     * 最后获得该操作的message
//     *
//     * @param joinPoint
//     * @return
//     * @throws Exception
//     */
//    public String getContent(JoinPoint joinPoint) throws Exception {
//        String name = joinPoint.getTarget().getClass().getName(); //得到类名
//        String methodName = joinPoint.getSignature().getName();  //得到方法名字
//        Object[] args = joinPoint.getArgs(); //参数集合
//        Class clazz = Class.forName(name);
//        Method[] methods = clazz.getMethods();
//        String message = "";
//        for (Method method : methods) {
//            if (method.getName().equals(methodName)) {
//                Class[] parameterTypes = method.getParameterTypes();
//                if (parameterTypes.length == args.length) {
//                    message = method.getAnnotation(CheckParams.class).message();
//                }
//            }
//        }
//        return message;
//    }

    private String convent(Object args) {
        try {
            return JSONObject.toJSONString(args);
        } catch (Exception e) {
            return "";
        }
    }

    private Method getMethod(JoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();
        try {
            return targetClass.getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
