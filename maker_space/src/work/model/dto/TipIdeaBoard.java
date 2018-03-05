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
	public TipIdeaBoard(int index, String title, String content, String result, String files, int hits, String email,
			String writeDate) {
		super(index, title, content, result, files, hits, email, writeDate);
		// TODO Auto-generated constructor stub
	}
	public TipIdeaBoard(int index, String title, String content, String result, String files, int hits, String email,
			String writeDate, int scraps) {
		super(index, title, content, result, files, hits, email, writeDate);
		this.scraps = scraps;
		// TODO Auto-generated constructor stub
	}
	/**
	 * get, set 메서드
	 */
	public int getScraps() {
		return scraps;
	}
	public void setScraps(int scraps) {
		this.scraps = scraps;
	}
	/**
	 * toString 재정의 오버라이딩
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipIdeaBoard [scraps=");
		builder.append(scraps);
		builder.append(", getIndex()=");
		builder.append(getIndex());
		builder.append(", getTitle()=");
		builder.append(getTitle());
		builder.append(", getContent()=");
		builder.append(getContent());
		builder.append(", getResult()=");
		builder.append(getResult());
		builder.append(", getFiles()=");
		builder.append(getFiles());
		builder.append(", getHits()=");
		builder.append(getHits());
		builder.append(", getEmail()=");
		builder.append(getEmail());
		builder.append(", getWriteDate()=");
		builder.append(getWriteDate());
		builder.append("]");
		return builder.toString();
	}
}
