package app.consumer;

import app.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: ConsumerController.java, v 0.1 2019/10/5 5:32 PM zz_huns Exp $$
 *
 */
@Controller
@RequestMapping("consumer")
public class ConsumerController {

    public static final String PROVIDER_PATH  = "http://zzeric-eureka-provider";

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private FeignService feignService;

    @GetMapping("baseInfo")
    @ResponseBody
    public ResponseEntity<String> baseInfo(){
        System.out.println("==================baseInfo执行了==================");
        return restTemplate.getForEntity(PROVIDER_PATH+"/provider/baseInfo",String.class);
    }


    @GetMapping("baseInfo2")
    @ResponseBody
    public String baseInfo2(){
        System.out.println("==================baseInfo2执行了==================");
        return feignService.baseInfo();
    }


    @GetMapping("baseInfo3")
    @ResponseBody
    public ResponseEntity<String> baseInfo3(){
        System.out.println("==================baseInfo3执行了==================");
        return new ResponseEntity<String>(feignService.baseInfo(), HttpStatus.OK);
    }

}
