package net.eric.rabbitmq.config;

import net.eric.rabbitmq.constants.RabbitConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import org.springframework.amqp.core.Queue;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: RabbitMqConfig.java, v 0.1 2020/3/11 9:51 AM zz_huns Exp $$
 *
 */
@SpringBootConfiguration
public class RabbitmqConfig {

    @Bean
    public Queue immediateQueue(){
        return new Queue(RabbitConstants.TEST_QUEUE,true) ;
    }

    @Bean
    public DirectExchange immediateExchange(){
        return new DirectExchange(RabbitConstants.TEST_EXCHANGE,true,false);
    }

    @Bean
    public Binding immediateBinding(){
        return BindingBuilder.bind(immediateQueue()).to(immediateExchange()).with(RabbitConstants.TEST_BINDING);
    }
}
