package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import work.model.dto.IdeaBoard;
import work.model.dto.Member;
import work.model.interfaces.InterfaceBoard;
/**
 * �� ��� ���� class
 * 
 * ��ũ��, �� ����, ����, ��ȸ
 * @author HANA
 *
 */

public class FunctionDao {
	private FactoryDao factory = FactoryDao.getInstance();
	private static FunctionDao instance = new FunctionDao();

	/**
	 * �⺻������ context ȯ�� ���� ��������.
	 */
	private FunctionDao() {
	}
	/**
	 * Singleton ����
	 * @return
	 */
	public static FunctionDao getInstance() {
		return instance;
	}
	/**
	 * �� ��ũ�� ���
	 * @param index
	 * @return
	 */
	/*
	public String scrapIdea(String index) {

		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "select * from scrap_boards where = (select idx=? from boards_idx)";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			System.out.println("�������� :: ");
			if (rs.next()) {
				String boardIndex = rs.getString(2);
				String wIndex = rs.getString(3);
				String title = rs.getString(4);
				String content = rs.getString(5);
				String result = rs.getString(6);
				String files = rs.getString(7);
				String writeDate = rs.getString(8);
				String writer = rs.getString(9);

				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage() + "// ��ȸ����ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;

		return index;

	}*/
	
}
