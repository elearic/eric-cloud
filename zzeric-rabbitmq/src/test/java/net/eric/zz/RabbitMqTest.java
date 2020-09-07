package net.eric.zz;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import net.eric.rabbitmq.RabbitMqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: RabbitMqTest.java, v 0.1 2020/3/16 4:05 PM zz_huns Exp $$
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RabbitMqApplication.class})
public class RabbitMqTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void init() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2).
                contentEncoding("UTF-8").build();

        channel.basicPublish("test_exchange_demo","test_queue_demo",properties,"helloworld".getBytes());
    }

    @Test
    public void testCache(){
        redisTemplate.opsForValue().set("123","456");
        System.out.println("131231231");
        String a = redisTemplate.opsForValue().get("123").toString();
        System.out.println("aaaa +++>>>>"+a);
    }


}
