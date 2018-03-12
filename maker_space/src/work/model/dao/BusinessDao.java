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
			System.out.println("Error : BusinessDao registerBoard 에러");
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
			System.out.println("Error : BusinessDao registerBoard 에러");
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
			System.out.println("쿼리시도 : boardIdx = "+boardIdx);
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
			System.out.println("쿼리성공? : boardIdx = "+boardIdx+"result : " +result);	
			return result;

		} catch (SQLException e) {
			System.out.println("Error : BusinessDao registerMyBoard 에러");
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
			System.out.println("쿼리시도 : boardIdx = "+boardIdx);
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
			System.out.println("Error : BusinessDao registerMySelect 에러");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
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
			pstmt.executeUpdate();
			return 1;
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
			String sql = "delete business_boards where business_boards_idx=?";
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
	/**
	 * 게시판 삭제
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
			System.out.println("Error: " + e.getMessage() + "// 테이블 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}

	/**
	 * 글 조회 기능 명세서 1) 추가 2) 수정 3) 삭제 4) 조회
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
			System.out.println("Error : 글 이름 검색 오류");
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
			System.out.println("Error : 글 제목 검색 오류");
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
			System.out.println("Error : 글 내용 검색 오류");
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
				System.out.println("쿼리 실행 끝");
			} else if(category.equals("select")) {
				String sql = "select business_boards.* from business_boards inner join select_boards on business_boards.business_boards_idx = select_boards.business_boards_idx";
				pstmt = conn.prepareStatement(sql);
				System.out.println("쿼리 실행 끝");
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

			if(category.equals("myTips")) { // 내가 작성한 꿀팁 모아보기 게시판
				String sql = "select tip_boards.* from tip_boards inner join tip_my_boards on tip_boards.tip_idx = tip_my_boards.tip_idx";
				pstmt = conn.prepareStatement(sql);
				System.out.println("쿼리 실행 끝");
			} else if(category.equals("tips")) { // 꿀팁 전체확인 게시판
				String sql = "select * from tip_boards order by tip_idx desc ";
				pstmt = conn.prepareStatement(sql);
				System.out.println("쿼리 실행 끝");
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
	 * business 테이블
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