package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import work.model.dto.IdeaBoard;

public class ProcessDao {

	/** Factory Pattern : Connection, close() */
	private FactoryDao factory = FactoryDao.getInstance();
	private static ProcessDao instance = new ProcessDao();

	/**
	 * 기본생성자
	 */
	private ProcessDao() {
	}

	/**
	 * Singleton 패턴
	 * 
	 * @return
	 */
	public static ProcessDao getInstance() {
		return instance;
	}

	/**
	 * business 테이블
	 * 
	 * @param tableName
	 * @param currentPage
	 * @param listSize
	 * @return
	 */
	public ArrayList<IdeaBoard> getProcessBoards(int currentPage, int listSize, String category, String _email) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<IdeaBoard> lists = new ArrayList<IdeaBoard>();
		try {
			conn = factory.getConnection();

			if (category.equals("mySelected")) {
				//String sql = "select business_boards.* from business_boards inner join my_selected_boards on business_boards.business_boards_idx = my_selected_boards.business_boards_idx where my_selected_boards.email=?";
				String sql = "select business_boards.* from business_boards inner join my_selected_boards on business_boards.business_boards_idx"
						+ " = my_selected_boards.business_boards_idx where my_selected_boards.email=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, _email);
				rs = pstmt.executeQuery();
			} else if (category.equals("mySelect")) {
				String sql = "select * from business_boards where email=? and process=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, _email);
				pstmt.setInt(2, 1);
				rs = pstmt.executeQuery();
			} else if (category.equals("selectComplete")) {
				String sql = "select business_boards.* from business_boards inner join select_complete_boards on business_boards.business_boards_idx"
						+ " = select_complete_boards.business_boards_idx where select_complete_boards.email=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, _email);
				rs = pstmt.executeQuery();
				int abPage = (currentPage - 1) * listSize + 1;
				if (!rs.next()) {
					listSize = 0;
				} else {
					rs.absolute(abPage);
				}
				for (int i = 0; i < listSize; i++) {
					{
						int businessBoardsIdx = rs.getInt("business_boards_idx");
						String title = rs.getString("title");
						String content = rs.getString("content");
						String result = rs.getString("result");
						String files = rs.getString("files");
						int hits = rs.getInt("hits");
						String email = rs.getString("email");
						String writeDate = rs.getString("write_date");
						String name = rs.getString("name");
						int process = rs.getInt("process");
						lists.add(new IdeaBoard(businessBoardsIdx, title, content, result, files, hits, email, writeDate,
								name, process));
						if (!rs.next()) {
							break;
						}
					}
					sql = "select * from business_boards where email=? and process=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, _email);
					pstmt.setInt(2, 3);
				}
			}
			rs = pstmt.executeQuery();
			int abPage = (currentPage - 1) * listSize + 1;
			if (!rs.next()) {
				listSize = 0;
			} else {
				rs.absolute(abPage);
			}
			for (int i = 0; i < listSize; i++) {
				{
					int businessBoardsIdx = rs.getInt("business_boards_idx");
					String title = rs.getString("title");
					String content = rs.getString("content");
					String result = rs.getString("result");
					String files = rs.getString("files");
					int hits = rs.getInt("hits");
					String email = rs.getString("email");
					String writeDate = rs.getString("write_date");
					String name = rs.getString("name");
					int process = rs.getInt("process");
					lists.add(new IdeaBoard(businessBoardsIdx, title, content, result, files, hits, email, writeDate,
							name, process));
					if (!rs.next()) {
						break;
					}
				}
			}
			return lists;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}

	/**
	 * business 테이블
	 * 
	 * @param tableName
	 * @param currentPage
	 * @param listSize
	 * @return
	 */
	public IdeaBoard getProcessBoard(int businessBoardsIdx, String category) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql;
			conn = factory.getConnection();
			sql = "select * from business_boards where business_boards_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, businessBoardsIdx);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int businessIdx = rs.getInt("business_idx");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String result = rs.getString("result");
				String files = rs.getString("files");
				int hits = rs.getInt("hits");
				String email = rs.getString("email");
				String writeDate = rs.getString("write_date");
				String name = rs.getString("name");
				int process = rs.getInt("process");
				return new IdeaBoard(businessIdx, businessBoardsIdx, title, content, result, files, hits, email, writeDate, name, process);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}

	public int getTotalMySelectedCount(String _email, int _process) {
		ResultSet result;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "select count(*) from business_boards inner join my_selected_boards on business_boards.business_boards_idx = my_selected_boards.business_boards_idx where my_selected_boards.email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _email);
			result = pstmt.executeQuery();
			if (result.next()) {
				return result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}

	public int getTotalMySelectCount(String email, int process) {
		ResultSet result;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();

			String sql = "select count(*) from business_boards where email=? and process=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, process);
			result = pstmt.executeQuery();
			if (result.next()) {
				return result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}

	public int getTotalSelectCompleteCount(String email, int process) {
		ResultSet result;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "select count(*) from business_boards where email=? and process=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, process);
			result = pstmt.executeQuery();
			if (result.next()) {
				return result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	
	@SuppressWarnings("resource")
	public int mySelectedCancel(int businessBoardsIdx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "delete from my_selected_boards where business_boards_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, businessBoardsIdx);
			pstmt.executeUpdate();
			
			sql = "update business_boards set process=? where business_boards_idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, businessBoardsIdx);
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage() + "// 테이블 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	
	@SuppressWarnings("resource")
	public int mySelectCancel(int businessBoardsIdx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "delete from my_selected_boards where business_boards_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, businessBoardsIdx);
			pstmt.executeUpdate();
			
			 sql = "delete from my_select_boards where business_boards_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, businessBoardsIdx);
			pstmt.executeUpdate();
			
			sql = "update business_boards set process=? where business_boards_idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, businessBoardsIdx);
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage() + "// 테이블 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	
	@SuppressWarnings("resource")
	public int mySelectedComplete(int businessBoardsIdx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
//			String sql = "delete from my_select_boards where business_boards_idx=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, businessBoardsIdx);
//			pstmt.executeUpdate();
			
			String sql = "update business_boards set process=? where business_boards_idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 2);
			pstmt.setInt(2, businessBoardsIdx);
			pstmt.executeUpdate();
			
			return 1;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage() + "// 테이블 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	@SuppressWarnings("resource")
	public int complete(int businessBoardsIdx, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "delete from my_select_boards where business_boards_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, businessBoardsIdx);
			pstmt.executeUpdate();
			
			sql = "delete from my_selected_boards where business_boards_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, businessBoardsIdx);
			pstmt.executeUpdate();
			
			sql = "update business_boards set process=? where business_boards_idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 3);
			pstmt.setInt(2, businessBoardsIdx);
			pstmt.executeUpdate();
			
			sql = "insert into select_complete_boards values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, businessBoardsIdx);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
			
			return 1;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage() + "// 테이블 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
}
