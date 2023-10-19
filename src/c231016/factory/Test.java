package c231016.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import c231016.user.JdbcContextUserDAO;
import c231016.user.UsedSpringUserDAO;
import c231016.user.UserBean;
import c231016.user.UserDAO;
import c231016.user.UserInterface;

public class Test {
	public static void main(String[] args) throws Exception{

		ApplicationContext context = new AnnotationConfigApplicationContext(DAOFactory.class);
//		JdbcContextUserDAO dao = context.getBean("jdbcContextUserDAO", JdbcContextUserDAO.class);
		UsedSpringUserDAO dao = context.getBean("usedSpringUserDAO", UsedSpringUserDAO.class);
//		UserDAO dao = context.getBean("userDAO", UserDAO.class);		
				
//		UserBean user = new UserBean();
//		user.setName("spirng테스트4");
//		user.setUserId("springtest4");
//		user.setPassword("tpsw2");
//		
//		dao.add(user);
//		dao.delete(7);
		System.out.println("추가확인");
		
		UserInterface createdUser = dao.get("springtest3");
		System.out.println(createdUser.getId());
		System.out.println(createdUser.getName());
		System.out.println(createdUser.getUserId());
		System.out.println(createdUser.getPassword());
	}
}
