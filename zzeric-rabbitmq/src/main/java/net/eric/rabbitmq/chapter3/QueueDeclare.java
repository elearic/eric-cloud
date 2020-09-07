package net.eric.rabbitmq.chapter3;/**
 * @Author zz_huns
 * @Date 2020/8/11 2:24 下午
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
 *  @version Id: QueueDeclare.java, v 0.1 2020/8/11 2:24 下午 zz_huns Exp $$
 *
 */
public class QueueDeclare {

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
         * 1.无参声明一个队列
         *
         * 不带任何参数的 queueDeclare 方法默认创建一个由RabbitMQ 命名的（类似这种amq-gen-LHQzlgv3GhDov8PIDABOXA名称，这种队列也称为之匿名队列）
         * 排他的、自动删除的、非持久化队列
         */
        channel.queueDeclare();


        /**
         * 2.创建一个持久化、非排他的、非自动删除的队列
         *
         * QUEUE_NAME : 队列的名称
         *
         * durable :  设置是否持久化。为true则设置队列为持久化。持久化的队列会存盘，在服务器重启的时候可以保证不丢失相关信息。
         *
         * exclusive :  设置是否排他。为true则设置队列为排他的。如果一个队列被声明为排他队列，该队列仅对首次声明它的连接可见，
         *              并在连接断开时自动删除。这里需要注意三点：排他队列是基于连接(Connection)可见的，同一个连接的不同信道(Channel)
         *              是可以同时访问同一连接创建的排他队列；"首次"是指如果一个连接已经声明了一个排他队列，其他连接是不允许建立同名的排他队列的，这个与
         *              普通队列不同；即使该队列是持久化的，一旦连接关闭或者客户端退出，该排他队列都会自动删除，这种适用于一个客户端同事发送和读取消息的应用场景。
         *
         * autoDelete : s
         */
        channel.queueDeclare(QUEUE_NAME,
                true,
                false,
                false,
                null);

    }

}
