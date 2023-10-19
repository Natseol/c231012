package c231016.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface StatementStrategy {
	PreparedStatement makePstmt(Connection con) throws SQLException ;
}
