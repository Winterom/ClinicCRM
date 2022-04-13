package sender.configs;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sender.command.CommandSender;

@Configuration
public class RabbitConfig {
    private final String QUEUE_NAME = "CRM_APP_SENDER";
    @Bean
    public SimpleMessageListenerContainer containerForDirectQueue(ConnectionFactory factory, MessageListenerAdapter adapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(adapter);
        return container;
    }
    @Bean
    public MessageListenerAdapter listenerAdapter (CommandSender commandSender){
        return new MessageListenerAdapter(commandSender,"execute");
    }
}
