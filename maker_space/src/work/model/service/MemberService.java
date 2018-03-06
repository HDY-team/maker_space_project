/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.model.service;

import java.util.ArrayList;

import work.model.dao.MemberDao;
import work.model.dto.Member;

/**
 * ## ��� (G: �Ϲ�ȸ��)
 * 1. �α��μ���
 * 2. ȸ�����Լ���
 * 3. ȸ��Ż�𼭺�
 * 4. ȸ��������������
 * 5. ��й�ȣã�⼭��
 * 6. ��������ȸ����
 * 
 * ## ��� (A: ������)
 * 1. ��ȸ����ȸ
 * 2. ��üȸ����ȸ
 * 3. ȸ��Ż��
 */
public class MemberService {

	private static MemberService instance;
	private MemberDao dao;
	/**
	 * �⺻������
	 */
	private MemberService() {
		dao = MemberDao.getInstance();
	}
	/**
	 * Singleton ����
	 * @return
	 */
	public static MemberService getInstance() {
		if(instance == null) {
			instance = new MemberService();
		}
		return instance;
	}
	/**
	 * �α��� ���� (������: A, �Ϲ�ȸ��: G)
	 * @param email
	 * @param password
	 * @return
	 */
	public String login(String email, String password) {
		return dao.loginMember(email, password);
	}
	/**
	 * ȸ������ ����
	 * @param member
	 * @return
	 */
	public int join(Member member) {
		if(dao.getMemberOne(member.getEmail()) != null) {
			return 0;
		}
		System.out.println(member.getEmail()); // log
		return dao.insertMember(member);
	}
	/**
	 * ȸ��Ż�� ����
	 * @param email
	 * @param password
	 * @return
	 */
	public int removeMember(String email, String password) {
		return dao.removeMember(email, password);
	}
	/**
	 * ȸ�������������� (��й�ȣ, �޴�����ȣ, �Ҽ� ����)
	 * @param email
	 * @param password
	 * @param mobile
	 * @param company
	 * @return
	 */
	public int updateMember(String email, String password, String mobile, String company) {
		return dao.changeMemberInfo(email, password, mobile, company);
	}
	/**
	 * ��й�ȣã�⼭��
	 * @param email
	 * @param password
	 * @return
	 */
	public int forgotPassword(String email, String password) {
		return dao.setPassword(email, password);
	}
	/**
	 * ��������ȸ����
	 * @param email
	 * @return
	 */
	public Member getMyInfo(String email) {
		return dao.getMemberOne(email);
	}
	/**
	 * ��������ȸ���� (������)
	 * @param email
	 * @return
	 */
	public Member getInfoByAdmin(String email) {
		return dao.getMemberOne(email);	
	}
	/**
	 * ��ü������ȸ���� (������)
	 * @return
	 */
	public ArrayList<Member> getAllInfoByAdmin() {
		return dao.getMembers();
	}
	/**
	 * ȸ��Ż�𼭺� (������)
	 * @param email
	 * @return
	 */
	public int removeMemberByAdmin(String email) {
		return dao.removeMemberByAdmin(email);
	}

	
}
