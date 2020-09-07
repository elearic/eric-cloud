package net.eric.rabbitmq.chapter3;/**
 * @Author zz_huns
 * @Date 2020/8/10 10:39 下午
 * @Version 1.0
 */

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *  exchangeDeclare 方法详解
 *  
 *  * @author zz_huns  
 *  @version Id: ExchangeDeclareDemo.java, v 0.1 2020/8/10 10:39 下午 zz_huns Exp $$
 *
 */
public class ExchangeDeclareDemo {
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
         * 这个方法的返回值是AMQP.Exchange.DeclareOK ,用来标识成功声明了一个交换器
         *
         * EXCHANGE_NAME : 交换器的名称
         *
         * type : 交换器的类型 ，常见的如 fanout、direct、topic
         *
         * durable : 设置是否持久化。durable 设置为true标识持久化，反之是非持久化。
         *           持久化可以将交换器存入磁盘，在服务器重启的时候不会丢失相关信息。
         *
         * autoDelete : 设置是否删除。autoDelete 设置为true则标识自动删除。自动删除的前提是
         *              至少有一个队列或者交换器与这个交换器绑定，之后所有与这个交换器绑定的
         *              队列或者交换器都与此解绑。
         *              注意：
         *              不能错误地把这个参数理解为"当与此交换器连接的客户端都断开时，RabbitMQ会自动删除本交换器"
         *
         *  internal : 设置是否是内置的。如果设置为true,则标识是内置的交换器，客户端程序无法直接发送消息到这个交换器中，
         *              只能通过交换器路由到交换器这种方式。
         *
         *  argument : 其他一些结构化参数，比如alternate-exchange
         *
         */
        AMQP.Exchange.DeclareOk declareOk = channel.exchangeDeclare(
                EXCHANGE_NAME,
                "direct",
                true,
                false,
                false,
                null);
    }
}
