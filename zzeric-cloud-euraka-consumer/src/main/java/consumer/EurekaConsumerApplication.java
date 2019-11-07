package consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: EurekaConsumerApplication.java, v 0.1 2019/10/5 5:31 PM zz_huns Exp $$
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class EurekaConsumerApplication {


    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }


}
