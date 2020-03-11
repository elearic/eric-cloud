package net.eric.zz.consumer;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: EurekaConsumerApplication.java, v 0.1 2019/10/5 5:31 PM zz_huns Exp $$
 *
 */
@SpringBootApplication
@EnableEurekaClient
@SpringBootConfiguration
@EnableFeignClients
public class EurekaConsumerApplication {


    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }


    @Bean
    @LoadBalanced
    public RestTemplate initRestTemplate(){
        return new RestTemplate();
    }


    @Bean
    public IRule getRibbonRule(){
        return new RetryRule();
    }

}
