package eureka;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 *  
 *  * @author zz_huns  
 *  @version Id: ZzericEurekaApplication.java, v 0.1 2019/4/4 12:37 AM zz_huns Exp $$
 */

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication  extends CachingConfigurerSupport {

    @Autowired
    private RedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

    @Bean
    @SuppressWarnings("rawtypes")
    public RedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer<Object>(Object.class);
    }

    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setValueSerializer(fastJson2JsonRedisSerializer());
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(fastJson2JsonRedisSerializer());
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

//    @PostConstruct
//    private void initGoodsInfo(){
////        try {
////            Long size = redisTemplate.opsForHash().size(CacheKeyConstant.CHILI_GOODS_CACHE_KEY);
////            if (size == 0){
////                List<Goods> goodsList = goodsMapper.selectList(new QueryWrapper<>());
////                Map<Long, List<Goods>> goodsMap = goodsList.stream().collect(Collectors.groupingBy(Goods::getId));
////                redisTemplate.opsForHash().putAll(CacheKeyConstant.CHILI_GOODS_CACHE_KEY,goodsMap);
////            }
////        }catch (Exception e){
////            log.error("商品数据初始化redis失败，exception:{}",e);
////        }
//
//
//
//        Pojo pojo1 = new Pojo("1","andy",1);
//        Pojo pojo2 = new Pojo("2", "ele",2);
//        Pojo pojo3 = new Pojo("3", "aric", 3);
//
//        Map<String,String> map = new HashMap<>();
//        map.put(pojo1.getId(), JSON.toJSONString(pojo1));
//        map.put(pojo2.getId(),JSON.toJSONString(pojo2));
//        map.put(pojo3.getId(),JSON.toJSONString(pojo3));
//        redisTemplate.opsForHash().putAll("hashPojo",map);
//
//    }
//
//
//    @Data
//    @ToString
//    public static class Pojo{
//
//        public Pojo(String id,String name,Integer age){
//            this.id =id;
//            this.name = name;
//            this.age = age;
//        }
//
//        private String id;
//
//        private String name;
//
//        private Integer age;
//    }
}
