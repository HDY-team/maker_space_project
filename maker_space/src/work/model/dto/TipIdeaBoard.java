/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.model.dto;
/**
 * 꿀팁 게시판 자식클래스 
 */
public class TipIdeaBoard extends IdeaBoard {
	private int scraps;
	/**
	 * 기본생성자
	 */
	public TipIdeaBoard() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param index
	 * @param title 
	 * @param content
	 * @param result
	 * @param files
	 * @param hits
	 * @param email
	 * @param writeDates
	 */
	public TipIdeaBoard(String index, String title, String content, String result, String files, int hits, String email,
			String writeDate) {
		super(index, title, content, result, files, hits, email, writeDate);
		// TODO Auto-generated constructor stub
	}
	public TipIdeaBoard(String index, String title, String content, String result, String files, int hits, String email,
			String writeDate, int scraps) {
		super(index, title, content, result, files, hits, email, writeDate);
		this.scraps = scraps;
		// TODO Auto-generated constructor stub
	}
}
