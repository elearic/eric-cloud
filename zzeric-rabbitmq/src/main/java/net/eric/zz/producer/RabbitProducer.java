package net.eric.zz.producer;

import net.eric.zz.constants.RabbitConstants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
    public void immediateProcess(String message) {
        System.out.println("Receiver：" + message);
    }

}
