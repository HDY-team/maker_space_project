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
public class TipIdeaBoard {
	private int tipIdx;
	/**
	 * 멤버변수 index: 테이블 index title: 글 제목 content: 글 내용 result: 기대효과 files: 파일 hits:
	 * 조회수 email: 이메일 writeDate: 작성일
	 */
	private String title;
	private String content;
	private String result;
	private String files;
	private int hits;
	private String email;
	private String writeDate;
	private String name;
	private int scraps;

	/**
	 * 기본생성자
	 */
	public TipIdeaBoard() {
	}
	/**
	 * 게시판 생성 생성자
	 */
	public TipIdeaBoard(String title, String content, String result, String files, int hits, String email,
			String writeDate, String name, int scraps) {
		this.title = title;
		this.content = content;
		this.result = result;
		this.files = files;
		this.hits = hits;
		this.email = email;
		this.writeDate = writeDate;
		this.name = name;
		this.scraps = scraps;
	}
	/**
	 * 전체 생성자
	 * 
	 * @param index
	 * @param title
	 * @param content
	 * @param result
	 * @param files
	 * @param hits
	 * @param email
	 * @param writeDates
	 */
	public TipIdeaBoard(int tipIdx, String title, String content, String result, String files, int hits, String email,
			String writeDate, String name, int scraps) {
		this(title, content, result, files, hits, email, writeDate, name, scraps);
		this.tipIdx = tipIdx;
	}
	public int getTipIdx() {
		return tipIdx;
	}
	public void setTipIdx(int tipIdx) {
		this.tipIdx = tipIdx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScraps() {
		return scraps;
	}
	public void setScraps(int scraps) {
		this.scraps = scraps;
	}
	@Override
	public String toString() {
		return "TipIdeaBoard [tipIdx=" + tipIdx + ", title=" + title + ", content=" + content + ", result=" + result
				+ ", files=" + files + ", hits=" + hits + ", email=" + email + ", writeDate=" + writeDate + ", name="
				+ name + ", scraps=" + scraps + "]";
	}

	

}
