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

/**
 * @author DONGHYUNLEE
 *
 */
public class TipDao implements InterfaceBoard{

   private FactoryDao factory = FactoryDao.getInstance();
   private static TipDao instance = new TipDao();
   /**
    * �⺻������
    * context ȯ�� ���� ��������.
    */
   private TipDao() {
   }
   /**
    * Singleton ����
    * @return
    */
   public static TipDao getInstance() {
      return instance;
   }
   
   /**
   * �� ��� �޼���.
   * index: ���̺� index(���� �ε���)
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
   @SuppressWarnings("resource")
   public int registerBoard(String category, TipIdeaBoard dto) {
      String title = dto.getTitle();
      String content = dto.getContent();
      String result = dto.getResult();
      String files = dto.getFiles();
      int hits = dto.getHits();
      int scraps = dto.getScraps();
      String email = dto.getEmail();
      String writeDate = dto.getWriteDate();
      String name = dto.getName();
      System.out.println(dto.toString());
      ResultSet rs = null;
      Connection conn = null;
      PreparedStatement pstmt = null;
         try {
            System.out.println("TIPDAO :: �����õ� :");
            conn = factory.getConnection();
            String sql = "insert into tip_boards "
                     + "(title, content, result, files, hits, scraps, email, write_date , name)"
                     + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            if(rs.next()) {
               return rs.getInt("tip_idx");
            }
         }catch (SQLException e){
            System.out.println("Error : TipDao registerTipBoard ����");
            e.printStackTrace();
         }finally {
            factory.close(conn, pstmt, rs);
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
   public int changeBoard(int boardIdx, String title, String content, String result, String files) {
      Connection conn =null;
      PreparedStatement pstmt = null;
      try {
         conn = factory.getConnection();
         String sql = "update tip_boarfactory set title=?, content=?, result=?, files=? where tip_idx =?";
         pstmt = conn.prepareStatement(sql);     
         pstmt.setString(1, title);
         pstmt.setString(2, content);
         pstmt.setString(3, result);
         pstmt.setString(4, files);
         pstmt.setInt(5, boardIdx);
         pstmt.executeUpdate();
         return 1;
      }catch (SQLException e){
         System.out.println("Error : �� ���� ����");
         e.printStackTrace();
      }finally {
         factory.close(conn, pstmt);
      }
      return 0;
   }
   /**
    * �Խ��� ����
    * @param boardIdx
    * @return
    */
   public int removeBoard(int boardIdx) {
      Connection conn= null;
      PreparedStatement pstmt = null;
      try {
         conn = factory.getConnection();
         String sql = "delete tip_boarfactory where tip_idx=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, boardIdx);
         pstmt.executeUpdate();
         return 1;
      } catch(SQLException e){
         System.out.println("Error: " + e.getMessage() + "// ���̺� ���� ����");
         e.printStackTrace();
      } finally {
         factory.close(conn, pstmt);
      }
      return 0;
   }
   @Override
   public int registerBoard(String category, IdeaBoard dto) {
      // TODO Auto-generated method stub
      return 0;

   }
   @Override
   public ArrayList<IdeaBoard> findBoardHashTag(String hashTag) {
      // TODO Auto-generated method stub
      return null;
   }
   @Override

   public ArrayList<IdeaBoard> findBoardTitle(int currentPage, int listSize, String title, String category) {
      // TODO Auto-generated method stub
      return null;
   }
   @Override

   public ArrayList<IdeaBoard> findBoardName(int currentPage, int listSize, String title, String category) {
      // TODO Auto-generated method stub
      return null;
   }
   @Override
   public ArrayList<IdeaBoard> findBoardContent(int currentPage, int listSize, String title, String category) {
      // TODO Auto-generated method stub
      return null;
   }

}