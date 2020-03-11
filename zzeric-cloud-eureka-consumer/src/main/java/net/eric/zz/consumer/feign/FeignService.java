package net.eric.zz.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 此处为了测试，将Feign接口订到了消费端，实际是要定义到服务生产端
 *  
 *  * @author zz_huns  
 *  @version Id: FeignService.java, v 0.1 2020/2/23 1:24 AM zz_huns Exp $$
 *
 * FeignCLient
 *
 * name : 指定FeignClient的名称，如果项目使用了Ribbon,name 属性会作为微服务的名称，用于服务发现
 *
 * url:  一般用于调试,可以手动指定 @FeignClient调用的地址
 *
 * decode404：当发生404错误时，如果该字段为true, 会调用decoder进行解码，否则抛出FeignException
 *
 * configuration： Feign配置类，可以自定义Feign的Encoder,Decoder,LogLevel,Contract.
 *
 * fallback: 定义容错的处理类，当调用远程接口失败或者超时时，会调用对应接口的容错逻辑，fallback指定的类必须实现
 *           @FeignClient 标记的接口
 *
 * fallbackFactory: 工厂类，用于生成fallback类示例。通过这个属性我们可以实现每个接口通用的容错逻辑，减少重复的代码。
 *
 * path : 定义当前FeignClient 的统一前缀
 *
 *
 * */

@FeignClient(name = "zzeric-eureka-provider")
public interface FeignService {

    @GetMapping("provider/baseInfo")
    public String baseInfo();

}
