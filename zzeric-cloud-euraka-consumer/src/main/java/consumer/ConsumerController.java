package consumer;

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
@RequestMapping("/consumer")
public class ConsumerController {

    public static final String PROVIDER_PATH  = "http://localhost:8021/provider";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("baseInfo")
    @ResponseBody
    public ResponseEntity<String> baseInfo(){
        return restTemplate.getForEntity(PROVIDER_PATH+"/baseInfo",String.class);
    }
}
