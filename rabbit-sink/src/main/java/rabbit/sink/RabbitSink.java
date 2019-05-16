package rabbit.sink;

import java.util.function.Function;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.MessageBuilder;

public class RabbitSink implements Function<Object, Object> {

	private final MessageHandler messageHandler;

	public RabbitSink(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	@Override
	public Object apply(Object o) {
		if(o instanceof Message) {
			this.messageHandler.handleMessage((Message)o);
		}
		else {
			this.messageHandler.handleMessage(MessageBuilder.withPayload(o).build());
		}
		return "Message sent to rabbitmq";
	}
}
