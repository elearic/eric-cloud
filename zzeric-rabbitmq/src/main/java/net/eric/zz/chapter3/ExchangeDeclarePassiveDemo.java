package net.eric.zz.chapter3;/**
 * @Author zz_huns
 * @Date 2020/8/10 11:12 下午
 * @Version 1.0
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: ExchangeDeclarePassiveDemo.java, v 0.1 2020/8/10 11:12 下午 zz_huns Exp $$
 *
 */
public class ExchangeDeclarePassiveDemo {
    private static final String EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routingkey_demo";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("root");
        factory.setPassword("passwd");
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();

        /**
         * exchangeDelcarePassive 用来检测相应的交换器是否存在，如果存在则正常返回。
         */
//        channel.exchangeDeclarePassive(EXCHANGE_NAME);

        /**
         * 如果不存在 则抛出 404 channel exception ，同事ChanneL 也会被关闭
         */
        channel.exchangeDeclarePassive("not_exist_exchange_demo");
    }
}
