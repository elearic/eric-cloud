package provider;

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
        return "hello world!!!";
    }
}
