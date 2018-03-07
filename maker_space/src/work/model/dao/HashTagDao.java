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

	private FactoryDao factory = FactoryDao.getInstance();
	private static HashTagDao instance = new HashTagDao();
	/**
	 * 기본생성자
	 */
	private HashTagDao() {
	}
	/**
	 * Singleton 패턴
	 * @return
	 */
	public static HashTagDao getInstance() {
		return instance;
	}
	/**
	 * 게시판 해시태그추가
	 * @param boardIdx
	 * @param hashTag
	 * @return
	 */
	public int insertHashTag(int boardIdx, String hashTag) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();		
			String sql = "insert into hashtags values(?, ?)";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			pstmt.setString(2, hashTag);
			pstmt.executeUpdate();
			return 1;
		} catch(SQLException e){
			System.out.println("Error: " + e.getMessage() + "// 해시태그추가 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	/**
	 * 게시판 해시태그삭제
	 * @param boardIdx
	 * @param hashTag
	 * @return
	 */
	public int deleteHashTag(int boardIdx, String hashTag) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "delete hashtags where boardIdx=? and hashTag=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			pstmt.setString(2, hashTag);
			pstmt.executeUpdate();
			return 1;
		} catch(SQLException e){
			System.out.println("Error: " + e.getMessage() + "// 해시태그 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	/**
	 * 게시파 내 해시태그 개수 검색 
	 * @param boardIdx
	 * @return
	 */
	public int countHashTagInBoard(int boardIdx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			String sql = "select count(*) from hashtags where business_boarfactory_idx=?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int count= rs.getInt(1);
				return count;
			}
		} catch(SQLException e) {
				System.out.println("Error: " + e.getMessage() + "// 게시판 당 해시태그 개수 오류");
				e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
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
