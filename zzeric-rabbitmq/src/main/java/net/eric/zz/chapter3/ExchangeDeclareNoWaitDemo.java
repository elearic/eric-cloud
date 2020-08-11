package net.eric.zz.chapter3;/**
 * @Author zz_huns
 * @Date 2020/8/10 10:51 下午
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
 *  @version Id: ExchangeDeclareNoWait.java, v 0.1 2020/8/10 10:51 下午 zz_huns Exp $$
 *
 */
public class ExchangeDeclareNoWaitDemo {
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
        //创建一个持久化、非排他的、非自动删除的交换器

        /**
         * 这个 exchangeDeclareNoWait 比 exchangeDeclare 多设置了一个nowait参数，这个nowait参数指的是AMQP中
         * Exchange.Declare命令的参数，意思是不要要服务器返回去，注意这个方法的返回值是void，而普通的exchangeDeclare 方法
         * 的返回值是AMQP.Exchange.DeclareOk. 意思是在客户端申明了一个交换器之后，需要等待服务器的返回去(服务器会返回Exchange.Declare-Ok 这个AMQP命令)
         *
         * 针对"ExchangeDeclareNoWait 不需要服务器任何返回值"这一点，考虑这样一种情况，在声明完一个交换器之后
         * （实际服务器还并未完成交换器的创建），那么此时客户端紧接着使用这个交换器，必然会发生异常。如果没有特殊的缘由和应用场景，并不建议使用这个方法
         */
        channel.exchangeDeclareNoWait(EXCHANGE_NAME,"direct",true,false,false,null);


    }
}
