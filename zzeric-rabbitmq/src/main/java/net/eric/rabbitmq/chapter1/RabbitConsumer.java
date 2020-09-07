package net.eric.rabbitmq.chapter1;/**
 * @Author zz_huns
 * @Date 2020/8/9 12:29 下午
 * @Version 1.0
 */

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 *  1-2
 *  * @author zz_huns  
 *  @version Id: RabbitConsumer.java, v 0.1 2020/8/9 12:29 下午 zz_huns Exp $$
 *
 */
public class RabbitConsumer {

    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Address[] addresses = new Address[]{new Address(IP_ADDRESS, PORT)};
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUsername("root");
        factory.setPassword("passwd");

        //这里的连接方式与生产者的demo略有不同，注意辨别区别
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        final Channel channel = connection.createChannel();
        //设置客户端最多接受未被ack的消息个数
        channel.basicQos(64);
        /**
         * 这里采用的是集成DefaultConsumer的方式来实现消费，有过RabbitMQ使用经验的
         * 读者也许会喜欢QueueingConsumer的方式来实现消费，但是我们并不推荐，使用QueueingConsumer会有一些隐患。
         * 同时，在RabbitMQ java客户端4.0.0版本开始将QueueingConsumer标记未@Deprecated。在后面的大版本中会删除这个类。
         * 更多详细内容可以参考4.9.3节目
         */
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){

            /**
             * No-op implementation of {@link Consumer#handleDelivery}.
             *
             * @param consumerTag
             * @param envelope
             * @param properties
             * @param body
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("recv message: " + new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };

        channel.basicConsume(QUEUE_NAME,defaultConsumer);
        //等待回调函数执行完毕之后，关闭资源
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }

}
