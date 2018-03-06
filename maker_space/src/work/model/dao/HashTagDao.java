/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import work.model.dto.Member;

/**
 * 해시태그 Dao
 * 1. 게시판 해시태그 추가(최대 5개)
 * 2. 게시판 해시태그 삭제
 * 3. 해시태그 개수 검색 (인기별, 게시판 내)
 */
public class HashTagDao {

	private DataSource ds;
	private static HashTagDao instance;

	/**
	 * 기본생성자
	 */
	public HashTagDao() {
		try {
			Context context = new InitialContext();
	         ds = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
		} catch(NamingException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	/**
	 * Singleton 패턴
	 * @return
	 */
	public static HashTagDao getInstance() {
		if(instance == null) {
			instance = new HashTagDao();
		}
		return instance;
	}
	/**
	 * 게시판 해시태그추가
	 * @param boardIdx
	 * @param hashTag
	 * @return
	 */
	public int insertHashTag(String boardIdx, String hashTag) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();		
			String sql = "insert into hashtags values(?, ?)";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, boardIdx);
			pstmt.setString(2, hashTag);
			pstmt.executeUpdate();
			return 1;
		} catch(SQLException e){
			System.out.println("Error: " + e.getMessage() + "// 해시태그추가 오류");
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) { 
					conn.close();
				}
			} catch(SQLException e) {
				System.out.println("Error : 해시태그추가 자원 해제 오류");
				e.printStackTrace();
			}
		}
		return 0;
	}
	/**
	 * 게시판 해시태그삭제
	 * @param boardIdx
	 * @param hashTag
	 * @return
	 */
	public int deleteHashTag(String boardIdx, String hashTag) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "delete hashtags where boardIdx=? and hashTag=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, boardIdx);
			pstmt.setString(2, hashTag);
			pstmt.executeUpdate();
			return 1;
		} catch(SQLException e){
			System.out.println("Error: " + e.getMessage() + "// 해시태그 삭제 오류");
			e.printStackTrace();
		} finally {
			try {
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) { 
						conn.close();
					}
			} catch(SQLException e) {
				System.out.println("Error : 해시태그 자원해제 오류");
				e.printStackTrace();
			}
		}
		return 0;
	}
	/**
	 * 게시파 내 해시태그 개수 검색 
	 * @param boardIdx
	 * @return
	 */
	public int countHashTagInBoard(String boardIdx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select count(*) from hashtags where business_boards_idx=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, boardIdx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int count= rs.getInt(1);
				return count;
			}
		} catch(SQLException e) {
				System.out.println("Error: " + e.getMessage() + "// 게시판 당 해시태그 개수 오류");
				e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) { 
					conn.close();
				}
			} catch(SQLException e) {
				System.out.println("Error : 게시판 당 해시태그 개수 자원해제 오류");
				e.printStackTrace();
			}
		}	
		return 0;
	}
	/**
	 * 인기별 해시태그 (상의 후 개발 -> insert 부분 변경해야함.)
	 * @return
	 */
	//TODO 개발예정
	public ArrayList<String> getPopularHashTag() {
		
		return null;
	}
}
