package c231017.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class TestUserDAO {
	private JdbcTemplate jdbcTemplate;
	
	public TestUserDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void drop() {
		jdbcTemplate.execute("drop table users");
	}
	
	public void create() throws Exception {
		int maxCount = 3;
		while (maxCount-->0) {
			try {
				jdbcTemplate.execute(
						"create table users (" + " id number generated as identity primary key," + " name varchar2(20),"
								+ " user_id varchar2(50) not null unique," + " password varchar2(64) not null)");
				return;
			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println("시도 중 "+maxCount);
			}
		}
		throw new Exception("table 생성 실패");
	}
	
//	public void add(UserInterface user) throws SQLException {
//		jdbcTemplate.update("insert into users (name, user_id, password) values (?, ?, ?)",
//				user.getName(), user.getUserId(), user.getPassword());
//		
//	}
//	
//	public UserInterface get(String userId) throws SQLException {
//		return jdbcTemplate.queryForObject(
//				"select * from users where user_id=?",
//				new Object[] {userId},
//				new RowMapper<UserBean>() {
//					@Override
//					public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException{
//						UserBean user = new UserBean();
//						user.setId(rs.getInt("id"));
//						user.setName(rs.getString("name"));
//						user.setUserId(rs.getString("user_id"));
//						user.setPassword(rs.getString("password"));
//						return user;
//					}
//				});		
//	}
}
