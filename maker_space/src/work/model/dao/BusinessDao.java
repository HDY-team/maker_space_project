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
import work.model.dto.TipIdeaBoard;
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
	@SuppressWarnings("resource")
	public int registerTipBoard(String category, TipIdeaBoard dto) {
		String title = dto.getTitle();
		String content = dto.getContent();
		String result = dto.getResult();
		String files = dto.getFiles();
		String email = dto.getEmail();
		String writeDate = dto.getWriteDate();
		String name = dto.getName();
		int hits = dto.getHits();
		int scraps = dto.getScraps();
		String sql;
		System.out.println(dto.toString());
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			if (category.equals("tips")) {
					sql = "insert into tip_boards "
							+ "(title, content, result, files, hits,scraps, email, write_date,name)"
							+ "values(?, ?, ?, ?, ?, ?, ?, ?,?)";

			} else {
					return 0;
				}
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				pstmt.setString(3, result);
				pstmt.setString(4, files);
				pstmt.setInt(5, hits);
				pstmt.setInt(6, scraps);
				pstmt.setString(7, email);
				pstmt.setString(8, writeDate);
				pstmt.setString(9, name);
				pstmt.executeUpdate();
				
				sql = "select last_insert_id() 'tip_idx'";
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getInt("tip_idx");
				}
			
		} catch (SQLException e) {
			System.out.println("Error : BusinessDao registerBoard ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return 0;
	}
	
	public int registerMyBoard(String category, int boardIdx) {
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			System.out.println("�����õ� : boardIdx = "+boardIdx);
			if (category.equals("tips")) {
				sql = "insert into tip_my_boards"
						+ "(tip_idx)"
						+"values (?)";

			} else if (category.equals("it") || category.equals("market") || category.equals("media") || category.equals("etc")) {
					sql = "insert into my_idea_boards"
							+ "(business_boards_idx)"
							+"values (?)";
			} else {
				return 0;
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			int result = pstmt.executeUpdate();
			System.out.println("��������? : boardIdx = "+boardIdx+"result : " +result);	
			return result;

		} catch (SQLException e) {
			System.out.println("Error : BusinessDao registerMyBoard ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	public int registerMySelect(String category, int boardIdx) {
		String sql;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			System.out.println("�����õ� : boardIdx = "+boardIdx);
			if(category.equals("select")) {
			sql = "insert into select_boards"
					+ "(business_boards_idx)"
					+"values (?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			}
			int result = pstmt.executeUpdate();
			return result;

		} catch (SQLException e) {
			System.out.println("Error : BusinessDao registerMySelect ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
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
			pstmt.executeUpdate();
			return 1;
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
			String sql = "delete business_boards where business_boards_idx=?";
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
	 * �Խ��� ����
	 * 
	 * @param boardIdx
	 * @return
	 */
	public int removeFromMyBoard(int businessBoardsIdx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "delete from my_idea_boards where business_boards_idx=?";
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

	public ArrayList<IdeaBoard> findBoardTitle(String title) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<IdeaBoard> list = new ArrayList<IdeaBoard>();

		try {
			conn = factory.getConnection();
			String sql = "select * from business_boards where title like '%?%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int index = rs.getInt("business_boards_idx");
				String content = rs.getString("content");
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
			} else if(category.equals("myIdea")) {
				String sql = "select business_boards.* from business_boards inner join my_idea_boards on business_boards.business_boards_idx = my_idea_boards.business_boards_idx";
				pstmt = conn.prepareStatement(sql);
				
			} else if(category.equals("myTips")) {
				String sql = "select tip_boards.* from tip_boards inner join tip_my_boards on tip_boards.tip_idx = tip_my_boards.tip_idx";
				pstmt = conn.prepareStatement(sql);
				System.out.println("���� ���� ��");
			} else if(category.equals("select")) {
				String sql = "select business_boards.* from business_boards inner join select_boards on business_boards.business_boards_idx = select_boards.business_boards_idx";
				pstmt = conn.prepareStatement(sql);
				System.out.println("���� ���� ��");
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
			if(!category.equals("myTips")) {
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
			} else {
				for (int i = 0; i < listSize; i++) {
					{
						int tipBoardsIdx = rs.getInt("tip_idx");
						String title = rs.getString("title");
						String content = rs.getString("content");
						String result = rs.getString("result");
						String files = rs.getString("files");
						int hits = rs.getInt("hits");
						int scraps = rs.getInt("scrpas");
						String email = rs.getString("email");
						String writeDate = rs.getString("write_date");
						String name = rs.getString("name");
						lists.add(new TipIdeaBoard(tipBoardsIdx, title, content, result, files, hits,writeDate,email, scraps,
								name));
						if (!rs.next()) {
							break;
						}
					}
				}
			}
			
			return lists;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return lists;
	}
	
	public ArrayList<TipIdeaBoard> getTipBoards(int currentPage, int listSize, String category) { 
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<TipIdeaBoard> lists = new ArrayList<TipIdeaBoard>();
		try {
			conn = factory.getConnection();

			if(category.equals("myTips")) { // ���� �ۼ��� ���� ��ƺ��� �Խ���
				String sql = "select tip_boards.* from tip_boards inner join tip_my_boards on tip_boards.tip_idx = tip_my_boards.tip_idx";
				pstmt = conn.prepareStatement(sql);
				System.out.println("���� ���� ��");
			} else if(category.equals("tips")) { // ���� ��üȮ�� �Խ���
				String sql = "select * from tip_boards order by tip_idx desc ";
				pstmt = conn.prepareStatement(sql);
				System.out.println("���� ���� ��");
			} 
			rs = pstmt.executeQuery();

			int abPage = (currentPage - 1) * listSize + 1;
			if (!rs.next()) {
				listSize = 0;
			} else {
				rs.absolute(abPage);
			}
			if(!category.equals("etc")) {
				for (int i = 0; i < listSize; i++) {
					{
						int tipBoardsIdx = rs.getInt("tip_idx");
						String title = rs.getString("title");
						String content = rs.getString("content");
						String result = rs.getString("result");
						String files = rs.getString("files");
						int hits = rs.getInt("hits");
						int scraps = rs.getInt("scrpas");
						String email = rs.getString("email");
						String writeDate = rs.getString("write_date");
						String name = rs.getString("name");
						lists.add(new TipIdeaBoard(tipBoardsIdx, title, content, result, files, hits,writeDate,email, scraps,
								name));
						if (!rs.next()) {
							break;
						}
					}
				}
			} 
			
			return lists;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return lists;
	
	}
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
	public int getTotalTipCount() {
		ResultSet result;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();

			String sql = "select count(*) from tip_boards";
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
	 * business ���̺�
	 * 
	 * @param tableName
	 * @param currentPage
	 * @param listSize
	 * @return
	 */
	public IdeaBoard getBoard(int businessBoardsIndex) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "select * from business_boards where business_boards_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, businessBoardsIndex);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int businessBoardsIdx = rs.getInt("business_boards_idx");
				System.out.println(businessBoardsIdx);
				String title = rs.getString("title");
				String content = rs.getString("content");
				String result = rs.getString("result");
				String files = rs.getString("files");
				int hits = rs.getInt("hits");
				String email = rs.getString("email");
				String writeDate = rs.getString("write_date");
				String name = rs.getString("name");
				return new IdeaBoard(businessBoardsIdx, title, content, result, files, hits, email, writeDate, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	public TipIdeaBoard getTipBoard(int tipBoardsIndex) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "select * from tip_boards where tip_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tipBoardsIndex);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int tipIndex = rs.getInt("tip_idx");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String result = rs.getString("result");
				String files = rs.getString("files");
				int hits = rs.getInt("hits");
				int scraps = rs.getInt("scraps");
				String email = rs.getString("email");
				String writeDate = rs.getString("write_date");
				String name = rs.getString("name");
				return new TipIdeaBoard(tipIndex, title, content, result, files, hits, writeDate,email, scraps, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
}