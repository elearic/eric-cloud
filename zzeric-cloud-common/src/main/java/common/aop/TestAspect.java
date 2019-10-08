/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/** 
 * 
  * @author zz_huns  
 * @version Id: TestAspect.java, v 0.1 2019/3/4 11:29 PM zz_huns Exp $$
  */
@Aspect
@Component
public class TestAspect {

    //TODO  定义切点的路径
    @Pointcut("execution(* com..*.*(..))")
    public void poingcut(){};


    @Before("@annotation(common.aop.AspectTag)")
    public void before(){
        System.out.println("自定义切面类TestAspect.前置增强执行了");
    }
    
    @Around("@annotation(common.aop.AspectTag)")
    public void around(ProceedingJoinPoint point){
        System.out.println("自定义切面类TestAspect.around环绕通知开始了");
        try {
            point.proceed();
            System.out.println("目标方法执行了");
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("自定义切面类TestAspect.around环绕通知结束了");
    }

    @After("@annotation(common.aop.AspectTag)")
    public void after(){
        System.out.println("自定义切面类TestAspect.后置增强执行了");
    }

}
