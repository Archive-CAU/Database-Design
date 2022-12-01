import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	static final String DB_URL = "jdbc:mysql://localhost/coupang?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static final String USER = "root"; // user name
	static final String PASS = "mjhj6775@@"; // user password
	static final String QUERY = "select * from item"; // input query
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// select * from item °á°ú
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS); 
			stmt = conn.createStatement();
			rs = stmt.executeQuery(QUERY);
			int itemNum = 1;
			while (rs.next()) {
				System.out.println("Item " + itemNum + " name = " + 
				rs.getString("item_name"));
				itemNum++;
			}
		} catch (SQLException e) {
			System.out.println("SQLException : " + e);
		} finally {
			try {
			rs.close();
			stmt.close();
			conn.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		}
	}
}
