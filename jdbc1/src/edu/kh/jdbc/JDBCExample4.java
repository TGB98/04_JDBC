package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample4 {

	public static void main(String[] args) {

		// 부서명을 입력받아
		// 해당 부서에 근무하는 사원의
		// 사번, 이름, 부서명, 직급명을 조회
		// 직급코드 오름차순 정렬
		
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			
			String type = "jdbc:oracle:thin:@"; // 드라이버의 종류.
			
			String host = "localhost"; // DB 서버 컴퓨터의 IP주소 또는 도메인 주소.
									   // localhost == 현재 컴퓨터.
			
			String port = ":1521"; // 프로그램 연결을 위한 포트번호.
			
			String dbName = ":XE"; // DBMS 버전 이름(XE == eXpress Edition)
			// jdbc:oracle:thin:@localhost:1521:XE
			
			String userName = "kh_mkj"; // 사용자 계정명
			
			String password = "kh1234";
			
			conn = DriverManager.getConnection(type+host+port+dbName, userName, password);
			
			System.out.print("부서명 입력 : ");
			String dept = sc.next();
			
			String sql = "SELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '없음') DEPT_TITLE, JOB_NAME FROM EMPLOYEE LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID) JOIN JOB USING(JOB_CODE) WHERE DEPT_TITLE = '" + dept + "'" + " ORDER BY JOB_CODE";

			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			int count = 0;
			
			boolean flag = true;
			// 조회 결과가 있다면 false, 없으면 true
			
			while(rs.next()) {
				flag = false;
				
				count++;
				
				String empId = rs.getString("EMP_ID");
				
				String empName = rs.getString("EMP_NAME");
				
				String deptTitle = rs.getString("DEPT_TITLE");
				
				String jobName = rs.getString("JOB_NAME");
				
				System.out.printf("%s / %s / %s / %s\n", empId, empName, deptTitle, jobName);
				
			}
			
			if(flag) { // flag == true == while 문이 수행되지 않았음.
				System.out.println("일치하는 부서가 없습니다.");
			}
			
			System.out.println("총 인원 : " + count + "명");
			
		
		} catch(Exception e) {
			
			e.printStackTrace();
			
		} finally {
			try {
				
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
