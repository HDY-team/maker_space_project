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

import work.model.dto.IdeaBoard;
import work.model.dto.Member;
import work.model.interfaces.InterfaceBoard;
import work.model.service.MemberService;

/**
 * Business Idea Board Dao - ��� : �� ���(business, AllBusiness), ����, ����, ��ȸ - test
 */
public class BusinessDao implements InterfaceBoard {
	/** Factory Pattern : Connection, close() */
	private FactoryDao factory = FactoryDao.getInstance();
	private static BusinessDao instance = new BusinessDao();

	/**
	 * �⺻������
	 */
	private BusinessDao() {
	}

	/**
	 * Singleton ����
	 * 
	 * @return
	 */
	public static BusinessDao getInstance() {
		return instance;
	}

	/**
	 * business �� ��� �޼���. index: ���̺� index(����о� �ε���) title: �� ���� content: �� ����
	 * result: ���ȿ�� files: ���� hits: ��ȸ�� email: �̸��� writeDate: �ۼ���
	 * 
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("resource")
	@Override
	public int registerBoard(String category, IdeaBoard dto) {
		String title = dto.getTitle();
		String content = dto.getContent();
		String result = dto.getResult();
		String files = dto.getFiles();
		String email = dto.getEmail();
		String writeDate = dto.getWriteDate();
		String name = dto.getName();
		int hits = dto.getHits();
		String sql;
		System.out.println(dto.toString());
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			if (!category.equals("tip")) {
				if (category.equals("it")) {
					sql = "insert into business_boards "
							+ "(business_idx, title, content, result, files, hits, email, write_date, name)"
							+ "values(1, ?, ?, ?, ?, ?, ?, ?, ?)";
				} else if (category.equals("media")) {
					sql = "insert into business_boards "
							+ "(business_idx, title, content, result, files, hits, email, write_date, name)"
							+ "values(2, ?, ?, ?, ?, ?, ?, ?, ?)";
				} else if (category.equals("maket")) {
					sql = "insert into business_boards "
							+ "(business_idx, title, content, result, files, hits, email, write_date, name)"
							+ "values(3, ?, ?, ?, ?, ?, ?, ?, ?)";
				} else if (category.equals("etc")) {
					sql = "insert into business_boards "
							+ "(business_idx, title, content, result, files, hits, email, write_date)"
							+ "values(4, ?, ?, ?, ?, ?, ?, ?, ?)";

				} else {
					return 0;
				}
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				pstmt.setString(3, result);
				pstmt.setString(4, files);
				pstmt.setInt(5, hits);
				pstmt.setString(6, email);
				pstmt.setString(7, writeDate);
				pstmt.setString(8, name);
				pstmt.executeUpdate();

				sql = "select last_insert_id() 'boards_index'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getInt("boards_index");
				}
			}
		} catch (SQLException e) {
			System.out.println("Error : BusinessDao registerBoard ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return 0;
	}

	/**
	 * �� ���� �޼��� ���� �� �� �ִ� �κ� index, title, content, result , files �κи� ������Ʈ �ȴ�.
	 * 
	 * @param dto
	 * @return
	 */
	public int changeBoard(int businessBoardsIdx, String title, String content, String result, String files) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "update business_boards set "
					+ "title=?, content=?, result=?, files=? where business_boards_idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, result);
			pstmt.setString(4, files);
			pstmt.setInt(5, businessBoardsIdx);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : �� ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}

	/**
	 * �Խ��� ����
	 * 
	 * @param boardIdx
	 * @return
	 */
	public int removeBoard(int businessBoardsIdx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "delete from business_boards where business_boards_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, businessBoardsIdx);
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage() + "// ���̺� ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}

	/**
	 * �� ��ȸ ��� ���� 1) �߰� 2) ���� 3) ���� 4) ��ȸ
	 * 
	 * @param name,
	 *            title, content, hashTag
	 * @return
	 */
	@SuppressWarnings("resource")
	public ArrayList<IdeaBoard> findBoardName(String name) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<IdeaBoard> list = new ArrayList<IdeaBoard>();

		try {
			conn = factory.getConnection();
			String sql = "select email from members where name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String email = rs.getString(name);
				sql = "select * from business_boards where email like '%?%'";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int index = rs.getInt("business_boards_idx");
					String title = rs.getString("title");
					String content = rs.getString("content");
					String result = rs.getString("result");
					String files = rs.getString("files");
					int hits = rs.getInt("hits");
					String writeDate = rs.getString("write_date");
					list.add(new IdeaBoard(index, title, content, result, files, hits, email, writeDate, name));
				}
				return list;
			}
		} catch (SQLException e) {
			System.out.println("Error : �� �̸� �˻� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}

	@SuppressWarnings("unused")
	public ArrayList<IdeaBoard> findBoardTitle(int currentPage, int listSize, String title, String category) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<IdeaBoard> lists = new ArrayList<IdeaBoard>();
		try {
			conn = factory.getConnection();
			if (category.equals("it")) {
				String sql = "select * from business_boards where business_idx=1 and title like ? order by business_boards_idx desc ";
				pstmt = conn.prepareStatement(sql);
			} else if (category.equals("media")) {
				String sql = "select * from business_boards where business_idx=2 and title like ? order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
			} else if (category.equals("market")) {
				String sql = "select * from business_boards where business_idx=3 and title like ? order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
			} else {
				String sql = "select * from business_boards where business_idx=4 and title like ? order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
			}
			pstmt.setString(1, "%" + title + "%");
			rs = pstmt.executeQuery();
			int abPage = (currentPage - 1) * listSize + 1;
			if (!rs.next()) {
				listSize = 0;
			} else {
				rs.absolute(abPage);
			}
			for (int i = 0; i < listSize; i++) {
				if (!rs.next()) {
					break;
				} else {
					
					int businessBoardsIdx = rs.getInt("business_boards_idx");
					String content = rs.getString("content");
					String result = rs.getString("result");
					String files = rs.getString("files");
					int hits = rs.getInt("hits");
					String email = rs.getString("email");
					String writeDate = rs.getString("write_date");
					String name = rs.getString("name");
					lists.add(
							new IdeaBoard(businessBoardsIdx, title, content, result, files, hits, email, writeDate, name));
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

	public ArrayList<IdeaBoard> findBoardContent(String content) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<IdeaBoard> list = new ArrayList<IdeaBoard>();

		try {
			conn = factory.getConnection();
			String sql = "select * from business_boards where content like '%?%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int index = rs.getInt("business_boards_idx");
				String title = rs.getString("title");
				String result = rs.getString("result");
				String files = rs.getString("files");
				int hits = rs.getInt("hits");
				String email = rs.getString("email");
				String writeDate = rs.getString("write_date");
				String name = rs.getString("name");
				list.add(new IdeaBoard(index, title, content, result, files, hits, email, writeDate, name));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("Error : �� ���� �˻� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}

	public ArrayList<IdeaBoard> findBoardHashTag(String hashTag) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<IdeaBoard> list = new ArrayList<IdeaBoard>();
		ArrayList<Integer> indexBoards = new ArrayList<Integer>();
		Integer integer;
		String sql;
		try {
			conn = factory.getConnection();
			sql = "select business_boards_idx from hashtags where hash_tag like '%?%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hashTag);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				integer = new Integer(rs.getInt("business_boards_idx"));
				indexBoards.add(integer);
			}
			for (int i = 0; i < indexBoards.size(); i++) {
				sql = "select * from business_boards where business_boards_idx =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, indexBoards.get(i).intValue());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int index = rs.getInt("business_boards_idx");
					String title = rs.getString("title");
					String content = rs.getString("content");
					String result = rs.getString("result");
					String files = rs.getString("files");
					int hits = rs.getInt("hits");
					String email = rs.getString("email");
					String writeDate = rs.getString("write_date");
					String name = rs.getString("name");
					list.add(new IdeaBoard(index, title, content, result, files, hits, email, writeDate, name));
				}
			}
			if (list.size() != 0) {
				return list;
			}
		} catch (SQLException e) {
			System.out.println("Error : �� �ؽ��±�  �̸� �˻� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}

	/**
	 * business ���̺�
	 * 
	 * @param tableName
	 * @param currentPage
	 * @param listSize
	 * @return
	 */
	public ArrayList<IdeaBoard> getBoards(int currentPage, int listSize, String category) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<IdeaBoard> lists = new ArrayList<IdeaBoard>();
		try {
			conn = factory.getConnection();

			if (category.equals("it")) {
				String sql = "select * from business_boards where business_idx=1 order by business_boards_idx desc ";
				pstmt = conn.prepareStatement(sql);
			} else if (category.equals("media")) {
				String sql = "select * from business_boards where business_idx=2 order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
			} else if (category.equals("market")) {
				String sql = "select * from business_boards where business_idx=3 order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
			} else {
				String sql = "select * from business_boards where business_idx=4 order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
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
					lists.add(new IdeaBoard(businessBoardsIdx, title, content, result, files, hits, email, writeDate,
							name));
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
	 * boards count �޼���
	 * 
	 * @return
	 */
	public int getTotalCount() {
		ResultSet result;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();

			String sql = "select count(*) from business_boards";
			pstmt = conn.prepareStatement(sql);
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

	/**
	 * boards count �޼���
	 * 
	 * @return
	 */
	public int getSearchTitleCount(String title) {
		ResultSet result;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();
			String sql = "select count(*) from business_boards where title like '%?%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
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

	/**
	 * business ���̺�
	 * 
	 * @param tableName
	 * @param currentPage
	 * @param listSize
	 * @return
	 */
	public IdeaBoard getBoard(int businessBoardsIdx, String category) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "select * from business_boards where business_boards_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, businessBoardsIdx);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String result = rs.getString("result");
				String files = rs.getString("files");
				int hits = rs.getInt("hits");
				String email = rs.getString("email");
				String writeDate = rs.getString("write_date");
				String name = rs.getString("name");
				if (category.equals("it")) {
					return new IdeaBoard(1, businessBoardsIdx, title, content, result, files, hits, email, writeDate,
							name);
				} else if (category.equals("media")) {
					return new IdeaBoard(2, businessBoardsIdx, title, content, result, files, hits, email, writeDate,
							name);
				} else if (category.equals("market")) {
					return new IdeaBoard(3, businessBoardsIdx, title, content, result, files, hits, email, writeDate,
							name);
				} else if (category.equals("etc")) {
					return new IdeaBoard(4, businessBoardsIdx, title, content, result, files, hits, email, writeDate,
							name);
				} else {
					return null;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}

	/**
	 * hits ������Ʈ �޼���
	 * 
	 * @param businessBoardsIdx
	 * @return
	 */
	@SuppressWarnings("resource")
	public int updateHits(int businessBoardsIdx) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql;
			int hits = 0;
			sql = "select hits from business_boards where business_boards_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, businessBoardsIdx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				hits = rs.getInt("hits");
			}
			sql = "update business_boards set " + "hits=? where business_boards_idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ++hits);
			pstmt.setInt(2, businessBoardsIdx);
			pstmt.executeUpdate();
			return hits;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return 0;
	}

}