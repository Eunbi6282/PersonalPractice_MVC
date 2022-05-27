package model2.mvcboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mvc_board/view.do")
public class ViewController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get��û�� ó��
		
		// �Խù� ���� �ҷ����� (1. ��ȸ�� ����, 2. �Խù� ���� ��������)
		MVCBoardDAO dao = new MVCBoardDAO();
		
		String idx = req.getParameter("idx");
		
		// 1. ��ȸ�� ����
		dao.updateVisitCount(idx);
		
		// 2. �Խù��� �ڼ��� ������ ��������
		MVCBoardDTO dto = dao.selectView(idx);
		dao.close(); // ��ü�ݳ�
		
		// DB�� content�÷��� \r\n(����)�� "<br\>" �±׷� ��ȯ�ؾ� �Ѵ�.
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br>"));
		
		// �Խù�(dto) ��ü�� view�������� �����ϱ� ���� ������ ����
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/mvc_board/View.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
}
