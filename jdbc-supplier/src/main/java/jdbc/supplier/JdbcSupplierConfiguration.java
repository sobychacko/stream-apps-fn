package jdbc.supplier;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;

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
	public JdbcSupplier jdbcSupplier(MessageSource messageSource) {
		return new JdbcSupplier(messageSource);
	}
}
