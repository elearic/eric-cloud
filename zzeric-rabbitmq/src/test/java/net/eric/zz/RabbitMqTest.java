package net.eric.zz;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
@SpringBootTest
@AutoConfigureMockMvc
public class RabbitMqTest {


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


}
