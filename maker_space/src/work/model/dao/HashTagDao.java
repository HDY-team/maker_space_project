/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.model.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author DONGHYUNLEE
 *
 */
public class HashTagDao {

	private DataSource ds;
	private static HashTagDao instance;

	/**
	 * 기본생성자
	 */
	public HashTagDao() {
		try {
			Context context = new InitialContext();
	         ds = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
		} catch(NamingException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	/**
	 * Singleton 패턴
	 * @return
	 */
	public static HashTagDao getInstance() {
		if(instance == null) {
			instance = new HashTagDao();
		}
		return instance;
	}

}
