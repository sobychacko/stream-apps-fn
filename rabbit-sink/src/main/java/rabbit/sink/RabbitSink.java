package rabbit.sink;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
public class RabbitSink {

	@Autowired
	private MessageHandler messageHandler;

	@Bean
	public Function<String, String> sink() {
		return o -> {
			this.messageHandler.handleMessage(MessageBuilder.withPayload(o).build());
			return "Message sent to rabbitmq";
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitSink.class, args);
	}
}
