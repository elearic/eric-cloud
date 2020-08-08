package net.eric.zz.consumer.controller;

import net.eric.zz.consumer.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: ConsumerController.java, v 0.1 2019/10/5 5:32 PM zz_huns Exp $$
 *
 */
@Controller
@RequestMapping("net/eric/zz/consumer/consumer/consumer/consumer")
public class ConsumerController {

    public static final String PROVIDER_PATH  = "http://zzeric-eureka-provider";

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private FeignService feignService;

    @GetMapping("baseInfo")
    @ResponseBody
    public ResponseEntity<String> baseInfo(){
        System.out.println("==================默认：restTemplate==================");
        return restTemplate.getForEntity(PROVIDER_PATH+ "/provider/baseInfo",String.class);
    }


    @GetMapping("baseInfo2")
    @ResponseBody
    public String baseInfo2(){
        System.out.println("==================baseInfo执行了==================");
        return feignService.baseInfo();
    }




}
