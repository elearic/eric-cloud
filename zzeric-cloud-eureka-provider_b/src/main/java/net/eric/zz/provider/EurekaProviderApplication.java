package net.eric.zz.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: net.cloud.net.eric.zz.provider.EurekaProviderApplication.java, v 0.1 2019/10/5 2:45 PM zz_huns Exp $$
 *
 */
@SpringBootApplication
@EnableEurekaClient
/**
 * 此注册会在程序启动时，进行包扫描，扫描所有带@FeignClient 的注解的类并进行处理
 */
@EnableFeignClients
public class EurekaProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderApplication.class, args);
    }

}
