/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import work.model.interfaces.InterfaceBoard;

/**
 * Factory Pattern
 * -- Connection
 * -- close()
 */
public class FactoryDao {
	
	private DataSource ds;
	private static FactoryDao instance = new FactoryDao();
	/**
	 * �⺻������
	 */
	private FactoryDao() {
		try {
			Context context = new InitialContext();
			System.out.println("!!");
			ds = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
			System.out.println("\n### ds: " + ds);
		} catch(NamingException e){
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}	
	}
	
	/**
	 * Singleton ����
	 * @return
	 */
	public static FactoryDao getInstance() {
		return instance;
	}
	/**
	 * Ŀ�ؼǿ���
	 * @return
	 */
	public Connection getConnection() {
		try {
			return ds.getConnection();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * �ڿ�����
	 * @param conn
	 * @param stmt
	 */
	public void close(Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}
	/**
	 * select �ڿ�����
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	public void close(Connection conn, Statement stmt, ResultSet rs) {
       try {
    	   if (rs != null) {
    		   rs.close();
    	   }
       	   if (stmt != null) {
    		   stmt.close();
    	   }
       	   if (conn != null) {
    		   conn.close();
    	   }
      } catch(SQLException e) {
    	   System.out.println("Error : �� ��� �ڿ����� ����");
    	  e.printStackTrace();
       }
	}
}