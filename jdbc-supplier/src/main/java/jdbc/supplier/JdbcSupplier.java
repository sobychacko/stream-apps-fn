package jdbc.supplier;

import java.util.function.Supplier;

import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;

public class JdbcSupplier implements Supplier<Object> {

	private final MessageSource messageSource;

	public JdbcSupplier(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public Object get() {
		final Message received = messageSource.receive();
		System.out.println("Data received from JDBC Source: " + received);
		return received;
	}
}
