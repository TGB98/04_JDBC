package kh.edu.stdList.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kh.edu.stdList.service.StdListService;
import kh.edu.stdList.service.StdListServiceImpl;

@WebServlet("/std/delete")
public class DeleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			int stdNo = Integer.parseInt(req.getParameter("stdNo"));
			
			StdListService service = new StdListServiceImpl();
			
			int result = service.stdDelete(stdNo);
			
			HttpSession session = req.getSession();
			
			if(result > 0) {
				session.setAttribute("message", "학생 정보를 삭제했습니다.");
				resp.sendRedirect("/");
				return;
			}
			
			session.setAttribute("message", "삭제하려는 학생이 없습니다.");
			resp.sendRedirect("/std/detail");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	
	
	}
	
}
