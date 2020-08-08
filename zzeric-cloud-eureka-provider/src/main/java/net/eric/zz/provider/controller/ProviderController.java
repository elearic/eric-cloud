package net.eric.zz.provider.controller;

import net.eric.zz.provider.controller.aop.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: ProviderController.java, v 0.1 2019/10/5 5:36 PM zz_huns Exp $$
 *
 */
@RestController
@RequestMapping("provider")
public class ProviderController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("baseInfo/{age}")
    @ResponseBody
    private App baseInfo(@PathVariable("age")Long age){
        redisTemplate.opsForValue().set("nihao","redis");

        Object nihao = redisTemplate.opsForValue().get("nihao");
        System.out.println("obfdfsf++"+nihao);

//        线程睡眠，测试客户端的feign超时配置
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("provider-B 调用成功 执行了");
        return new App();
    }
}
