package net.eric.zz.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *  
 *  * @author zz_huns  
 *  @version Id: ZzericEurekaApplication.java, v 0.1 2019/4/4 12:37 AM zz_huns Exp $$
 */

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication extends CachingConfigurerSupport {

    @Autowired
    private RedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
