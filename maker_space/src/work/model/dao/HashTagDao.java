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
 * �ؽ��±� Dao
 * 1. �Խ��� �ؽ��±� �߰�(�ִ� 5��)
 * 2. �Խ��� �ؽ��±� ����
 * 3. �ؽ��±� ���� �˻� (�α⺰, �Խ��� ��)
 */
public class HashTagDao {

	private DataSource ds;
	private static HashTagDao instance;

	/**
	 * �⺻������
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
	 * Singleton ����
	 * @return
	 */
	public static HashTagDao getInstance() {
		if(instance == null) {
			instance = new HashTagDao();
		}
		return instance;
	}
	/**
	 * �Խ��� �ؽ��±��߰�
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
			System.out.println("Error: " + e.getMessage() + "// �ؽ��±��߰� ����");
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
				System.out.println("Error : �ؽ��±��߰� �ڿ� ���� ����");
				e.printStackTrace();
			}
		}
		return 0;
	}
	/**
	 * �Խ��� �ؽ��±׻���
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
			System.out.println("Error: " + e.getMessage() + "// �ؽ��±� ���� ����");
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
				System.out.println("Error : �ؽ��±� �ڿ����� ����");
				e.printStackTrace();
			}
		}
		return 0;
	}
	/**
	 * �Խ��� �� �ؽ��±� ���� �˻� 
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
				System.out.println("Error: " + e.getMessage() + "// �Խ��� �� �ؽ��±� ���� ����");
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
				System.out.println("Error : �Խ��� �� �ؽ��±� ���� �ڿ����� ����");
				e.printStackTrace();
			}
		}	
		return 0;
	}
	/**
	 * �α⺰ �ؽ��±� (���� �� ���� -> insert �κ� �����ؾ���.)
	 * @return
	 */
	//TODO ���߿���
	public ArrayList<String> getPopularHashTag() {
		
		return null;
	}
}
