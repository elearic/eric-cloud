package consumer.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: ConsumerServiceImpl.java, v 0.1 2019/11/7 1:12 AM zz_huns Exp $$
 *
 */
@Service
public class ConsumerServiceImpl implements ConsumerService{

    public static final String PROVIDER_PATH  = "http://localhost:8021/provider";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "baseInfoFallBack")
    public ResponseEntity<String> baseInfo() {
        return restTemplate.getForEntity(PROVIDER_PATH+"/baseInfo",String.class);
    }


    private ResponseEntity<String> baseInfoFallBack(){
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
        return responseEntity;
    }
}
