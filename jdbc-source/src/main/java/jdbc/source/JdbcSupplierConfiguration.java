package jdbc.source;

import java.util.function.Supplier;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.messaging.Message;

@Configuration
@EnableConfigurationProperties(JdbcEventSourceProperties.class)
public class JdbcSupplierConfiguration {

	@Autowired
	JdbcEventSourceProperties properties;

	@Autowired
	private DataSource dataSource;

	@Bean
	public MessageSource<Object> jdbcMessageSource() {
		JdbcPollingChannelAdapter jdbcPollingChannelAdapter =
				new JdbcPollingChannelAdapter(this.dataSource, this.properties.getQuery());
		jdbcPollingChannelAdapter.setMaxRowsPerPoll(this.properties.getMaxRowsPerPoll());
		jdbcPollingChannelAdapter.setUpdateSql(this.properties.getUpdate());
		return jdbcPollingChannelAdapter;
	}

	@Bean
	public Supplier<Message<?>> get() {
		return () -> {
			final Message<?> received = jdbcMessageSource().receive();
			System.out.println("Data received from JDBC Source: " + received);
			//TODO: Add splitting capability
			return received;
		};
	}
}
