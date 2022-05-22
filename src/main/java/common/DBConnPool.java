package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnPool {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	//기본 생성자
	public DBConnPool () {
		// JDBC 드라이버 로드
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			// DB에 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "HR2";
			String pwd = "1234";
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB연결성공(기본생성자)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//자원 연결 해제 (자원반납) : close() 메서드 호출 시 자원을 반납하도록
	public void close() {
			try {
				if(rs != null) {
				rs.close();
				}
				
				if(stmt != null) {
					stmt.close();
				}
				
				if(psmt != null) {
					psmt.close();
				}
				
				if (con != null) {
					con.close();
				}
				System.out.println("DB커넥션 풀 자원 반납(성공)");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("DB커넥션 풀 자원 반납(실패)");
				
			}
	}
}
