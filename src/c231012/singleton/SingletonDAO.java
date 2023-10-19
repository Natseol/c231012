package c231012.singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import c231012.connection.ConnectionMaker;
import c231012.user.UserBean;

public class SingletonDAO {
	private ConnectionMaker maker;
	
	public static SingletonDAO INSTANCE;
	
	public SingletonDAO(ConnectionMaker maker) {
		this.maker = maker;
	}
	
	public static SingletonDAO getInstance() {
		return INSTANCE;
	}
	
	public static SingletonDAO getInstance(ConnectionMaker maker) {
		if (INSTANCE == null) {INSTANCE = new SingletonDAO(maker);}
		return INSTANCE;
	}
	
	public void add(UserBean user) throws Exception  {
		Connection con = maker.makeConnection();
		String query = "insert into users (name, user_id, password) values (?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(query);
				
		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getUserId());
		pstmt.setString(3, user.getPassword());
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
	}
	
	public UserBean get(String userId) throws Exception  {
		Connection con = maker.makeConnection();
		String query = "select * from users where user_id=?";
		PreparedStatement pstmt = con.prepareStatement(query);
		UserBean temp = new UserBean();
				
		pstmt.setString(1, userId);		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			temp.setId(rs.getInt("id"));
			temp.setName(rs.getString("name"));
			temp.setUserId(rs.getString("user_id"));
			temp.setPassword(rs.getString("password"));
		}
		rs.close();
		pstmt.close();
		con.close();
		
		return temp;
	}

}
