package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample2 {

	public static void main(String[] args) {

		// 입력 받은 급여보다 초과해서 받는 사원의
		// 사번, 이름, 급여 를 조회해서 이클립스 콘솔창에 출력.
		
		// 1. JDBC 객체 참조용 변수 선언.
		Connection conn = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		// 2. DriverManager 객체를 이용해서 Connection 객체 생성.
		// 2-1 ) Oracle JDBC Driver 객체 메모리 로드.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2-2 ) DB 연결 정보를 작성.
			String type = "jdbc:oracle:thin:@"; // 드라이버 종류
			
			String host = "localhost";
			
			String port = ":1521";
			
			String dbName = ":XE";
			
			String userName = "kh_mkj";
			
			String password = "kh1234";
			
			// 2-3 ) DB 연결 정보와 DriverManager 를 이용해 Connection 객체를 생성.
			conn = DriverManager.getConnection(type+host+port+dbName, userName, password);
			
			// 3. SQL 문을 작성.
			// 입력 받은 급여 -> Scanner 필요.
			// int input 여기에 급여 담기.
			Scanner sc = new Scanner(System.in);
			System.out.print("급여 입력 : ");
			int input = sc.nextInt();
			
			String sql = "SELECT EMP_ID, EMP_NAME, SALARY FROM EMPLOYEE WHERE SALARY > " + input;
			
			// 4. Statement 객체 생성.
			stmt = conn.createStatement();
			
			// 5. Statement 객체를 이용해 SQL 수행 후 결과 반환 받기.
			// excuteQuery() : SELECT 실행, ResultSet 반환.
			// excuteUpdate() : DML 실행, 결과 행의 개수를 반환(int)함.
			rs = stmt.executeQuery(sql);
			
			// 6. 조회 결과가 담겨있는 ResultSet 을 Cursor 이용해 1행 씩 접근하고 각 행에 작성된 컬럼 값 얻어오기.
			// -> while 문 안에서 꺼낸 데이터 출력.
			
			//	201 / 송종기 / 6000000원
			//	202 / 노옹철 / 3700000원
			// ...
			while(rs.next()) {
				
				String empId = rs.getString("EMP_ID");
				
				String empName = rs.getString("EMP_NAME");
				
				int salary = rs.getInt("SALARY");
				
				System.out.printf("사번 : %s / 이름 : %s / 급여 : %d원 \n", empId, empName, salary);
				
			}
			
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				// 7. 사용 완료된 JDBC 객체 자원 반환(close)
				// -> 생성된 역순으로 close 하기
				
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}
