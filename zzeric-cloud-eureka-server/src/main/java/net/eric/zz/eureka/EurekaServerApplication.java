package net.eric.zz.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  
 *  * @author zz_huns  
 *  @version Id: ZzericEurekaApplication.java, v 0.1 2019/4/4 12:37 AM zz_huns Exp $$
 */

@EnableEurekaServer
@SpringBootApplication
@Controller
public class EurekaServerApplication {


    @Autowired
    private  RedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);

    }

    @GetMapping("eureka/test")
    public void test(){
        System.out.println("!11111");
        redisTemplate.opsForValue().set("nihao","redis");
    }

}
