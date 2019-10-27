package consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: Configuration.java, v 0.1 2019/10/5 6:06 PM zz_huns Exp $$
 *
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public RestTemplate initRestTemplate(){
        return new RestTemplate();
    }
}
