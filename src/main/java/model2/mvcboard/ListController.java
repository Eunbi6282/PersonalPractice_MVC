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
		// GET������� ��û�� ���� �� �������� ó��
		
		// 1. DAO��ü���� ����(Model : ����Ͻ� ���� ó��)
		MVCBoardDAO dao = new MVCBoardDAO(); //��ü�� ����� ���� Ŀ�ؼ�Ǯ(DBCP)���� ���� -> DAO���� super()�� �����߱� ����. 
		
		// 2. �信 ������ �Ű����� ����� �� ���� (Key, Value)
		Map<String, Object> map = new HashMap <String, Object>();
		String searchFiled = req.getParameter("seachFiled");
		String searchWord = req.getParameter("searchWord");
		
		if (searchWord != null) {
			map.put("searchFiled", searchFiled);
			map.put("searchWord", searchWord);
		}
		
		// ���ù� ���� �˾ƿ��� (DAO�� selectCount)
		int totalCount = dao.selectCount(map);
		//System.out.println("��ü ���ڵ� �� : " + totalCount);
		
		
		/* ����¡ ó�� �κ� start */
			//web.xml���� ������ ���� �� �Դ����ؿ���
			ServletContext application = getServletContext();
			int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
			int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
			
			//System.out.println(pageSize);
			//System.out.println(blockPage);
			
			// ���� ������ Ȯ��
			int pageNum = 1;  //pageNum => ���� ������
			String pageTemp = req.getParameter("pageNum"); //Parameter�� �Ѿ���� ���� ��� String => ����Ϸ��� int�� ��ȯ �ʿ�
			if(pageTemp != null && !pageTemp.equals("")) {
				pageNum = Integer.parseInt(pageTemp); 	// ���� ������� ���� �� �Ѿ�� ������ ������ ������ ��ȯ�ؼ� ������ �ִ´�. 
			}
			
			// ��Ͽ� ����� �Խù� ���� ��� 
			int start = (pageNum - 1) * pageSize * 1;	// ù �Խù� ��ȣ //pageSize => POSTS_PER_PAGE
			int end = pageNum * pageSize;	// ������ �Խù� ��ȣ
			
			// ���������� ���� ������ 
			map.put("start", start);
			map.put("end", end);
			
		/* ����¡ ó�� �κ� end*/
		
		// �Խù� ��� �޾ƿ��� (DAO ��ü�� �۾��� ����)
				// DAO�� selectListPage()ȣ�� => return board�̹Ƿ� DTO���� �ҷ��� => boardList�� ����� ���
					//DTO���� DB���� ������ ����̚�. board�� DTO��ü�� ��� �ִ�.
			List <MVCBoardDTO> boardLists = dao.selectListPage(map);
			dao.close(); //DB���� �ݱ� -> Dao�� DBPool�� ����ϰ� �־ ����
			
		// ���������� ������ �Ű��������� �߰�
			String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../mvc_board/list.do");
			
			//�� �������� ������ ���� ����
			map.put("pagingImg", pagingImg);
			map.put("totalCount", totalCount);
			map.put("pageSize", pageSize);
			map.put("pageNum", pageNum);
			
			// �� �������� ������ ����, request ������ ������ �����͸� ���� �� List.jsp(���������� ������)
			req.setAttribute("boardLists", boardLists);
			req.setAttribute("map", map);
			req.getRequestDispatcher("/mvc_board/List.jsp").forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
