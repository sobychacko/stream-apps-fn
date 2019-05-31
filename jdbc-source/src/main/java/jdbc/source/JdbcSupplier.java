//package jdbc.source;
//
//import java.util.function.Supplier;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.integration.core.MessageSource;
//import org.springframework.messaging.Message;
//
////@SpringBootApplication
//public class JdbcSupplier {
//
//	@Autowired
//	@Qualifier("jdbcMessageSource")
//	private MessageSource messageSource;
//
//	@Bean
//	public Supplier<Object> get() {
//		return () -> {
//			final Message received = messageSource.receive();
//			System.out.println("Data received from JDBC Source: " + received);
//			return received;
//		};
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.run(JdbcSupplier.class, args);
//	}
//}
