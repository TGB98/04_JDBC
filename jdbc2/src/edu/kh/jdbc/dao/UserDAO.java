package edu.kh.jdbc.dao;

import static edu.kh.jdbc.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dto.User;

// DAO (Data Access Object)
// 데이터가 저장된 곳에 접근하는 용도의 객체.
// -> DB에 접근하여 Java에서 원하는 결과를 얻기 위해
//    SQL을 수행하고 결과를 반환 받는 역할.
public class UserDAO {
	
	// 필드
	// - DB 접근 관련한 JDBC 객체 참조 변수 미리 선언.
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
	 * 전달 받은 Connection을 이용해서 DB에 접근하여
	 * 전달 받은 아이디와 일치하는 User 정보 조회 하는 메서드.
	 * @param conn : Service에서 생성한 Connection 객체.
	 * @param inputId : View에서 입력 받은 아이디.
	 * @return 아이디가 일치하는 회원의 User 또는 null
	 */
	public User selectId(Connection conn, String inputId) {
		
		User user = null; // 결과 저장용 변수.
		
		try {
			
			// SQL 작성/
			String sql = "SELECT * FROM TB_USER WHERE USER_ID = ?";
			
			// PreparedStatement 객체 생성/
			pstmt = conn.prepareStatement(sql);
			
			// ?(위치 홀더)에 알맞은 값 대입.
			pstmt.setString(1, inputId);
			
			// SQL 수행 후 결과 반환 받기.
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을 경우
			// -> 중복되는 아이디가 없을 경우
			//	1행만 조회 되기 때문에 while문 보다는 if를 사용하는게 효과적.
			if(rs.next()) {
				// 첫 행에 데이터가 존재한다면
				
				// 각 컬럼의 값 얻어오기
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				
				// java.sql.Date 활용.
				Date enrollDate = rs.getDate("ENROLL_DATE");
				
				// 조회된 컬럼 값을 이용해서 User 객체 생성.
				user = new User(userNo, userId, userPw, userName, enrollDate.toString());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
				// 사용한 JDBC 객체 자원 반환.
				JDBCTemplate.close(rs);
				JDBCTemplate.close(pstmt);
				
				// Connection 객체는 Service에서 close를 한다.
				// -> Service에서 Connection 객체를 생성했기 때문.
		}
		return user; // 결과 반환 (생성된 User 또는 null)
	}

	/**
	 * User 등록 DAO 메서드.
	 * @param conn : DB 연결 정보가 담겨있는 Connection 객체. 
	 * @param user : 입력 받은 id,pw,name이 세팅된 User 객체.
	 * @return INSERT 결과 행의 개수
	 */
	public int insertUser(Connection conn, User user) throws Exception{
		
		// SQL 수행 중 발생하는 예외를
		// catch로 처리하지 않고, throws를 이용해 호출부로 던져서 처리.
		// -> catch 문 필요 없다.
		
		// 1. 결과 저장용 변수 선언
		int result = 0;
		
		try {
			
			// 2. SQL 작성.
			String sql = "INSERT INTO TB_USER VALUES (SEQ_USER_NO.NEXTVAL, ?, ?, ?, DEFAULT)";
			
			// 3. PreparedStatement 객체 생성.
			pstmt = conn.prepareStatement(sql);
			
			// 4. ?(위치홀더)에 알맞은 값 대입.
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			
			// 5. SQL 수행 후 결과 값(삽입된 행의 개수) 반환 받기.
			result = pstmt.executeUpdate();
			
		} finally {
			// 6. 사용한 JDBC 객체 자원 반환.
			close(pstmt);
			
			
		}
		// 결과 저장용 변수에 저장된 값 반환.
		
		return result;
	}

	/**
	 * User 전체 조회 DAO 메서드 
	 * @param conn
	 * @return userList
	 * @throws Exception
	 */
	public List<User> selectAll(Connection conn) throws Exception {
		
		// 1. 결과 저장용 변수 선언.
		// List 같은 컬렉션을 반환하는 경우,
		// 변수 선언 시 객체도 같이 생성해두는게 좋다.
		List<User> userList = new ArrayList<User>();
		
		try {
			// 2. SQL 작성.
			String sql = """
					SELECT USER_NO, USER_ID, USER_PW, USER_NAME,
					TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"') ENROLL_DATE
					FROM TB_USER
					ORDER BY USER_NO ASC
					""";
			
			// 3. PreparedStatement 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ?에 알맞은 값 대입 (없으면 패스)
			
			// 5. SQL(SELECT)수행(executeQuery()) 후 결과(ResultSet) 반환 받기.
			rs = pstmt.executeQuery();
			
			// 6. 조회 결과를 1행 씩 접근해 컬럼 값 얻어오기.
			// 몇 행이 조회될지 모른다 -> while
			// 무조건 1행이 조회 된다 -> if
			while(rs.next()) {
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");
				// - java.sql.Date 타입으로 값을 저장하지 않은 이유
				// -> TO_CHAR()를 이용해 문자열로 변환했기 때문.
				
				// userList 에 추가.
				// -> User 객체를 생성해 조회된 값을 담고
				// userList에 추가.
				User user = new User(userNo, userId, userPw, userName, enrollDate);
				userList.add(user);
				
//				userList.add(new User(userNo, userId, userPw, userName, enrollDate));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		// 조회 결과가 담긴 List 반환.
		return userList;
	}

	public List<User> selectName(Connection conn, String inputName) throws Exception {
		
		// 1. 결과 저장용 변수 선언.
		List<User> userList = new ArrayList<User>();
		
		// 2. sql 작성.
		try {
			String sql = """
					SELECT USER_NO, USER_ID, USER_PW, USER_NAME, TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"') ENROLL_DATE
					FROM TB_USER
					WHERE USER_NAME LIKE
					""" + "'%" + inputName + "%'";
			
			// 3. PreparedStatement 생성.
			pstmt = conn.prepareStatement(sql);
			
			// 4. ?에 값 대입
//			pstmt.setString(1, inputName);
			// 5. SQL 수행(SELECT) 후 결과(ResultSet) 반환 받기.
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");
				
				User user = new User(userNo, userId, userPw, userName, enrollDate);

				userList.add(user);
			}
			
		} finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return userList;
	}

	public User selectUser(Connection conn, int inputUserNo) throws SQLException {
		// 1. 결과 저장용 변수 선언.
		User user = null;
		
		try {
			// 2. SQL문 작성.
			String sql = """
					SELECT USER_NO, USER_ID, USER_PW, USER_NAME,
					TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"') ENROLL_DATE
					FROM TB_USER
					WHERE USER_NO = 
					""" + inputUserNo;
			
			// 3. PrepareStatement 생성.
			pstmt = conn.prepareStatement(sql);
			
			// 4. ?에 값 대입
//			pstmt.setInt(1, inputUserNo);
			
			// 5. SQL 실행(SELECT) 후 결과 반환 받기.
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");
				
				user = new User(userNo, userId, userPw, userName, enrollDate);
			}
		} 
		finally {
			
			close(rs);
			close(pstmt);
			
		}

		return user;
	}

	/**
	 * USER_NO을 입력받아 일치하는 USER 삭제
	 * @param conn
	 * @param delUserNo
	 * @return
	 */
	public int deleteUser(Connection conn, int delUserNo) throws Exception {
		// 1. 결과 저장용 변수
		int result = 0;
		
		try {
			// 2. sql 작성.
			String sql = "DELETE FROM TB_USER WHERE USER_NO = " + delUserNo;
			
			// 3. PrepareStatement 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ? 에 값 대입
			
			// 5. SQL (DELETE)실행 후 결과값 반환 받기
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}

		return result;
	}

	public int updateName(Connection conn, String inputId, String inputPw) throws Exception {

		// 1. 결과 저장용 변수
		int result = 0;
		
		try {
			// 2. sql 작성
			
			String sql = "UPDATE TB_USER SET USER_NAME = '' WHERE USER_ID = '' AND USER_PW = ''"
			
			
		} finally {
			
		}
		
		return 0;
	}

}
