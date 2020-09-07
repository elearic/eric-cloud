package net.eric.rabbitmq.sender;

import net.eric.rabbitmq.constants.RabbitConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: RabbitmqSender.java, v 0.1 2020/2/10 4:14 PM zz_huns Exp $$
 *
 */
@Component
@RequestMapping("rabbitmq")
public class RabbitmqSender {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @RequestMapping("demo")
    public void send(){
        String message = "hello wolrd";

        System.out.println("===message===>"+message);

        rabbitTemplate.convertAndSend(RabbitConstants.TEST_EXCHANGE,RabbitConstants.TEST_BINDING,message);
    }
}
