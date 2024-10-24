package kh.edu.stdList.service;

import java.util.List;

import kh.edu.stdList.dto.Student;

public interface StdListService {

	List<Student> stdListFullView() throws Exception;

	Student stdDetailView(int todoNo) throws Exception;

	int stdAdd(String stdName, int stdAge, String stdGender, String stdScore) throws Exception;

	int stdDelete(int stdNo) throws Exception;

	int stdUpdate(int stdNo, String stdName, int stdAge, String stdGender, String stdScore) throws Exception;

	
	
	
	
}
