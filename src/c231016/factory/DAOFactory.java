package c231016.factory;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import c231016.user.JdbcContextUserDAO;
import c231016.user.UsedSpringUserDAO;
import c231016.user.UserDAO;

public class DAOFactory {
	
	@Bean
	public UserDAO userDAO() {
		return new UserDAO(dataSource());
	}
	
	@Bean
	public JdbcContextUserDAO jdbcContextUserDAO() {
		return new JdbcContextUserDAO(dataSource());
	}
	
	@Bean
	public UsedSpringUserDAO usedSpringUserDAO() {
		return new UsedSpringUserDAO(dataSource());
	}
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(oracle.jdbc.OracleDriver.class);
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/xe");
		dataSource.setUsername("java");
		dataSource.setPassword("qwer");
		return dataSource;
	}
}
