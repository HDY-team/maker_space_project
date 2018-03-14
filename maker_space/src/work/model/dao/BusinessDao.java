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
 * Business Idea Board Dao - 기능 : 글 등록(business, AllBusiness), 수정, 삭제, 조회 - test
 */
public class BusinessDao implements InterfaceBoard {
	/** Factory Pattern : Connection, close() */
	private FactoryDao factory = FactoryDao.getInstance();
	private static BusinessDao instance = new BusinessDao();

	/**
	 * 기본생성자
	 */
	private BusinessDao() {
	}

	/**
	 * Singleton 패턴
	 * 
	 * @return
	 */
	public static BusinessDao getInstance() {
		return instance;
	}

	/**
	 * business 글 등록 메서드. index: 테이블 index(사업분야 인덱스) title: 글 제목 content: 글 내용
	 * result: 기대효과 files: 파일 hits: 조회수 email: 이메일 writeDate: 작성일
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
							+ "values(3, ?, ?, ?, ?, ?, ?, ?, ?)";
				} else if (category.equals("market")) {
					sql = "insert into business_boards "
							+ "(business_idx, title, content, result, files, hits, email, write_date, name)"
							+ "values(2, ?, ?, ?, ?, ?, ?, ?, ?)";
				} else if (category.equals("etc")) {
					sql = "insert into business_boards "
							+ "(business_idx, title, content, result, files, hits, email, write_date, name)"
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
			System.out.println("Error : BusinessDao registerBoard 에러");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return 0;
	}

	/**
	 * 글 수정 메서드 변경 될 수 있는 부분 index, title, content, result , files 부분만 업데이트 된다.
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
			System.out.println("Error : 글 수정 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	public int changeTipBoard(int tipBoardsIdx, String title, String content, String result, String files) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "update tip_boards set "
					+ "title=?, content=?, result=?, files=? where tip_idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, result);
			pstmt.setString(4, files);
			pstmt.setInt(5, tipBoardsIdx);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : 글 수정 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	/**
	 * 게시판 삭제
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
			System.out.println("Error: " + e.getMessage() + "// 테이블 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	@SuppressWarnings("resource")
	public int removeTipBoard(int tipBoardsIdx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql = "delete from tip_scraps_boards where tip_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tipBoardsIdx);
			pstmt.executeUpdate();
			sql = "delete from tip_my_boards where tip_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tipBoardsIdx);
			pstmt.executeUpdate();
			sql = "delete from tip_boards where tip_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tipBoardsIdx);
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
	/**
	 * 제목으로 게시판 검색
	 */
	public ArrayList<IdeaBoard> findBoardName(int currentPage, int listSize, String searchContent, String category) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<IdeaBoard> lists = new ArrayList<IdeaBoard>();
		try {
			conn = factory.getConnection();
			if (category.equals("it")) {
				String sql = "select * from business_boards where business_idx=1 and name like ? order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + searchContent + "%");
			} else if (category.equals("market")) {
				String sql = "select * from business_boards where business_idx=2 and name like ? order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + searchContent + "%");
			} else if (category.equals("media")) {
				String sql = "select * from business_boards where business_idx=3 and name like ? order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + searchContent + "%");
			} else {
				String sql = "select * from business_boards where business_idx=4 and title like ? order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + searchContent + "%");
			}
			rs = pstmt.executeQuery();
			int abPage = (currentPage - 1) * listSize + 1;
			if (!rs.next()) {
				listSize = 0;
			} else {
				rs.absolute(abPage);
			}
			for (int i = 0; i < listSize; i++) {
				int businessBoardsIdx = rs.getInt("business_boards_idx");
				int businessIdx = rs.getInt("business_idx");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String result = rs.getString("result");
				String files = rs.getString("files");
				int hits = rs.getInt("hits");
				String email = rs.getString("email");
				String writeDate = rs.getString("write_date");
				String name = rs.getString("name");
				lists.add(new IdeaBoard(businessIdx, businessBoardsIdx, title, content, result, files, hits, email,
						writeDate, name));
				if (!rs.next()) {
					break;
				}
			}
			for (int i = 0; i < lists.size(); i++) {
				System.out.println("!!!" + lists.get(i).toString());
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
	 * 제목으로 게시판 검색
	 */
	@SuppressWarnings("unused")
	public ArrayList<IdeaBoard> findBoardContent(int currentPage, int listSize, String searchContent, String category) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<IdeaBoard> lists = new ArrayList<IdeaBoard>();
		try {
			conn = factory.getConnection();
			if (category.equals("it")) {
				String sql = "select * from business_boards where business_idx=1 and content like ? order by business_boards_idx desc ";
				pstmt = conn.prepareStatement(sql);
			} else if (category.equals("media")) {
				String sql = "select * from business_boards where business_idx=3 and content like ? order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
			} else if (category.equals("market")) {
				String sql = "select * from business_boards where business_idx=2 and content like ? order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
			} else {
				String sql = "select * from business_boards where business_idx=4 and content like ? order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
			}
			pstmt.setString(1, "%" + searchContent + "%");
			rs = pstmt.executeQuery();
			int abPage = (currentPage - 1) * listSize + 1;
			if (!rs.next()) {
				listSize = 0;
			} else {
				rs.absolute(abPage);
			}
			for (int i = 0; i < listSize; i++) {
				int businessBoardsIdx = rs.getInt("business_boards_idx");
				int businessIdx = rs.getInt("business_idx");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String result = rs.getString("result");
				String files = rs.getString("files");
				int hits = rs.getInt("hits");
				String email = rs.getString("email");
				String writeDate = rs.getString("write_date");
				String name = rs.getString("name");
				// public IdeaBoard(int businessIdx, int index, String title, String content,
				// String result, String files, int hits, String email, String writeDate, String
				// name)
				lists.add(new IdeaBoard(businessIdx, businessBoardsIdx, title, content, result, files, hits, email,
						writeDate, name));
				if (!rs.next()) {
					break;
				}
			}
			for (int i = 0; i < lists.size(); i++) {
				System.out.println("!!!" + lists.get(i).toString());
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
	 * 제목으로 게시판 검색
	 */
	@SuppressWarnings("unused")
	public ArrayList<IdeaBoard> findBoardTitle(int currentPage, int listSize, String searchContent, String category) {
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
				String sql = "select * from business_boards where business_idx=3 and title like ? order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
			} else if (category.equals("market")) {
				String sql = "select * from business_boards where business_idx=2 and title like ? order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
			} else {
				String sql = "select * from business_boards where business_idx=4 and title like ? order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
			}
			pstmt.setString(1, "%" + searchContent + "%");
			rs = pstmt.executeQuery();
			int abPage = (currentPage - 1) * listSize + 1;
			if (!rs.next()) {
				listSize = 0;
			} else {
				rs.absolute(abPage);
			}
			for (int i = 0; i < listSize; i++) {
				int businessBoardsIdx = rs.getInt("business_boards_idx");
				int businessIdx = rs.getInt("business_idx");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String result = rs.getString("result");
				String files = rs.getString("files");
				int hits = rs.getInt("hits");
				String email = rs.getString("email");
				String writeDate = rs.getString("write_date");
				String name = rs.getString("name");
				// public IdeaBoard(int businessIdx, int index, String title, String content,
				// String result, String files, int hits, String email, String writeDate, String
				// name)
				lists.add(new IdeaBoard(businessIdx, businessBoardsIdx, title, content, result, files, hits, email,
						writeDate, name));
				if (!rs.next()) {
					break;
				}
			}
			for (int i = 0; i < lists.size(); i++) {
				System.out.println("!!!" + lists.get(i).toString());
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
	 * tip board find
	 */
	/**
	 * 제목으로 게시판 검색
	 */
	public ArrayList<TipIdeaBoard> findTipBoardName(int currentPage, int listSize, String searchContent, String category) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<TipIdeaBoard> lists = new ArrayList<TipIdeaBoard>();
		try {
			conn = factory.getConnection();
			if (category.equals("tips")) {
				String sql = "select * from tip_boards where name like ? order by tip_idx desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + searchContent + "%");
			} else {
				return null;
			}
			rs = pstmt.executeQuery();
			int abPage = (currentPage - 1) * listSize + 1;
			if (!rs.next()) {
				listSize = 0;
			} else {
				rs.absolute(abPage);
			}
			for (int i = 0; i < listSize; i++) {
				int tipBoardsIdx = rs.getInt("tip_idx");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String result = rs.getString("result");
				String files = rs.getString("files");
				int hits = rs.getInt("hits");
				String email = rs.getString("email");
				String writeDate = rs.getString("write_date");
				String name = rs.getString("name");
				int scraps = rs.getInt("scraps");
				lists.add(new TipIdeaBoard(tipBoardsIdx, title, content, result, files, hits, email,
						writeDate, name, scraps));
				if (!rs.next()) {
					break;
				}
			}
			for (int i = 0; i < lists.size(); i++) {
				System.out.println("!!!" + lists.get(i).toString());
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
	 * 제목으로 게시판 검색
	 */
	@SuppressWarnings("unused")
	public ArrayList<TipIdeaBoard> findTipBoardContent(int currentPage, int listSize, String searchContent, String category) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<TipIdeaBoard> lists = new ArrayList<TipIdeaBoard>();
		try {
			conn = factory.getConnection();
			if (category.equals("tips")) {
				String sql = "select * from tip_boards where content like ? order by tip_idx desc";
				pstmt = conn.prepareStatement(sql);
			} else {
				return null;
			}
			pstmt.setString(1, "%" + searchContent + "%");
			rs = pstmt.executeQuery();
			int abPage = (currentPage - 1) * listSize + 1;
			if (!rs.next()) {
				listSize = 0;
			} else {
				rs.absolute(abPage);
			}
			for (int i = 0; i < listSize; i++) {
				int tipBoardsIdx = rs.getInt("tip_idx");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String result = rs.getString("result");
				String files = rs.getString("files");
				int hits = rs.getInt("hits");
				String email = rs.getString("email");
				String writeDate = rs.getString("write_date");
				String name = rs.getString("name");
				int scraps = rs.getInt("scraps");
				lists.add(new TipIdeaBoard(tipBoardsIdx, title, content, result, files, hits, email,
						writeDate, name, scraps));
				if (!rs.next()) {
					break;
				}
			}
			for (int i = 0; i < lists.size(); i++) {
				System.out.println("!!!" + lists.get(i).toString());
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
	 * 제목으로 게시판 검색
	 */
	@SuppressWarnings("unused")
	public ArrayList<TipIdeaBoard> findTipBoardTitle(int currentPage, int listSize, String searchContent, String category) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<TipIdeaBoard> lists = new ArrayList<TipIdeaBoard>();
		try {
			conn = factory.getConnection();
			if (category.equals("tips")) {
				String sql = "select * from tip_boards where title like ? order by tip_idx desc";
				pstmt = conn.prepareStatement(sql);
			} else {
				return null;
			}
			pstmt.setString(1, "%" + searchContent + "%");
			rs = pstmt.executeQuery();
			int abPage = (currentPage - 1) * listSize + 1;
			if (!rs.next()) {
				listSize = 0;
			} else {
				rs.absolute(abPage);
			}
			for (int i = 0; i < listSize; i++) {
				int tipBoardsIdx = rs.getInt("tip_idx");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String result = rs.getString("result");
				String files = rs.getString("files");
				int hits = rs.getInt("hits");
				String email = rs.getString("email");
				String writeDate = rs.getString("write_date");
				String name = rs.getString("name");
				int scraps = rs.getInt("scraps");
				lists.add(new TipIdeaBoard(tipBoardsIdx, title, content, result, files, hits, email,
						writeDate, name, scraps));
				if (!rs.next()) {
					break;
				}
			}
			for (int i = 0; i < lists.size(); i++) {
				System.out.println("!!!" + lists.get(i).toString());
			}
			return lists;
		} catch (SQLException e) {
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
			System.out.println("Error : 글 해시태그  이름 검색 오류");
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
	public ArrayList<IdeaBoard> getBoards(int currentPage, int listSize, String category, String _email) {
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
				String sql = "select * from business_boards where business_idx=3 order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
			} else if (category.equals("market")) {
				String sql = "select * from business_boards where business_idx=2 order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
			} else if (category.equals("etc")) {
				String sql = "select * from business_boards where business_idx=4 order by business_boards_idx desc";
				pstmt = conn.prepareStatement(sql);
			} else { // my board에서 인자를 이메일로 받아 왔을 때
				String sql = "select * from business_boards where email =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, _email);
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
	 * boards it count 메서드
	 * 
	 * @return
	 */
	public int getTotalItCount() {
		ResultSet result;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();

			String sql = "select count(*) from business_boards where business_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
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
	 * boards it count 메서드
	 * 
	 * @return
	 */
	public int getTotalMarketCount() {
		ResultSet result;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();

			String sql = "select count(*) from business_boards where business_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 2);
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
	 * boards it count 메서드
	 * 
	 * @return
	 */
	public int getTotalMediaCount() {
		ResultSet result;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();

			String sql = "select count(*) from business_boards where business_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 3);
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
	 * boards it count 메서드
	 * 
	 * @return
	 */
	public int getTotalEtcCount() {
		ResultSet result;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();

			String sql = "select count(*) from business_boards where business_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 4);
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
	 * boards title count 메서드
	 * 
	 * @return
	 */
	public int getSearchTitleCount(String title, String category) {
		ResultSet result;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql;
			if(category.equals("it")) {
				sql = "select count(*) from business_boards where title like ? and business_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + title + "%");
				pstmt.setInt(2, 1);
			} else if(category.equals("market")) {
				sql = "select count(*) from business_boards where title like ? and business_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + title + "%");
				pstmt.setInt(2, 2);
			} else if(category.equals("media")) {
				sql = "select count(*) from business_boards where title like ? and business_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + title + "%");
				pstmt.setInt(2, 3);
			} else if(category.equals("etc")) {
				sql = "select count(*) from business_boards where title like ? and business_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + title + "%");
				pstmt.setInt(2, 4);
			} else {
				return 0;
			}
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
	 * boards content count 메서드
	 * 
	 * @param title
	 * @return
	 */
	public int getSearchContentCount(String content, String category) {
		ResultSet result;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			String sql;
			conn = factory.getConnection();
			if(category.equals("it")) {
				sql = "select count(*) from business_boards where content like ? and business_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + content + "%");
				pstmt.setInt(2, 1);
			} else if(category.equals("market")) {
				sql = "select count(*) from business_boards where content like ? and business_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + content + "%");
				pstmt.setInt(2, 2);
			} else if(category.equals("media")) {
				sql = "select count(*) from business_boards where content like ? and business_idx";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + content + "%");
				pstmt.setInt(2, 3);
			} else if(category.equals("etc")) {
				sql = "select count(*) from business_boards where content like ? and business_idx";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + content + "%");
				pstmt.setInt(2, 4);
			} else {
				return 0;
			}
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
	 * boards name count 메서드
	 * 
	 * @param title
	 * @return
	 */
	public int getSearchNameCount(String name, String category) {
		ResultSet result;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();
			String sql;
			if(category.equals("it")) {
				sql = "select count(*) from business_boards where name like ? and business_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
				pstmt.setInt(2, 1);
			} else if(category.equals("market")) {
				sql = "select count(*) from business_boards where name like ? and business_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
				pstmt.setInt(2, 2);
			} else if(category.equals("media")) {
				sql = "select count(*) from business_boards where name like ? and business_idx";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
				pstmt.setInt(2, 3);
			} else if(category.equals("etc")) {
				sql = "select count(*) from business_boards where name like ? and business_idx";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
				pstmt.setInt(2, 4);
			} else {
				return 0;
			}
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
	 * Tip find count
	 */
	public int getTipSearchTitleCount(String title, String category) {
		ResultSet result;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql;
			sql = "select count(*) from tip_boards where title like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + title + "%");
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
	 * boards content count 메서드
	 * 
	 * @param title
	 * @return
	 */
	public int getTipSearchContentCount(String content, String category) {
		ResultSet result;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			String sql;
			conn = factory.getConnection();
			sql = "select count(*) from tip_boards where content like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + content + "%");
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
	 * boards name count 메서드
	 * 
	 * @param title
	 * @return
	 */
	public int getTipSearchNameCount(String name, String category) {
		ResultSet result;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();
			String sql;
			sql = "select count(*) from tip_boards where name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
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
	 * business 테이블
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
					return new IdeaBoard(3, businessBoardsIdx, title, content, result, files, hits, email, writeDate,
							name);
				} else if (category.equals("market")) {
					return new IdeaBoard(2, businessBoardsIdx, title, content, result, files, hits, email, writeDate,
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
	 * business hits 업데이트 메서드
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
	/**
	 * tip hits 업데이트 메서드
	 * 
	 * @param businessBoardsIdx
	 * @return
	 */
	@SuppressWarnings("resource")
	public int updateTipHits(int tipBoardsIndex) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql;
			int hits = 0;
			sql = "select hits from tip_boards where tip_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tipBoardsIndex);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				hits = rs.getInt("hits");
			}
			sql = "update tip_boards set " + "hits=? where tip_idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ++hits);
			pstmt.setInt(2, tipBoardsIndex);
			pstmt.executeUpdate();
			return hits;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return 0;
	}
	/**
	 * scrap hits 업데이트 메서드
	 * 
	 * @param businessBoardsIdx
	 * @return
	 */
	@SuppressWarnings("resource")
	public int updateScrapHits(int tipBoardsIndex) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			String sql;
			int scraps = 0;
			sql = "select hits from tip_boards where tip_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tipBoardsIndex);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				scraps = rs.getInt("scraps");
			}
			sql = "update tip_boards set " + "scraps=? where tip_idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ++scraps);
			pstmt.setInt(2, tipBoardsIndex);
			pstmt.executeUpdate();
			return scraps;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return 0;
	}
	public int registerMySelect(String category, int boardIdx) {
		String sql;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			System.out.println("쿼리시도 : boardIdx = " + boardIdx);
			if (category.equals("select")) {
				sql = "insert into select_boards" + "(business_boards_idx)" + "values (?)";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardIdx);
			}
			int result = pstmt.executeUpdate();
			return result;

		} catch (SQLException e) {
			System.out.println("Error : BusinessDao registerMySelect 에러");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
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
				return new TipIdeaBoard(tipIndex, title, content, result, files, hits, email, writeDate, name, scraps);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	@SuppressWarnings("resource")
	public ArrayList<TipIdeaBoard> getTipBoards(int currentPage, int listSize, String category, String _email) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<TipIdeaBoard> lists = new ArrayList<TipIdeaBoard>();
		try {
			conn = factory.getConnection();

			if (category.equals("myTips")) { // 내가 작성한 꿀팁 모아보기 게시판
				String sql = "select * from tip_boards where email =? order by tip_idx desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, _email);
			} else if (category.equals("tips")) { // 꿀팁 전체확인 게시판
				String sql = "select * from tip_boards order by tip_idx desc";
				pstmt = conn.prepareStatement(sql);
			} else if (category.equals("scrap")) {
				String sql = "select tip_boards.* from tip_boards inner join tip_scraps_boards on tip_boards.tip_idx = tip_scraps_boards.tip_idx where tip_scraps_boards.email=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, _email);
			} 
			rs = pstmt.executeQuery();
			int abPage = (currentPage - 1) * listSize + 1;
			if (!rs.next()) {
				listSize = 0;
			} else {
				rs.absolute(abPage);
			}
			for (int i = 0; i < listSize; i++) {
				int tipBoardsIdx = rs.getInt("tip_idx");
				System.out.println(">>>!!: " +rs.getInt("tip_idx"));
				String title = rs.getString("title");
				String content = rs.getString("content");
				String result = rs.getString("result");
				String files = rs.getString("files");
				int hits = rs.getInt("hits");
				int scraps = rs.getInt("scraps");
				String email = rs.getString("email");
				String writeDate = rs.getString("write_date");
				String name = rs.getString("name");
				lists.add(new TipIdeaBoard(tipBoardsIdx, title, content, result, files, hits, email, writeDate, name, scraps));
				if (!rs.next()) {
					break;
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
	/**
	 * 나의 팁 테이블 개수 메서드
	 * 
	 * @param email
	 * @return
	 */
	public int getTotalMyScrapsCount(String email) {
		ResultSet result;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();
			
			String sql = "select count(*) from tip_scraps_boards where email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
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
	 * 나의 팁 테이블 개수 메서드
	 * 
	 * @param email
	 * @return
	 */
	public int getTotalMyTipCount(String email) {
		ResultSet result;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();

			String sql = "select count(*) from tip_boards where email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
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
	public int getTotalMyCount(String email) {
		ResultSet result;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();

			String sql = "select count(*) from business_boards where email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
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
	 * 꿀팁 스크랩 메서드
	 * 
	 * @param category
	 * @param boardIdx
	 * @return
	 */
	public int registerMyScraps(String category, int tipBoardsIndex, String email) {
		String sql;
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println("category myscraps: " + category);
		try {
			conn = factory.getConnection();
			System.out.println("쿼리시도 : tipBoardsIndex = " + tipBoardsIndex);
			if (category.equals("scrap")) {
				sql = "insert into tip_scraps_boards(tip_idx, email) values(?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, tipBoardsIndex);
				pstmt.setString(2, email);
			}
			int result = pstmt.executeUpdate();
			return result;

		} catch (SQLException e) {
			System.out.println("Error : BusinessDao registerMyScraps 에러");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	public int registerMyBoard(String category, int boardIdx) {
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = factory.getConnection();
			System.out.println("쿼리시도 : boardIdx = " + boardIdx);
			if (category.equals("tips")) {
				sql = "insert into tip_my_boards" + "(tip_idx)" + "values (?)";
				System.out.println("tip :: 쿼리 ");

			} else if (category.equals("it") || category.equals("market") || category.equals("media")
					|| category.equals("etc")) {
				sql = "insert into my_idea_boards" + "(business_boards_idx)" + "values (?)";
			} else {
				return 0;
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			int result = pstmt.executeUpdate();
			System.out.println("쿼리성공? : boardIdx = " + boardIdx + "result : " + result);
			return result;

		} catch (SQLException e) {
			System.out.println("Error : BusinessDao registerMyBoard 에러");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}

	
}