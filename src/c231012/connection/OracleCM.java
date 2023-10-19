package c231012.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleCM implements ConnectionMaker{
	@Override
	public Connection makeConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");		
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521/xe",
				"java",
				"qwer"
			);
		return con;
	}
}
