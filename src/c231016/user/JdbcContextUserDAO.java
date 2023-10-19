package c231016.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContextUserDAO {
	private DataSource dataSource;
		
	public JdbcContextUserDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void jdbcContextWithStatementStrategy(StatementStrategy stmtS) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;		
		try {
			con = dataSource.getConnection();			
			pstmt = stmtS.makePstmt(con);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public void add(UserBean user) throws SQLException {
		jdbcContextWithStatementStrategy(new StatementStrategy() {
			@Override
			public PreparedStatement makePstmt(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement("insert into users (name, user_id, password) values (?, ?, ?)");
				
				pstmt.setString(1, user.getName());
				pstmt.setString(2, user.getUserId());
				pstmt.setString(3, user.getPassword());
				
				return pstmt;
			}
		});
	}
	public void delete(int id) throws SQLException {
		jdbcContextWithStatementStrategy(new StatementStrategy() {
			@Override
			public PreparedStatement makePstmt(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement("delete from users where id=?");
				
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
				
				return pstmt;
			}
		});
	}
}
