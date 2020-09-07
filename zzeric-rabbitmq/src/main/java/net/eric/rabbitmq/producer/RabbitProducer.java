package net.eric.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import net.eric.rabbitmq.constants.RabbitConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: MqProvider.java, v 0.1 2020/3/11 9:41 AM zz_huns Exp $$
 *
 */
@Component
public class RabbitProducer {



    @RabbitHandler
    @RabbitListener(queues = RabbitConstants.TEST_QUEUE)
    public void immediateProcess(Message<byte[]> message, Channel channel) throws IOException {
        System.out.println("Receiver：" + message);
        byte[] payload = message.getPayload();
        String json = new String(payload);
        if (StringUtils.isNotBlank(json)){
            System.out.println("json....->"+json);
        }
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        // 手动ACK
        channel.basicAck(deliveryTag, false);    }
}
