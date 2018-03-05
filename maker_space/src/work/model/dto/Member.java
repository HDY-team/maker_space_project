/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.model.dto;

import java.sql.Date;

import work.util.MyUtility;

/**
 * ## ȸ�� dto Ŭ����
 */
public class Member {
	/**
	 * �������
	 * email: ����� identification
	 * password: ����� password
	 * name: ����� �̸�
	 * mobile: ����� �޴�����ȣ
	 * company: ����� �Ҽ�
	 */
	private String email;
	private String password;
	private String name;
	private String mobile;
	private String company;
	private String grade;
	private int point;
	/**
	 * �⺻ ������
	 */
	public Member() {
	}
	/**
	 * ȸ�� ���� ������
	 * @param email 
	 * @param password
	 * @param name
	 * @param mobile
	 * @param company
	 */
	public Member(String email, String password, String name, String mobile, String company) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.mobile = mobile;
		this.company = company;
	}
	/**
	 * ������ ������
	 * @param email
	 * @param password
	 * @param name
	 * @param mobile
	 * @param company
	 * @param point
	 */
	public Member(String email, String password, String name, String mobile, String company, String grade) {
		this(email, password, name, mobile, company);
		this.grade = grade;
	}
	/**
	 * ȸ�� ���� ��ü ������
	 * @param email
	 * @param password
	 * @param name
	 * @param mobile
	 * @param company
	 * @param point
	 */
	public Member(String email, String password, String name, String mobile, String company, String grade, int point) {
		this(email, password, name, mobile, company);
		this.grade = grade;
		this.point = point;
	}
	/**
	 * toString �������̵� Setting 
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(email);
		builder.append(", ");
		builder.append(MyUtility.convertSecureString(password));
		builder.append(", ");
		builder.append(name);
		builder.append(", ");
		builder.append(mobile);
		builder.append(", ");
		builder.append(company);
		builder.append(", ");
		builder.append(grade);
		builder.append(", ");
		builder.append(point);
		return builder.toString();
	}
	/**
	 * get/set �޼���
	 */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
}
