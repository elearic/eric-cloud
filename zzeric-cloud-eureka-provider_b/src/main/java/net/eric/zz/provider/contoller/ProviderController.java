package net.eric.zz.provider.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: ProviderController.java, v 0.1 2019/10/5 5:36 PM zz_huns Exp $$
 *
 */
@Controller
@RequestMapping("provider")
public class ProviderController {

    @GetMapping("baseInfo")
    @ResponseBody
    private String baseInfo(){
//        线程睡眠，测试客户端的feign超时配置
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("provider-B 调用成功 执行了");
        return "provider-B 调用成功";
    }
}
