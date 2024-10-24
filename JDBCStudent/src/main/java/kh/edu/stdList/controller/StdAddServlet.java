package kh.edu.stdList.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kh.edu.stdList.service.StdListService;
import kh.edu.stdList.service.StdListServiceImpl;

@WebServlet("/std/add")
public class StdAddServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			StdListService service = new StdListServiceImpl();
			
			String stdName = req.getParameter("stdName");
			
			int stdAge = Integer.parseInt(req.getParameter("stdAge"));
			
			String stdGender = req.getParameter("stdGender");
			
			String stdScore = req.getParameter("stdScore");
			
			int result = service.stdAdd(stdName, stdAge, stdGender, stdScore);
			
			String message = null;
			
			if(result > 0) {
				message = "학생 추가 성공";
				resp.sendRedirect("/");
			}
			else {
				message = "학생 추가 실패";
				resp.sendRedirect("/std/add");
			}
			
			req.getSession().setAttribute("message", message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	
	}
	
	
	
	
}
