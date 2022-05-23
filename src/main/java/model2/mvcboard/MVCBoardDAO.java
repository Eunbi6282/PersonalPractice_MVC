package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;


import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool{
	// 기본 생성자 호출 시 부모 클래스의 호출
	public MVCBoardDAO() {
		super();	// 부모의 기본생성자 호출. DBCP에서 con객체 활성화
	}
	
	// 검색 조건에 맞는 게시물 개수를 반환합니다.
	public int selectCount (Map <String, Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM pebboard";	// 레코드의 총 개수 반환
			if(map.get("searchWord") != null)  {  //T(String)에 searchWord가 있을 때 where값을 추가로 쿼리에 넣는다.
				query += " Where " + map.get("searchField") + " " + "like '%" + map.get("searchWord") + "%'";
			}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("게시물 카운트 중 예외발생");
			e.printStackTrace();
		}
		
		return totalCount;
	}
		
		//검색 조건에 맞는 게시물 목록을 반환
			//DataBase에서 Select 한 결과값을 DTO에 담아서 리턴
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
		// 첫번째 게시물의 번호와 마지막 게시물 사이에 해당하는 게시물들 출력
		
		System.out.println(query); // 콘솔에 전체 쿼리 출력
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());	// map에서 start변수의 값을 가지고 옴
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();	//DB에 Select한 결과값을  rs에 저장
			
			// rs에 저장된 값을 DTO에 저장 => 객체를 List에 add
			
			while (rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				dto.setIdx(rs.getString(1)); //rs의 index1번의 값을 setter을 통해 주입
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto); //List의 DB의 rs의 하나의 레코드 값을 dto에 저장하고 dto를 List에 추가
			}
		} catch (Exception e) {e.printStackTrace();}
		
		return board;
	}
	
	// 목록 검색 (select ) : 주어진 일련번호에 해당하는 값을 DTO에 담아 변환 (한 페이지 read)
		//ViewController에서 요청 처리 /idx값으로 select
	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO();
		String query = "SELECT * FROM pebboard WHERE idx = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				// rs (select)의 결과물을 set을 이용해서 값 주입
				dto.setIdx(rs.getString(1));	// 1번 컬럼
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
			System.out.println("게시물 상세정보 출력시 예외발생");
			e.printStackTrace();
		}
		return dto;
	}
	
	// 주어진 일련번호에 해당하는 게시물의 조회수를 1증가시킴
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
			System.out.println("게시물 조회수 증가시 예외 발생");
		}
	}
		
	// 비밀번호 확인 메서드 (입력한 비밀번호가 DB의 값과 일치하는 지 확인)
	public boolean confirmPassword (String pass, String idx) {
		boolean isCorr = true;
		
		
		
		return isCorr;
	}
	
	
	
		
		
		
		
}
