/**
 *
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package aop;


import common.aop.AspectTag;

/** 
 * 
  * @author zz_huns  
 * @version Id: AopTest.java, v 0.1 2019/3/4 11:58 PM zz_huns Exp $$
  */
public class AopTest {

    public static void main(String[] args){
        AopTest aopTest = new AopTest();
        aopTest.handleMethod();
    }


    @AspectTag
    public void handleMethod(){
        System.out.println("================核心方法执行了================");
    }
}
