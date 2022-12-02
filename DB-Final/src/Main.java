import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;


public class Main {
	static final String DB_URL = "jdbc:mysql://localhost/coupang?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static final String USER = "root"; // user name
	static final String PASS = "mjhj6775@@"; // user password
	//static final String QUERY = "select * from item"; // input query
	
	public static void main(String[] args) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS); 
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("SQLException : " + e);
		}
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Coupang");
		System.out.println("1. 카테고리별 아이템 검색");
		System.out.println("2. 아이템 주문하기");
		System.out.println("3. 종료");
		System.out.print("input: ");
		int option = scanner.nextInt();
		
		switch(option)
		{
		case 1:
			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS); 
				stmt = conn.createStatement();
				
				System.out.print("카테고리를 입력하시오: ");
				String category = scanner.next();
				
				String query1 = "select * from item where category = '" + category + "'";
				rs = stmt.executeQuery(query1);
				
				int itemNum = 1;
				System.out.println("");
				
				while (rs.next()) {
					System.out.println(category +" " + itemNum +") " + rs.getString("item_name"));
					itemNum++;
				}
				
			} catch (SQLException e) {
				System.out.println("SQLException : " + e);
			}
			
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			// 시스템 이어서 시작
			System.out.println("");
			System.out.println("Coupang");
			System.out.println("1. 카테고리별 아이템 검색");
			System.out.println("2. 아이템 주문하기");
			System.out.println("3. 종료");
			System.out.print("input: ");
			option = scanner.nextInt();
			
		case 2:
			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				String query2 = "insert into coupang.order values (?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query2);
				
				System.out.print("order_id: ");
				int orderId = scanner.nextInt();
				
				System.out.print("user_id: ");
				int userId = scanner.nextInt();
				
				System.out.print("item_id: ");
				int itemId = scanner.nextInt();
				
				System.out.print("order_date: ");
				String orderDate = scanner.next();
				//java.sql.Date orderDate = java.sql.Date.valueOf(dt);
						
				System.out.print("num_of_item: ");
				int num_of_item = scanner.nextInt();
				
				System.out.print("total_price: ");
				int totalPrice = scanner.nextInt();
				
				
				pstmt.setInt(1, orderId);
				pstmt.setInt(2, userId);
				pstmt.setInt(3, itemId);
				pstmt.setString(4, orderDate);
				pstmt.setInt(5, num_of_item);
				pstmt.setInt(6, totalPrice);
				//System.out.println(pstmt);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("SQLException : " + e);
			}
			
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 시스템 이어서 시작
			System.out.println("");
			System.out.println("Coupang");
			System.out.println("1. 카테고리별 아이템 검색");
			System.out.println("2. 아이템 주문하기");
			System.out.println("3. 종료");
			System.out.print("input: ");
			option = scanner.nextInt();
			
		case 3:
			
		}		
		scanner.close();
	}
}
