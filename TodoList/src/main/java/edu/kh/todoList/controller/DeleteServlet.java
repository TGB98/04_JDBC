package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.service.TodoListService;
import edu.kh.todoList.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/delete")
public class DeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));
			
			TodoListService service = new TodoListServiceImpl();
			
			int result = service.delete(todoNo);
			
			HttpSession session = req.getSession();
			
			// 삭제 성공 시
			if(result > 0) {
				session.setAttribute("message", "할 일 삭제를 완료했습니다.");
				resp.sendRedirect("/");
				return;
			}
			
			// 삭제 실패 시
			session.setAttribute("message", "todo가 존재하지 않습니다.");
			resp.sendRedirect("/");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		

	
	
	
	
	
	
	}
	
}
