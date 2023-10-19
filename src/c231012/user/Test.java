package c231012.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import c231012.factory.DAOFactory;

public class Test {
	public static void main(String[] args) throws Exception{

		ApplicationContext context = new AnnotationConfigApplicationContext(DAOFactory.class);
		UserDAO dao = context.getBean("userDAO", UserDAO.class);		
		
//		UserDAO dao1 = context.getBean("userDAO", UserDAO.class);
//		UserDAO dao2 = context.getBean("userDAO", UserDAO.class);
//		
//		System.out.println(dao1);
//		System.out.println(dao2);
		
		UserBean user = new UserBean();
		user.setName("테스트2");
		user.setUserId("test2");
		user.setPassword("psw");
		
		dao.add(user);
		System.out.println("추가확인");
		
		UserBean createdUser = dao.get("test2");
		System.out.println(createdUser.getId());
		System.out.println(createdUser.getName());
		System.out.println(createdUser.getUserId());
		System.out.println(createdUser.getPassword());
	}
}
