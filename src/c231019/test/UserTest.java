package c231019.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import c231019.user.UserBean;
import c231019.user.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "./applicationContext.xml")
public class UserTest {
	@Autowired
	UserDAO dao;
	@Autowired
	UserTestDAO test;
	
	private UserBean user1 = new UserBean();
	
	@Before
	public void initialize() throws Exception{
		try {
			test.create();
		} catch (Exception e) {
			System.out.println(e.getMessage());
//			test.drop();
//			test.create();
		}
		user1.setName("spirng테스트4");
		user1.setUserId("abcd");
		user1.setPassword("tpsw2");
		dao.add(user1);
	}
	
	@Test
	public void add(){
		UserBean user = new UserBean();
		
		String userId = "abc";
		user.setName("spirng테스트4");
		user.setUserId(userId);
		user.setPassword("tpsw2");
		dao.add(user);
	}
	
	@After
	public void dropTable(){
		test.drop();
	}
	
	@Test
	public void get(){
		UserBean createdUser = dao.get(user1.getUserId());
		assertThat(createdUser.getId(), is(1));
		assertThat(createdUser.getName(), is(user1.getName()));
		assertThat(createdUser.getUserId(), is(user1.getUserId()));
		assertThat(createdUser.getPassword(), is(user1.getPassword()));
	}
	
	
	@Test
	public void addAndGet(){
		UserBean user = new UserBean();
		
		String userId = "abc";
		user.setName("spirng테스트4");
		user.setUserId(userId);
		user.setPassword("tpsw2");
		dao.add(user);
		
		UserBean createdUser = dao.get(userId);
		assertThat(createdUser.getName(), is(user.getName()));
		assertThat(createdUser.getUserId(), is(user.getUserId()));
		assertThat(createdUser.getPassword(), is(user.getPassword()));
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void duplicate() {
		UserBean user2 = new UserBean();
		user2.setName("asdf");
		user2.setUserId("asdf");
		user2.setPassword("asdf");
		dao.add(user2);
		
		UserBean user3 = new UserBean();
		user3.setName("asdf");
		user3.setUserId("asdf");
		user3.setPassword("asdf");
		dao.add(user3);
	}
}
