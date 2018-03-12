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
 * 글 기능 관련 class
 * 
 * 스크랩, 글 수정, 삭제, 조회
 * @author HANA
 *
 */

public class FunctionDao {
	private FactoryDao factory = FactoryDao.getInstance();
	private static FunctionDao instance = new FunctionDao();

	/**
	 * 기본생성자 context 환경 정보 가져오기.
	 */
	private FunctionDao() {
	}
	/**
	 * Singleton 패턴
	 * @return
	 */
	public static FunctionDao getInstance() {
		return instance;
	}
	/**
	 * 글 스크랩 기능
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
			System.out.println("쿼리성공 :: ");
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
			System.out.println("Error: " + e.getMessage() + "// 상세회원조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;

		return index;

	}*/
	
}
