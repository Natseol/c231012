package c231013.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import c231013.user.UserBean;
import c231013.user.UserDAO;
import c231013.user.UserInterface;

public class Test {
	public static void main(String[] args) throws Exception{

		ApplicationContext context = new AnnotationConfigApplicationContext(DAOFactory.class);
		UserDAO dao = context.getBean("userDAO", UserDAO.class);		
				
		UserBean user = new UserBean();
		user.setName("spirng테스트");
		user.setUserId("springtest");
		user.setPassword("tpsw");
		
		dao.add(user);
		System.out.println("추가확인");
		
		UserInterface createdUser = dao.get("springtest");
		System.out.println(createdUser.getId());
		System.out.println(createdUser.getName());
		System.out.println(createdUser.getUserId());
		System.out.println(createdUser.getPassword());
	}
}
