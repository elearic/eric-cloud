package provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: net.cloud.provider.EurekaProviderApplication.java, v 0.1 2019/10/5 2:45 PM zz_huns Exp $$
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderApplication.class, args);
    }

}
