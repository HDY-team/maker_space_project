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
import java.sql.SQLException;import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import work.model.dto.IdeaBoard;
import work.model.interface_package.InterfaceBoard;
import work.model.service.MemberService;

/**
 * Business Idea Board Dao
 * - ��� : �� ���, ����, ����, ��ȸ
 */
public class BusinessDao implements InterfaceBoard{
	
	private DataSource ds;
	private static BusinessDao instance;

	/**
	 * �⺻������
	 */
	public BusinessDao() {
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
	public static BusinessDao getInstance() {
		if(instance == null) {
			instance = new BusinessDao();
		}
		return instance;
	}
	
   /**
	* �� ��� �޼���.
	* index: ���̺� index(����о� �ε���)
	* title: �� ����
	* content: �� ����
	* result: ���ȿ��
	* files: ����
	* hits: ��ȸ��
	* email: �̸���
	* writeDate: �ۼ���
	* @param dto
	* @return
	*/
	public int registerBoard(IdeaBoard dto) {
		// TODO Auto-generated method stub
		int index = dto.getIndex();
		String title = dto.getTitle();
		String content = dto.getContent();
		String result = dto.getResult();
		String files = dto.getFiles();
		String email = dto.getEmail();
		Connection conn =null;
		PreparedStatement pstmt = null;
	      try {
	         conn = ds.getConnection();
	         String sql = "insert into business_boards "
	         		+ "(title, content, result, files, hits, email, write_date)"
	         		+ "values(?, ?, ?, ?, 0, ?, date_format(sysdate(),'%Y.%m.%d'))";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, title);
	         pstmt.setString(2, content);
	         pstmt.setString(3, result);
	         pstmt.setString(4, files);
	         pstmt.setString(5, email);
	         pstmt.executeUpdate();
	         return 1;
	      }catch (SQLException e){
	         System.out.println("Error : �� ��� ����");
	         e.printStackTrace();
	      }finally {
	         try {
	            if(pstmt !=null) {
	               pstmt.close();
	            } if(conn != null) { 
	                  conn.close();
	               }
	            } catch(SQLException e) {
	               System.out.println("Error : �� ��� �ڿ����� ����");
	            }
	
	      }
	      return 0;
	}
   /**
    * �� ���� �޼���
    * ���� �� �� �ִ� �κ�
    * index, title, content, result , files �κи� ������Ʈ �ȴ�.
    * @param dto
    * @return
    */
	public int changeBoard(String boardIdx, String title, String content, String result, String files) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "update business_boards set "
					+ "title=?, content=?, result=?, files=? where business_boards_idx =?";
			pstmt = conn.prepareStatement(sql);     
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, result);
			pstmt.setString(4, files);
			pstmt.setString(5, boardIdx);
			pstmt.executeUpdate();
			return 1;
		}catch (SQLException e){
			System.out.println("Error : �� ���� ����");
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null) {
					pstmt.close();
				} if(conn != null) { 
					conn.close();
				}
			} catch(SQLException e) {
				System.out.println("Error : �� ���� �ڿ����� ����");
			}
		}
		return 0;
	}
	/**
	 * �Խ��� ����
	 * @param boardIdx
	 * @return
	 */
	public int removeBoard(String boardIdx) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "delete business_boards where business_boards_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardIdx);
			pstmt.executeUpdate();
			return 1;
		} catch(SQLException e){
			System.out.println("Error: " + e.getMessage() + "// ���̺� ���� ����");
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
				System.out.println("Error : ���̺� ���� �ڿ����� ����");
				e.printStackTrace();
			}
		}
		return 0;
	}
	/**
	    * �� ��ȸ
	    * ��� ����
	    * 1) �߰�
	    * 2) ����
	    * 3) ����
	    * 4) ��ȸ 
	    * @param name, title, content, hashTag
	    * @return
	    */
	@SuppressWarnings("resource")
	public ArrayList<IdeaBoard> findBoardName(String name) {
		ResultSet rs = null;
        Connection conn =null;
        PreparedStatement pstmt = null;
        ArrayList<IdeaBoard> list = new ArrayList<IdeaBoard>();
        
        try {
           conn = ds.getConnection();
           String sql = "select email from members where name=?";
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, name);
           rs = pstmt.executeQuery();
           if(rs.next()) {
        	   String email = rs.getString(name);
        	   sql = "select * from business_boards where email like '%?%'";
               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1, name);
               rs = pstmt.executeQuery();	
               while(rs.next()) {
            	  int index = rs.getInt("business_boards_idx");
                  String title = rs.getString("title");
                  String content = rs.getString("content");
                  String result = rs.getString("result");
                  String files = rs.getString("files");
                  int hits = rs.getInt("hits");
                  String writeDate = rs.getString("write_date");
                  list.add(new IdeaBoard(index, title, content, result, files, hits, email, writeDate));
               }
               return list;
           }
        }catch (SQLException e){
           System.out.println("Error : �� �̸� �˻� ����");
           e.printStackTrace();
        }finally {
           try {
              if(pstmt !=null) {
                 pstmt.close();
              }
                 if(conn != null) {
                    conn.close();
                 }
              } catch(SQLException e) {
                 System.out.println("Error : �� �̸� �˻� �ڿ����� ����");
              }
        }
        return null;
	}

	public ArrayList<IdeaBoard> findBoardTitle(String title) {
		ResultSet rs = null;
        Connection conn =null;
        PreparedStatement pstmt = null;
        ArrayList<IdeaBoard> list = new ArrayList<IdeaBoard>();
        
        try {
           conn = ds.getConnection();
           String sql = "select * from business_boards where title like '%?%'";
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, title);
           rs = pstmt.executeQuery();	
           while(rs.next()) {
        	   int index = rs.getInt("business_boards_idx");
              String content = rs.getString("content");
              String result = rs.getString("result");
              String files = rs.getString("files");
              int hits = rs.getInt("hits");
              String email = rs.getString("email");
              String writeDate = rs.getString("write_date");
              list.add(new IdeaBoard(index, title, content, result, files, hits, email, writeDate));
           }
           return list;
        }catch (SQLException e){
           System.out.println("Error : �� ���� �˻� ����");
           e.printStackTrace();
        }finally {
           try {
              if(pstmt !=null) {
                 pstmt.close();
              }
                 if(conn != null) {
                    conn.close();
                 }
              } catch(SQLException e) {
                 System.out.println("Error : �� ���� �˻� �ڿ����� ����");
              }
        }
        return null;
	}
	public ArrayList<IdeaBoard> findBoardContent(String content) {
		ResultSet rs = null;
        Connection conn =null;
        PreparedStatement pstmt = null;
        ArrayList<IdeaBoard> list = new ArrayList<IdeaBoard>();
        
        try {
           conn = ds.getConnection();
           String sql = "select * from business_boards where content like '%?%'";
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, content);
           rs = pstmt.executeQuery();	
           while(rs.next()) {
        	   int index = rs.getInt("business_boards_idx");
              String title = rs.getString("title");
              String result = rs.getString("result");
              String files = rs.getString("files");
              int hits = rs.getInt("hits");
              String email = rs.getString("email");
              String writeDate = rs.getString("write_date");
              list.add(new IdeaBoard(index, title, content, result, files, hits, email, writeDate));
           }
           return list;
        }catch (SQLException e){
           System.out.println("Error : �� ���� �˻� ����");
           e.printStackTrace();
        }finally {
           try {
              if(pstmt !=null) {
                 pstmt.close();
              }
                 if(conn != null) {
                    conn.close();
                 }
              } catch(SQLException e) {
                 System.out.println("Error : �� ���� �˻� �ڿ����� ����");
              }
        }
        return null;
	}
	public ArrayList<IdeaBoard> findBoardHashTag(String hashTag) {
		ResultSet rs = null;
        Connection conn =null;
        PreparedStatement pstmt = null;
        ArrayList<IdeaBoard> list = new ArrayList<IdeaBoard>();
        ArrayList<Integer> indexBoards = new ArrayList<Integer>();
        Integer integer;
        String sql;
        try {
           conn = ds.getConnection();
           for(int i = 1 ; i <= 5 ; i ++) {
        	   sql = "select business_boards_idx from hashtags where hash_tag'" + i + "'=? like '%?%'";
               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1, hashTag);
               rs = pstmt.executeQuery();	
               while(rs.next()) {
            	   integer = new Integer(rs.getInt("business_boards_idx"));
            	   indexBoards.add(integer);
               }
           }
           for(int i = 0 ; i < indexBoards.size(); i++) {
        	   sql = "select * from business_boards where business_boards_idx =?";
               pstmt = conn.prepareStatement(sql);
               pstmt.setInt(1, indexBoards.get(i).intValue());
               rs = pstmt.executeQuery();	
               while(rs.next()) {
                  int index = rs.getInt("business_boards_idx");
                  String title = rs.getString("title");
                  String content = rs.getString("content");
                  String result = rs.getString("result");
                  String files = rs.getString("files");
                  int hits = rs.getInt("hits");
                  String email = rs.getString("email");
                  String writeDate = rs.getString("write_date");
                  list.add(new IdeaBoard(index, title, content, result, files, hits, email, writeDate));
               }
           }
           if(list.size() != 0) {
        	   return list;
           }
        }catch (SQLException e){
           System.out.println("Error : �� �ؽ��±�  �̸� �˻� ����");
           e.printStackTrace();
        }finally {
           try {
              if(pstmt !=null) {
                 pstmt.close();
              }
                 if(conn != null) {
                    conn.close();
                 }
              } catch(SQLException e) {
                 System.out.println("Error : �� �ؽ��±� �˻� �ڿ����� ����");
              }
        }
        return null;
	}
}