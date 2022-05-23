package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;


import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool{
	// �⺻ ������ ȣ�� �� �θ� Ŭ������ ȣ��
	public MVCBoardDAO() {
		super();	// �θ��� �⺻������ ȣ��. DBCP���� con��ü Ȱ��ȭ
	}
	
	// �˻� ���ǿ� �´� �Խù� ������ ��ȯ�մϴ�.
	public int selectCount (Map <String, Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM pebboard";	// ���ڵ��� �� ���� ��ȯ
			if(map.get("searchWord") != null)  {  //T(String)�� searchWord�� ���� �� where���� �߰��� ������ �ִ´�.
				query += " Where " + map.get("searchField") + " " + "like '%" + map.get("searchWord") + "%'";
			}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("�Խù� ī��Ʈ �� ���ܹ߻�");
			e.printStackTrace();
		}
		
		return totalCount;
	}
		
		//�˻� ���ǿ� �´� �Խù� ����� ��ȯ
			//DataBase���� Select �� ������� DTO�� ��Ƽ� ����
	public List <MVCBoardDTO> selectListPage ( Map<String, Object> map) {
		List <MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		
		String query = " "
				+ "SELECT * FROM ( " 
				+ "		SELECT Tb.*, ROWNUM rNUM FROM ( "
				+ " 		SELECT * FROM pebboard ";
		
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")
				+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		query += "		ORDER BY idx DESC"
				+ " ) Tb"
				+") "
				+" WHERE rNUM BETWEEN ? AND ?";
		// ù��° �Խù��� ��ȣ�� ������ �Խù� ���̿� �ش��ϴ� �Խù��� ���
		
		System.out.println(query); // �ֿܼ� ��ü ���� ���
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());	// map���� start������ ���� ������ ��
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();	//DB�� Select�� �������  rs�� ����
			
			// rs�� ����� ���� DTO�� ���� => ��ü�� List�� add
			
			while (rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				dto.setIdx(rs.getString(1)); //rs�� index1���� ���� setter�� ���� ����
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto); //List�� DB�� rs�� �ϳ��� ���ڵ� ���� dto�� �����ϰ� dto�� List�� �߰�
			}
		} catch (Exception e) {e.printStackTrace();}
		
		return board;
	}
	
	// ��� �˻� (select ) : �־��� �Ϸù�ȣ�� �ش��ϴ� ���� DTO�� ��� ��ȯ (�� ������ read)
		//ViewController���� ��û ó�� /idx������ select
	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO();
		String query = "SELECT * FROM pebboard WHERE idx = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				// rs (select)�� ������� set�� �̿��ؼ� �� ����
				dto.setIdx(rs.getString(1));	// 1�� �÷�
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
			}
		} catch (Exception e) {
			System.out.println("�Խù� ������ ��½� ���ܹ߻�");
			e.printStackTrace();
		}
		return dto;
	}
	
	// �־��� �Ϸù�ȣ�� �ش��ϴ� �Խù��� ��ȸ���� 1������Ŵ
	public void updateVisitCount (String idx) {
		String query = "UPDATE pebboard SET "
				+ " visitcount = visitcount + 1"
				+ " WHERE idx = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�Խù� ��ȸ�� ������ ���� �߻�");
		}
	}
		
	// ��й�ȣ Ȯ�� �޼��� (�Է��� ��й�ȣ�� DB�� ���� ��ġ�ϴ� �� Ȯ��)
	public boolean confirmPassword (String pass, String idx) {
		boolean isCorr = true;
		
		
		
		return isCorr;
	}
	
	
	
		
		
		
		
}
