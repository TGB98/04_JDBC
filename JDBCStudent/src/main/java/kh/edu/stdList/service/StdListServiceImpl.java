package kh.edu.stdList.service;

import java.sql.Connection;
import java.util.List;

import static kh.edu.stdList.common.JDBCTemplate.*;

import kh.edu.stdList.dao.StdListDAO;
import kh.edu.stdList.dao.StdListDAOImpl;
import kh.edu.stdList.dto.Student;

public class StdListServiceImpl implements StdListService {

	private StdListDAO dao = new StdListDAOImpl();
	
	
	@Override
	public List<Student> stdListFullView() throws Exception {

		Connection conn = getConnection();
		
		List<Student> stdList = dao.stdListFullView(conn);
		
		
		return stdList;
	}


	@Override
	public Student stdDetailView(int stdNo) throws Exception {

		Connection conn = getConnection();
		
		Student std = dao.stdDetailView(conn, stdNo);
		
		close(conn);

		return std;
	}


	@Override
	public int stdAdd(String stdName, int stdAge, String stdGender, String stdScore) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.stdAdd(conn, stdName, stdAge, stdGender, stdScore);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		
		return result;
	}


	@Override
	public int stdDelete(int stdNo) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.stdDelete(conn, stdNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	@Override
	public int stdUpdate(int stdNo, String stdName, int stdAge, String stdGender, String stdScore) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.stdUpdate(conn, stdNo, stdName, stdAge, stdGender, stdScore);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);

		return result;
	}

}
