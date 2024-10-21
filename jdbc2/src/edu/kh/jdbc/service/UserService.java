package edu.kh.jdbc.service;

// import static : 지정된 경로에 존재하는 static 구문을 모두 얻어와
// 클래스명.메서드명() 이 아닌 메서드명() 만 작성해도 호출 가능하게 함.
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dao.UserDAO;
import edu.kh.jdbc.dto.User;

public class UserService {
	
	// 필드
	private UserDAO dao = new UserDAO();

	/**
	 * 전달 받은 아이디와 일치하는 User 정보 반환 서비스
	 * @param inputId (입력된 아이디)
	 * @return 아이디가 일치하는 회원 정보, 없으면 null 반환.
	 */
	public User selectId(String inputId) {
		
		// 커넥션 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO 메서드 호출 후 결과 반환 받기
		User user = dao.selectId(conn, inputId);
		
		// 다 쓴 Connection 자원 반환.
		JDBCTemplate.close(conn);
		
		return user; // DB 조회 결과 반환.
	}

	/**
	 * User 등록 서비스
	 * @param user : 입력받은 id, pw, name 세팅된 객체
	 * @return 삽입 성공한 결과 행의 개수
	 * @throws Exception 
	 */
	public int insertUser(User user) throws Exception {
		
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. 데이터 가공(할 게 없으면 넘어가도 됨)
		
		// 3. DAO 메서드 호출(INSERT) 후 결과(삽입 성공한 행의 개수, int) 반환 받기.
		int result = dao.insertUser(conn, user);
		
		// 4. INSERT 수행 결과에 따라 트랜잭션 제어 처리
		if(result > 0) { // INSERT 성공
			commit(conn);
		}
		else { // INSERT 실패
			rollback(conn);
		}
		
		// 5. Connection 자원 반환.
		close(conn);
		
		// 6. 결과 반환.
		return result;
		
	}

	/**
	 * User 전체 조회 서비스.
	 * @return 조회된 User가 담겨있는 List
	 */
	public List<User> selectAll() throws Exception {
		
		// 1. Connection 생성하기
		Connection conn = getConnection();
		
		// 2. 데이터 가공(없으면 넘어감)
		
		// 3. DAO 메서드 호출(SELECT) 후 결과 반환(List<User>) 받기.
		List<User> userList = dao.selectAll(conn);
		
		// 4. DML인 경우 트랜잭션 제어 처리 (SELECT이면 넘어가도 됨)
		
		// 5. Connection 반환.
		close(conn);
		// 6. 결과 반환.
		return userList;
	}

	/**
	 * User 이름 중 검색어가 포함된 회원 조회 서비스.
	 * @param inputName 
	 * @return 조회된 User가 담겨있는 List
	 */
	public List<User> selectName(String inputName) throws Exception {
		// 1. Connection 생성하기.
		Connection conn = getConnection();
		
		// 2. 데이터 가공
		
		// 3. DAO 메서드 호출(SELECT) 후 결과 반환 받기.
		List<User> userList = dao.selectName(conn, inputName);
		
		// 4. DML인 경우 트랜잭션 제어 처리.
		
		// 5. Connection 반환.
		close(conn);
		
		return userList;
	}

	public User selectUser(int inputUserNo) throws SQLException {
		// 1. 커넥션 생성.
		Connection conn = getConnection();
		
		// 2. 데이터 가공
		
		// 3. DAO 메서드 호출(SELECT) 후 결과 반환 받기.
		User user = dao.selectUser(conn, inputUserNo);
		
		// 4. DML인 경우 트랜잭션 처리.
		
		// 5. Connection 반환.
		close(conn);
		
		return user;
	}

	/**
	 * USER_NO으로 User 삭제 서비스.
	 * @param delUserNo
	 * @return
	 */
	public int deleteUser(int delUserNo) throws Exception {
		// 1. 커넥션 생성
		Connection conn = getConnection();
		
		// 2. 데이터 가공
		
		// 3. DAO 메서드 호출(DML : DELETE) 후 결과 반환 받기.
		int result = dao.deleteUser(conn, delUserNo);
		
		// 4. DML인 경우 트랜잭션 처리.
		if (result > 0) { // DELETE 성공
			commit(conn);
		}
		else { // DELETE 실패
			rollback(conn);
		}
		// 5. Connection 반환.
		close(conn);

		return result;
	}

	public int updateName(String inputId, String inputPw) throws Exception {

		// 1. 커넥션 생성.
		Connection conn = getConnection();
		
		// 2. 데이터 가공
		
		// 3. DAO 메서드 호출(DML : UPDATE) 후 결과 반환 받기.
		int result = dao.updateName(conn, inputId, inputPw);
		
		// 4. DML인 경우 트랜잭션 처리.
		if (result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		// 5. Connection 반환.
		close(conn);
		
		// 6. 결과 반환
		return result;
	}

}
