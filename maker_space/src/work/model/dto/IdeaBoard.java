/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.model.dto;

import work.util.MyUtility;

/**
 * Idea 게시판 dto
 */
public class IdeaBoard {
	private int businessIdx;
	/**
	 * 멤버변수
	 * index: 테이블 index
	 * title: 글 제목
	 * content: 글 내용
	 * result: 기대효과
	 * files: 파일
	 * hits: 조회수
	 * email: 이메일
	 * writeDate: 작성일
	 */
	private int index;
	private String title;
	private String content;
	private String result;
	private String files;
	private int hits;
	private String email;
	private String writeDate;
	private String name;
	private int process;
	// 0: 일반
	// 1: 채택 (보여줌)
	// 2: 승인(사라짐)
	// 3. 채택완료(사라짐)
	/**
	 * 기본 생성자
	 */
	public IdeaBoard() {
	}	
	/**
	 * 상속
	 * @param businessIdx
	 * @param index
	 * @param title
	 * @param content
	 * @param result
	 * @param files
	 * @param hits
	 * @param email
	 * @param writeDate
	 */
	public IdeaBoard(String title, String content, String result, String files, int hits, String email, String writeDate, String name, int process) {
		this.title = title;
		this.content = content;
		this.result = result;
		this.files = files;
		this.hits = hits;
		this.email = email;
		this.writeDate = writeDate;
		this.name = name;
		this.process = process;
	}
	/**
	 * 게시판 생성 시 생성자
	 * @param title
	 * @param content
	 * @param result
	 * @param files
	 * @param hits
	 * @param email
	 * @param writeDate
	 */
	public IdeaBoard(int businessIdx, String title, String content, String result, String files, int hits, String email, String writeDate, String name, int process) {
		this(title, content, result, files, hits, email, writeDate, name, process);
		this.businessIdx = businessIdx;
	}

	/**
	 * 클래스 변수 전체 생성자
	 * @param index
	 * @param title
	 * @param content
	 * @param result
	 * @param files
	 * @param hits
	 * @param email
	 * @param write_date
	 */
	public IdeaBoard(int businessIdx, int index, String title, String content, String result, String files, int hits, String email, String writeDate, String name, int process) {
		this(businessIdx, title, content, result, files, hits, email, writeDate, name, process);
		this.index = index;
	}
	
	/**
	 * toString 오버라이드 Setting 
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("businessIdx:");
		builder.append(businessIdx);
		builder.append(", ");
		builder.append(" index:");
		builder.append(index);
		builder.append(", ");
		builder.append(" title:");
		builder.append(title);
		builder.append(", ");
		builder.append(" content:");
		builder.append(content);
		builder.append(", ");
		builder.append(" result:");
		builder.append(result);
		builder.append(", ");
		builder.append(" files:");
		builder.append(files);
		builder.append(", ");
		builder.append(" hits:");
		builder.append(hits);
		builder.append(", ");
		builder.append(" email:");
		builder.append(email);
		builder.append(", ");
		builder.append(" writeDate:");
		builder.append(writeDate);
		builder.append(" name:");
		builder.append(name);
		builder.append(" process:");
		builder.append(process);
		return builder.toString();
	}
	/**
	 * get/set 메서드
	 */
	
	public int getBusinessIdx() {
		return businessIdx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBusinessIdx(int businessIdx) {
		this.businessIdx = businessIdx;
	}
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process = process;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
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
	

}
