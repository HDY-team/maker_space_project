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

	private FactoryDao factory = FactoryDao.getInstance();
	private static HashTagDao instance = new HashTagDao();
	/**
	 * �⺻������
	 */
	private HashTagDao() {
	}
	/**
	 * Singleton ����
	 * @return
	 */
	public static HashTagDao getInstance() {
		return instance;
	}
	/**
	 * �Խ��� �ؽ��±��߰�
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
			System.out.println("Error: " + e.getMessage() + "// �ؽ��±��߰� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	/**
	 * �Խ��� �ؽ��±׻���
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
			System.out.println("Error: " + e.getMessage() + "// �ؽ��±� ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	/**
	 * �Խ��� �� �ؽ��±� ���� �˻� 
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
				System.out.println("Error: " + e.getMessage() + "// �Խ��� �� �ؽ��±� ���� ����");
				e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
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
