package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.dto.Todo;
import edu.kh.todoList.service.TodoListService;
import edu.kh.todoList.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/update")
public class UpdateServlet extends HttpServlet {

	// 수정 화면 전환 Get 요청.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));
			
			// 수정 화면에는 기존의 제목, 상세 내용이
			// input, textarea에 채워져있는 상태여야 함.
			// -> 수정 전 제목/내용 조회 == 상세조회 서비스 재호출.
			
			TodoListService service = new TodoListServiceImpl();
			Todo todo = service.todoDetailView(todoNo);
			
			if(todo == null) {
				// 메인페이지로 redirect
				req.getSession().setAttribute("message", "todo가 없습니다.. 메인 페이지로 이동합니다.");
				resp.sendRedirect("/");
				return;
			}
			
			// request scope에 todo 객체 세팅.
			req.setAttribute("todo", todo);
			
			// 요청 발송자 이용해 forward
			String path = "/WEB-INF/views/update.jsp";
			req.getRequestDispatcher(path).forward(req, resp); // 요청 위임.

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * 요청 주소가 같을 때
	 * 데이터 전달 방식이 다르면(GET/POST)
	 * 하나의 서블릿에서 각각의 메서드(doGet() / doPost())를 만들어 처리할 수 있다.
	 * 
	 * */
	// 할 일 제목/내용 수정 POST 요청.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// 전달 받은 파라미터 얻어오기. (제목,상세내용,todoNo)
			String title = req.getParameter("title");
			String detail = req.getParameter("detail");
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));
			
			TodoListService service = new TodoListServiceImpl();
			int result = service.todoUpdate(todoNo, title, detail);
			
			// 수정 성공 시
			// 상세 조회 페이지로 redirect
			// "수정이 완료되었습니다." message 출력.

			// 수정 실패 시
			// 수정 화면으로 redirect 후
			// "수정 실패" message 출력.
			String url = null;
			String message = null;
			
			if(result > 0) {
				url = "/todo/detail?todoNo=" + todoNo;
				message = "수정이 완료되었습니다.";
			}
			else {
				url = "/todo/update?todoNo=" + todoNo;
				message = "수정 실패";
			}
			
			// session 객체에 속성 추가.
			req.getSession().setAttribute("message", message);
			
			// redirect는 Get 방식 요청.
			resp.sendRedirect(url);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}



}
