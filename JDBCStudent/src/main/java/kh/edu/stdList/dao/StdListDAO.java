package kh.edu.stdList.dao;

import java.sql.Connection;
import java.util.List;

import kh.edu.stdList.dto.Student;

public interface StdListDAO {

	List<Student> stdListFullView(Connection conn) throws Exception;

	Student stdDetailView(Connection conn, int todoNo) throws Exception;

	int stdDelete(Connection conn, int stdNo) throws Exception;

	int stdUpdate(Connection conn, int stdNo, String stdName, int stdAge, String stdGender, String stdScore) throws Exception;

	int stdGoToAdd(Connection conn, String stdName, int stdAge, String stdGender, String stdScore) throws Exception;

}
