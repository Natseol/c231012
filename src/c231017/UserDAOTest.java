package c231017;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DuplicateKeyException;

import c231017.factory.DAOFactory;
import c231017.user.TestUserDAO;
import c231017.user.UsedSpringUserDAO;
import c231017.user.UserBean;
import c231017.user.UserInterface;

public class UserDAOTest {
	public static void main(String[] args) throws Exception{
		addAndGetTest();			
	}
	
	private static void addAndGetTest() throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(DAOFactory.class);
		TestUserDAO dao = context.getBean("testUserDAO", TestUserDAO.class);
				
		dao.create();
//		UserBean user = new UserBean();
//		user.setName("spirng테스트4");
//		user.setUserId("springtest42");
//		user.setPassword("tpsw2");
//		dao.add(user);
//		System.out.println("추가확인");
//		
//		UserInterface createdUser = dao.get("springtest42");
//		System.out.println(createdUser.getId());
//		if (!createdUser.getName().equals(user.getName())) {
//			System.out.println("name problem");
//		} else if (!createdUser.getUserId().equals(user.getUserId())) {
//			System.out.println("id problem");
//		} else if (!createdUser.getPassword().equals(user.getPassword())) {
//			System.out.println("password problem");
//		} else {
//			System.out.println("success");
//		}
	}
	private UserInterface user1 = new UserBean();
	private ApplicationContext context = new AnnotationConfigApplicationContext(DAOFactory.class);
	
	@Before
	public void initialize() throws Exception{
		TestUserDAO test = context.getBean("testUserDAO", TestUserDAO.class);
		UsedSpringUserDAO dao = context.getBean("usedSpringUserDAO", UsedSpringUserDAO.class);
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
	public void add() throws SQLException {
		UsedSpringUserDAO dao = context.getBean("usedSpringUserDAO", UsedSpringUserDAO.class);
		UserBean user = new UserBean();
		
		String userId = "abc";
		user.setName("spirng테스트4");
		user.setUserId(userId);
		user.setPassword("tpsw2");
		dao.add(user);
	}
	
	@After
	public void dropTable() throws SQLException {
		TestUserDAO test = context.getBean("testUserDAO", TestUserDAO.class);
		test.drop();
	}
	
	@Test
	public void get() throws SQLException {
		UsedSpringUserDAO dao = context.getBean("usedSpringUserDAO", UsedSpringUserDAO.class);
				
		UserInterface createdUser = dao.get(user1.getUserId());
		assertThat(createdUser.getId(), is(1));
		assertThat(createdUser.getName(), is(user1.getName()));
		assertThat(createdUser.getUserId(), is(user1.getUserId()));
		assertThat(createdUser.getPassword(), is(user1.getPassword()));
	}
	
	
	@Test
	public void addAndGet() throws SQLException {
		UsedSpringUserDAO dao = context.getBean("usedSpringUserDAO", UsedSpringUserDAO.class);
		
		UserBean user = new UserBean();
		
		String userId = "abc";
		user.setName("spirng테스트4");
		user.setUserId(userId);
		user.setPassword("tpsw2");
		dao.add(user);
		
		UserInterface createdUser = dao.get(userId);
		assertThat(createdUser.getName(), is(user.getName()));
		assertThat(createdUser.getUserId(), is(user.getUserId()));
		assertThat(createdUser.getPassword(), is(user.getPassword()));
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void duplicate() {
		UsedSpringUserDAO dao = context.getBean("usedSpringUserDAO", UsedSpringUserDAO.class);
		
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
	
//	@Test
//	public void drop() throws SQLException {
//		ApplicationContext context = new AnnotationConfigApplicationContext(DAOFactory.class);
//		TestUserDAO dao = context.getBean("testUserDAO", TestUserDAO.class);
//		dao.drop();
//	}
//	
//	@Test
//	public void create() throws SQLException {
//		ApplicationContext context = new AnnotationConfigApplicationContext(DAOFactory.class);
//		TestUserDAO dao = context.getBean("testUserDAO", TestUserDAO.class);
//		dao.create();
//	}
}
