package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBCExample7 {

	public static void main(String[] args) {
		// EMPLOYEE	테이블에서
		// 사번, 이름, 성별, 급여, 직급명, 부서명을 조회
		// 단, 입력 받은 조건에 맞는 결과만 조회하고 정렬할 것
		
		// - 조건 1 : 성별 (M, F)
		// - 조건 2 : 급여 범위
		// - 조건 3 : 급여 오름차순/내림차순
		
		// [실행화면]
		// 조회할 성별(M/F) : F
		// 급여 범위(최소, 최대 순서로 작성) : 3000000 4000000
		// 급여 정렬(1.ASC, 2.DESC) : 2
		
		// 사번 | 이름   | 성별 | 급여    | 직급명 | 부서명
		//--------------------------------------------------------
		// 218  | 이오리 | F    | 3890000 | 사원   | 없음
		// 203  | 송은희 | F    | 3800000 | 차장   | 해외영업2부
		// 212  | 장쯔위 | F    | 3550000 | 대리   | 기술지원부
		// 222  | 이태림 | F    | 3436240 | 대리   | 기술지원부
		// 207  | 하이유 | F    | 3200000 | 과장   | 해외영업1부
		// 210  | 윤은해 | F    | 3000000 | 사원   | 해외영업1부 
		
		Connection conn = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userName = "kh_mkj";
			String password = "kh1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			
			conn.setAutoCommit(false);
			
			System.out.print("조회할 성별(M/F) : ");
			String gender = sc.next().toUpperCase();
			
			System.out.print("급여 범위(최소, 최대 순서로 작성) : ");
			int min = sc.nextInt();
			int max = sc.nextInt();
			
			System.out.print("급여 정렬(1.ASC, 2.DESC) : ");
			int sort = sc.nextInt();
			
			String sql = "SELECT EMP_ID, EMP_NAME, DECODE( SUBSTR(EMP_NO, 8, 1), '1', 'M', '2', 'F' ) GENDER, SALARY, JOB_NAME, NVL(DEPT_TITLE, '없음') DEPT_TITLE"
					+ " FROM EMPLOYEE LEFT JOIN DEPARTMENT ON(DEPT_ID = DEPT_CODE) JOIN JOB USING(JOB_CODE) WHERE DECODE( SUBSTR(EMP_NO, 8, 1), '1', 'M', '2', 'F' ) = ? AND SALARY BETWEEN ? AND ? ORDER BY SALARY";
			
			// -> placeholder -> String -> 리터럴표기법 ''
			// -> int -> 값
			
			// 입력 받은 정렬(sort) 값에 따라 sql에 오름/내림 차순 SQL 추가하기.
			if(sort == 1) sql += " ASC";
			else sql += " DESC";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, gender);
			pstmt.setInt(2, min);
			pstmt.setInt(3, max);
			
			rs = pstmt.executeQuery();
			

			System.out.println("사번 | 이름   | 성별 | 급여    | 직급명 | 부서명");
			System.out.println("--------------------------------------------------------");
			
			boolean flag = true; // true : 조회결과 없음, false : 조회 결과 존재함.
			
			while(rs.next()) {
				
				flag = false;
				
				String empId = rs.getString("EMP_ID");
				
				String empName = rs.getString("EMP_NAME");
				
				String empGender = rs.getString("GENDER");
				
				int empSalary = rs.getInt("SALARY");
				
				String jobName = rs.getString("JOB_NAME");
				
				String deptTitle = rs.getString("DEPT_TITLE");
				
				System.out.printf("%-4s | %3s | %-4s | %7d | %-3s | %s \n", empId, empName, empGender, empSalary, jobName, deptTitle);
			}
			
			if(flag) { // flag == true 인 경우 -> while 문 수행 x -> 조회 결과 없음.
				
				System.out.println("조회 결과 없음.");
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}

}
