package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BoardPage;

public class ListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// GET방식으로 요청이 왔을 때 서버에서 처리
		
		// 1. DAO객체에서 생성(Model : 비즈니스 로직 처리)
		MVCBoardDAO dao = new MVCBoardDAO(); //객체를 만드는 순간 커넥션풀(DBCP)연결 성공 -> DAO에서 super()을 연결했기 때문. 
		
		// 2. 뷰에 전달할 매개변수 저장용 맵 생성 (Key, Value)
		Map<String, Object> map = new HashMap <String, Object>();
		String searchFiled = req.getParameter("seachFiled");
		String searchWord = req.getParameter("searchWord");
		
		if (searchWord != null) {
			map.put("searchFiled", searchFiled);
			map.put("searchWord", searchWord);
		}
		
		// 개시물 개수 알아오기 (DAO에 selectCount)
		int totalCount = dao.selectCount(map);
		//System.out.println("전체 레코드 수 : " + totalCount);
		
		
		/* 페이징 처리 부분 start */
			//web.xml에서 세팅한 변수 값 게더링해오기
			ServletContext application = getServletContext();
			int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
			int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
			
			//System.out.println(pageSize);
			//System.out.println(blockPage);
			
			// 현재 페이지 확인
			int pageNum = 1;  //pageNum => 현재 페이지
			String pageTemp = req.getParameter("pageNum"); //Parameter로 넘어오는 값은 모두 String => 계산하려면 int로 변환 필요
			if(pageTemp != null && !pageTemp.equals("")) {
				pageNum = Integer.parseInt(pageTemp); 	// 값이 비어있지 않을 때 넘어온 페이지 변수를 정수로 변환해서 변수에 넣는다. 
			}
			
			// 목록에 출력할 게시물 범위 계산 
			int start = (pageNum - 1) * pageSize * 1;	// 첫 게시물 번호 //pageSize => POSTS_PER_PAGE
			int end = pageNum * pageSize;	// 마지막 게시물 번호
			
			// 뷰페이지에 값을 던져줌 
			map.put("start", start);
			map.put("end", end);
			
		/* 페이징 처리 부분 end*/
		
		// 게시물 목록 받아오기 (DAO 객체에 작업을 전달)
				// DAO의 selectListPage()호출 => return board이므로 DTO까지 불러옴 => boardList에 결과값 담기
					//DTO에는 DB안의 값들이 들어이싿. board는 DTO객체를 담고 있다.
			List <MVCBoardDTO> boardLists = dao.selectListPage(map);
			dao.close(); //DB연결 닫기 -> Dao가 DBPool을 상속하고 있어서 가능
			
		// 뷰페이지에 전달할 매개변수들을 추가
			String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../mvc_board/list.do");
			
			//뷰 페이지로 변수의 값을 전달
			map.put("pagingImg", pagingImg);
			map.put("totalCount", totalCount);
			map.put("pageSize", pageSize);
			map.put("pageNum", pageNum);
			
			// 뷰 페이지로 데이터 전달, request 영역에 전달할 데이터를 저장 후 List.jsp(뷰페이지로 포워드)
			req.setAttribute("boardLists", boardLists);
			req.setAttribute("map", map);
			req.getRequestDispatcher("/mvc_board/List.jsp").forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
