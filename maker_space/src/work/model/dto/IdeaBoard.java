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
 * Idea �Խ��� dto
 */
public class IdeaBoard {
	private int businessIdx;
	/**
	 * �������
	 * index: ���̺� index
	 * title: �� ����
	 * content: �� ����
	 * result: ���ȿ��
	 * files: ����
	 * hits: ��ȸ��
	 * email: �̸���
	 * writeDate: �ۼ���
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
	// 0: �Ϲ�
	// 1: ä�� (������)
	// 2: ����(�����)
	// 3. ä�ÿϷ�(�����)
	/**
	 * �⺻ ������
	 */
	public IdeaBoard() {
	}	
	/**
	 * ���
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
	 * �Խ��� ���� �� ������
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
	 * Ŭ���� ���� ��ü ������
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
	 * toString �������̵� Setting 
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
	 * get/set �޼���
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
