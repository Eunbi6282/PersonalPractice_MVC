package model2.mvcboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	}
	
}
