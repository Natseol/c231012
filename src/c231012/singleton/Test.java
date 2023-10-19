package c231012.singleton;

import c231012.connection.ConnectionMaker;
import c231012.connection.OracleCM;
import c231012.user.UserBean;

public class Test {
	public static void main(String[] args) throws Exception {
		ConnectionMaker maker = new OracleCM();
		SingletonDAO dao = SingletonDAO.getInstance(maker);
		SingletonDAO dao1 = SingletonDAO.getInstance();
		SingletonDAO dao2 = SingletonDAO.getInstance();
		System.out.println(dao);
		System.out.println(dao1);
		System.out.println(dao2);
		
//		UserBean user = new UserBean();
//		user.setName("테스트2");
//		user.setUserId("test2");
//		user.setPassword("psw");		
//		dao.add(user);
	}
}
