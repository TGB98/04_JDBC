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

@WebServlet("/std/detail")
public class DetailServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			int stdNo = Integer.parseInt(req.getParameter("stdNo"));
			
			StdListService service = new StdListServiceImpl();
			
			Student std = service.stdDetailView(stdNo);
			
			if(std == null) {
				req.getSession().setAttribute("message", "존재하지 않는 학생입니다.");
				resp.sendRedirect("/");
				return;
			}
			
			req.setAttribute("std", std);
			
			String path = "/WEB-INF/views/detail.jsp";
			
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	
	}
	
}
