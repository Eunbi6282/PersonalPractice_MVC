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
		// System.out.println("PassController 정상작동");
		
		//view 페이지(/mvc_Board/pass.jsp로 변수값을 넘김)
			// mode:edit <= 글 수정 mode:delete <= 글 삭제
		req.setAttribute("mode", req.getParameter("mode")); // get방식으로 mode값이 들어옴 => 그걸 "mode"변수에 담는다.
		req.getRequestDispatcher("/mvc_board/Pass.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// form에서 post방식으로 넘어오는 변수 값 3개
		// Pass.jsp에서 전송한 변수 값 3개 처리
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");
		String pass = req.getParameter("pass");
		
		// 비밀번호 확인 (DAO에 작업)
		
		MVCBoardDAO dao = new MVCBoardDAO();
		boolean confirmed = dao.confirmPassword(pass, idx);
		dao.close(); // close()시킴
		
		if(confirmed) { // 비밀번호가 일치할 때
			if(mode.equals("edit")) {
				HttpSession session = req.getSession();
				session.setAttribute("pass", pass);	// pass를 Session변수에 할당
				resp.sendRedirect("../mvc_board/edit.do?=idx" + idx);
			}else if (mode.equals("delete")){
				dao = new MVCBoardDAO(); // 객체 다시 만듬
				MVCBoardDTO dto = dao.selectView(idx);
				dao.close();
				
				// 삭제 이후 페이지 이동 (JavaScript) :JSFunction.java
				JSFunction.alertLocation(resp, "삭제되었습니다.", "../mvc_board/list.do");
			}
		} else { // 비밀번호가 일치하지 않을 때(Java Script 실행해서 이전 페이지로 돌아가도록)
					// 이전 페이지로 이동 (JSFunction)
			JSFunction.alertBack(resp, "비밀번호 검증에 실패했습니다.");
		}
	}
}
