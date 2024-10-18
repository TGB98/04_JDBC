package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample5 {

	public static void main(String[] args) {
		
		// 아이디, 비밀번호, 이름을 입력 받아서
		// TB_USER 테이블에 삽입(INSERT) 하기.
		
		// 1. jdbc 객체 참조 변수 선언하기
		Connection conn = null;
		
		PreparedStatement pstmt = null;
		// java.sql.PreparedStatement 
		// - SQL 중간에 ? (위치홀더, placeholder)를 작성해서
		// ? 자리에 java 값을 대입할 준비가 되어있는 Statement.
		
		/*
		 * 장점 1. SQL 작성이 간단해진다.
		 * 장점 2. ?에 값 대입 시 자료형에 맞는 형태의 리터럴 표기법으로 대입 됨.
		 * 		-> ex) String 대입 -> '값' (자동으로 자료형에 맞게 대입 된다.)
		 * 		-> ex) int 대입 -> 값
		 * 장점 3. 성능, 속도에서 일반 Statement 보다 우위를 가지고 있다.
		 * ** PreparedStatement 는 Statement 의 자식 **
		 * 
		 * */
		
		// SELECT 가 아니기 때문에 ResultSet 이 필요 없음.
		
		try {
			// 2. DriverManager 이용해서 Connection 객체 생성.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userName = "kh_mkj";
			String password = "kh1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			
			// 3. SQL 작성
			Scanner sc = new Scanner(System.in);
			
			System.out.print("아이디 입력 : ");
			String id = sc.nextLine();
			
			System.out.print("비밀번호 입력 : ");
			String pw = sc.next();
			
			System.out.print("이름 입력 : ");
			String name = sc.next();
			
			String sql = "INSERT INTO TB_USER VALUES(SEQ_USER_NO.NEXTVAL, ?, ?, ?, DEFAULT)";
			
			/*
			 * AutoCommit 끄기.
			 * -> 개발자가 트랜잭션을 마음대로 제어하기 위해서.
			 * 
			 * */
			conn.setAutoCommit(false);
			
			// 4. PreparedStatement 객체 생성.
			// -> 객체 생성과 동시에 SQL이 담겨짐.
			// -> 미리 ? (placeholder)에 값을 받을 준비를 해야되기 때문에.
			pstmt = conn.prepareStatement(sql);
			
			// 5. ? 위치홀더에 알맞은 값 대입.
			// pstmt.set자료형(?순서 , 대입할 값)
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			// -> 여기까지 실행하면 SQL이 작성 완료된 상태.
			
			// 6. SQL(INSERT) 수행 후 결과(int) 반환 받기.
			// executeQuery()  : SELECT 수행, ResultSet 반환.
			// executeUpdate() : DML(INSERT,UPDATE,DELETE) 수행, 결과 행 개수(int) 반환.
			// -> DML 실패 시 0, 성공 시 0 초과 된 값 반환.
			
			// PreparedStatement 를 이용해 SQL 실행 시
			// executeQuery() / executeUpdate() 매개변수 자리에 아무 것도 없어야 한다.
			int result = pstmt.executeUpdate();
			
			// 7. result 값에 따른 결과 처리 + 트랜잭션 제어 처리.
			if(result > 0) { // INSERT 성공 시
				System.out.println(name + "님이 추가 되었습니다.");
				conn.commit(); // COMMIT 수행 -> DB에 INSERT 영구 반영.
			} else {
				System.out.println("추가 실패.");
				conn.rollback(); // 실패 시 ROLLBACK
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		} finally {
			try {
				// 8. 사용한 JDBC 객체 자원 반환.
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(Exception e) {
				
				e.printStackTrace();
				
			}
		}
		
		
		
		
		
	}

}
