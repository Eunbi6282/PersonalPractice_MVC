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
	
	//�⺻ ������
	public DBConnPool () {
		// JDBC ����̹� �ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			// DB�� ����
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "HR2";
			String pwd = "1234";
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB���Ἲ��(�⺻������)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�ڿ� ���� ���� (�ڿ��ݳ�) : close() �޼��� ȣ�� �� �ڿ��� �ݳ��ϵ���
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
				System.out.println("DBĿ�ؼ� Ǯ �ڿ� �ݳ�(����)");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("DBĿ�ؼ� Ǯ �ڿ� �ݳ�(����)");
				
			}
	}
}
