package model2.mvcboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.JSFunction;

public class PassController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("PassController �����۵�");
		
		//view ������(/mvc_Board/pass.jsp�� �������� �ѱ�)
			// mode:edit <= �� ���� mode:delete <= �� ����
		req.setAttribute("mode", req.getParameter("mode")); // get������� mode���� ���� => �װ� "mode"������ ��´�.
		req.getRequestDispatcher("/mvc_board/Pass.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// form���� post������� �Ѿ���� ���� �� 3��
		// Pass.jsp���� ������ ���� �� 3�� ó��
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");
		String pass = req.getParameter("pass");
		
		// ��й�ȣ Ȯ�� (DAO�� �۾�)
		
		MVCBoardDAO dao = new MVCBoardDAO();
		boolean confirmed = dao.confirmPassword(pass, idx);
		dao.close(); // close()��Ŵ
		
		if(confirmed) { // ��й�ȣ�� ��ġ�� ��
			if(mode.equals("edit")) {
				HttpSession session = req.getSession();
				session.setAttribute("pass", pass);	// pass�� Session������ �Ҵ�
				resp.sendRedirect("../mvc_board/edit.do?=idx" + idx);
			}else if (mode.equals("delete")){
				dao = new MVCBoardDAO(); // ��ü �ٽ� ����
				MVCBoardDTO dto = dao.selectView(idx);
				dao.close();
				
				// ���� ���� ������ �̵� (JavaScript) :JSFunction.java
				JSFunction.alertLocation(resp, "�����Ǿ����ϴ�.", "../mvc_board/list.do");
			}
		} else { // ��й�ȣ�� ��ġ���� ���� ��(Java Script �����ؼ� ���� �������� ���ư�����)
					// ���� �������� �̵� (JSFunction)
			JSFunction.alertBack(resp, "��й�ȣ ������ �����߽��ϴ�.");
		}
	}
}
