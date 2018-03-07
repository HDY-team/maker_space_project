/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.model.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.PreparedStatement;


import work.model.dto.Member;
import work.model.service.MemberService;

/**
 * * 회원테이블에 대한 DAO(Data Access Object)클래스
 * 1) JDBC Driver loading
 * 2) DB 서버 연결
 * 3) SQL 통로 개설
 * 4) SQL 구문 수행 요청
 * 4) 결과 처리
 * 5) 자원 해제
 */

/**
 * ## 기능
 * 1. 회원추가
 * 2. 회원삭제
 * 3. 회원정보수정 (비밀번호, 휴대폰번호, 소속)
 * 4. 로그인
 * 5. 이메일찾기 (사용 X)
 * 6. 비밀번호변경
 * 7. 회원정보조회
 * 8. 회원삭제(관리자)
 */
public class MemberDao {

	private FactoryDao factory = FactoryDao.getInstance();
	private static MemberDao instance = new MemberDao();
	/**
	 * 기본생성자
	 * context 환경 정보 가져오기.
	 */
	private MemberDao() {
	}
	/**
	 * Singleton 패턴
	 * @return
	 */
	public static MemberDao getInstance() {
		return instance;
	}
	
	/**
	 * 회원추가
	 * @param dto
	 * @return
	 */
	public int insertMember(Member dto) {
		System.out.println("dto: " + dto.toString());
		String email = dto.getEmail();
		String password = dto.getPassword();
		String name = dto.getName();
		String mobile = dto.getMobile();
		String company = dto.getCompany();
		String grade = dto.getGrade();
		int point = dto.getPoint();
		
		Connection conn= null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "insert into members values(?, ?, ?, ?, ?, ?, ?)";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, mobile);
			pstmt.setString(5, company);
			pstmt.setString(6, grade);
			pstmt.setInt(7, point);
			pstmt.executeUpdate();
			return 1;
		} catch(SQLException e){
			System.out.println("Error: " + e.getMessage() + "// 회원추가 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	/**
	 * 회원삭제
	 * @param email
	 * @param password
	 * @return
	 */
	public int removeMember(String email, String password) {
		
		Connection conn= null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "delete members where email=? and password=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
			return 1;
		} catch(SQLException e){
			System.out.println("Error: " + e.getMessage() + "// 회원 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	/**
	 * 회원정보수정
	 * @param email
	 * @param password
	 * @param mobile
	 * @return
	 */
	public int changeMemberInfo(String email, String password, String mobile, String company) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "update members set password =?, mobile=? company=? where email=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, mobile);
			pstmt.setString(3, company);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			return 1;
		} catch(SQLException e){
			System.out.println("Error: " + e.getMessage() + "// 회원수정 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	
	/**
	 * 로그인
	 * @param email
	 * @param password
	 * @return
	 */
	public String loginMember(String email, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			String sql = "select grade from members where email=? and password=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String grade= rs.getString(1);
				return grade;
			}
		} catch(SQLException e) {
				System.out.println("Error: " + e.getMessage() + "// 로그인 오류");
				e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}	
		return null;
	}
	
	/**
	 * 이메일 찾기 (사용 x)
	 * @param name
	 * @param mobile
	 * @return
	 */
	public String findMemberEmail(String name, String mobile) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			String sql = "select email from members where name=? and mobile=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, mobile);
			rs = pstmt.executeQuery();		
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage() + "// 이메일찾기 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}	
		return null;
	}
	/**
	 * 비밀번호 변경
	 * @param email
	 * @param password
	 * @return
	 */
	public int setPassword(String email, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "update members set password =? where email=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
			return 1;
			
		} catch(SQLException e) {
			System.out.println("Error: " + e.getMessage() + "// 비밀번호 찾기 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}	
		return 0;
	}
	/**
	 * 회원정보조회
	 * @param email
	 * @return
	 */
	public Member getMemberOne(String email) {
		System.out.println("email:" + email );
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			String sql = "select * from members where email=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String password = rs.getString(2);
				String name = rs.getString(3);
				String mobile = rs.getString(4);
				String company = rs.getString(5);
				String grade = rs.getString(6);
				int point = rs.getInt(7);
				return new Member(email, password, name, mobile, company, grade, point);
			}
		} catch(SQLException e) {
				System.out.println("Error: " + e.getMessage() + "// 상세회원조회 오류");
				e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}	
		return null;
	}		
	
	/**
	 * 전체회원조회(관리자영역)
	 * @return
	 */
	public ArrayList<Member> getMembers() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			String sql = "select * from members";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<Member> members = new ArrayList<Member>();
			
			String email, password, name, mobile, company, grade;
			int point;

			while(rs.next()) {
				email = rs.getString(1);
				password = rs.getString(2);
				name = rs.getString(3);
				mobile = rs.getString(4);
				company = rs.getString(5);
				grade = rs.getString(6);
				point = rs.getInt(7);
				members.add( new Member(email, password, name, mobile, company, grade, point));
			}
			return members;
		} catch(SQLException e) {
						System.out.println("Error: " + e.getMessage() + "// 전체회원조회 오류");
						e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}	
		return null;
	}
	
	/**
	 * 회원삭제 (관리자)
	 * @param email
	 * @param password
	 * @return
	 */
	public int removeMemberByAdmin(String email) {
		
		Connection conn= null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "delete members where email=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.executeUpdate();
			return 1;
		} catch(SQLException e){
			System.out.println("Error: " + e.getMessage() + "// 회원 삭제(관리자) 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
}
