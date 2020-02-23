package app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: FeignService.java, v 0.1 2020/2/23 1:24 AM zz_huns Exp $$
 *
 * FeignCLient
 *
 * name : 指定FeignClient的名称，如果项目使用了Ribbon,name 属性会作为微服务的名称，用于服务发现
 *
 *
 * 此处为了测试，将Feign接口订到了消费端，实际是要定义到服务生产端
 * */

@FeignClient(name = "zzeric-eureka-provider")
public interface FeignService {

    @GetMapping("provider/baseInfo")
    public String baseInfo();

}
