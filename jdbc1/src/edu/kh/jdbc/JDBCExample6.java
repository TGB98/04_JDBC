package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCExample6 {

	public static void main(String[] args) {
		// 아이디, 비밀번호, 이름을 입력받아
		// 아이디, 비밀번호가 일치하는 사용자(TB_USER)의
		// 이름을 수정(UPDATE)
		
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
			
			System.out.print("아이디 입력 : ");
			String id = sc.next();
			
			System.out.print("비밀번호 입력 : ");
			String pw = sc.next();
			
			System.out.print("수정할 이름 입력 : ");
			String name = sc.next();
			
			String sql = "UPDATE TB_USER SET USER_NAME = ? WHERE USER_ID = ? AND USER_PW = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) { // "수정 성공" + COMMIT
				System.out.println("이름 수정 성공.");
				conn.commit();
				
			}
			else { // 실패 시 "아이디 또는 비밀번호 불일치" + ROLLBACK
				System.out.println("아이디 또는 비밀번호 불일치");
				conn.rollback();
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
