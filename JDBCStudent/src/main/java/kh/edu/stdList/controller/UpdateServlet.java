package kh.edu.stdList.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kh.edu.stdList.dto.Student;
import kh.edu.stdList.service.StdListService;
import kh.edu.stdList.service.StdListServiceImpl;

@WebServlet("/std/update")
public class UpdateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			int stdNo = Integer.parseInt(req.getParameter("stdNo"));
			
			StdListService service = new StdListServiceImpl();
			Student std = service.stdDetailView(stdNo);
			
			if(std == null) {
				
				req.getSession().setAttribute("message", "학생이 없습니다.");
				resp.sendRedirect("/");
				return;
				
			}
			
			req.setAttribute("std", std);
			
			String path = "/WEB-INF/views/update.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			String stdName = req.getParameter("stdName");
			int stdAge = Integer.parseInt(req.getParameter("stdAge"));
			String stdGender = req.getParameter("stdGender");
			String stdScore = req.getParameter("stdScore");
			int stdNo = Integer.parseInt(req.getParameter("stdNo"));
			
			StdListService service = new StdListServiceImpl();
			int result = service.stdUpdate(stdNo, stdName, stdAge, stdGender, stdScore);
			
			String url = null;
			String message = null;
			
			if(result > 0) {
				url = "/std/detail?stdNo=" + stdNo;
				message = "학생 정보 수정을 완료했습니다.";
			}
			else {
				url = "/std/detail?stdNo=" + stdNo;
				message = "학생 정보 수정을 실패했습니다.";
			}
			
			req.getSession().setAttribute("message", message);
			
			resp.sendRedirect(url);
			
		} catch (Exception e) {

			e.printStackTrace();
		
		}
	
	
	}
	
}
