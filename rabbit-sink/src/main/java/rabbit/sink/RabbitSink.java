package rabbit.sink;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
public class RabbitSink {

	@Autowired
	@Qualifier("amqpChannelAdapter")
	private MessageHandler messageHandler;

	@Bean
	public Function<Object, Object> sink() {
		return o -> {
			System.out.println("Got the message: " + o);
			System.out.println("Got:" + this.messageHandler.getClass().getName());
			this.messageHandler.handleMessage(MessageBuilder.withPayload(o).build());
			return "Message sent to rabbitmq - check the exchange...";
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitSink.class, args);
	}
}
