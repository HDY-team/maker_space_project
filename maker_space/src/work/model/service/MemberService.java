/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.model.service;

import java.util.ArrayList;
import java.util.Random;

import work.model.dao.MemberDao;
import work.model.dto.Member;

/**
 * ## 기능 (G: 일반회원)
 * 1. 로그인서비스
 * 2. 회원가입서비스
 * 3. 회원탈퇴서비스
 * 4. 회원정보수정서비스
 * 5. 비밀번호찾기서비스
 * 6. 내정보조회서비스
 * 
 * ## 기능 (A: 관리자)
 * 1. 상세회원조회
 * 2. 전체회원조회
 * 3. 회원탈퇴
 */
public class MemberService {

   private static MemberService instance;
   private MemberDao dao;
   private String code ;
   /**
    * 기본생성자
    */
   private MemberService() {
      dao = MemberDao.getInstance();
   }
   /**
    * Singleton 패턴
    * @return
    */
   public static MemberService getInstance() {
      if(instance == null) {
         instance = new MemberService();
      }
      return instance;
   }
   
   
   
   
   public String confirmEmail(String email) {
	   JavaMail mail= new JavaMail(email);
	   String title="이메일 인증코드입니다";
	   
	   int[] numArr = new int[4];
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<4; i++) {
			
			Random num = new Random();
			
			numArr[i] = num.nextInt(9);
			
			sb.append(numArr[i]);
		}
		
	   
	   code=sb.toString();
	   String contents="코드는 " + code +"입니다";
	   mail.sendMail(title, contents, false);
	   
	   return code;
	   
   }
   
   
   /**
    * 로그인 서비스 (관리자: A, 일반회원: G)
    * @param email
    * @param password
    * @return
    */
   public String login(String email, String password) {
      return dao.loginMember(email, password);
   }
   /**
    * 회원가입 서비스
    * @param member
    * @return
    */
   public int join(Member member) {
	  
     
      return dao.insertMember(member);
   }
  
   /**
    * 회원탈퇴 서비스
    * @param email
    * @param password
    * @return
    */
   public int removeMember(String email, String password) {
      return dao.removeMember(email, password);
   }
   /**
    * 회원정보수정서비스 (비밀번호, 휴대폰번호, 소속 변경)
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
    * 비밀번호찾기서비스
    * @param email
    * @param password
    * @return
    */
   public int forgotPassword(String email, String password) {
      return dao.setPassword(email, password);
   }
   /**
    * 내정보조회서비스
    * @param email
    * @return
    */
   public Member getMyInfo(String email) {
      return dao.getMemberOne(email);
   }
   /**
    * 이메일 중복체크 서비스
    * @param email
    * @return
    */
   public int checkOverlap(String email) {
	   if(dao.getMemberOne(email)!= null) {
		   return 0; // 가입된 메일
	   }
	   else {
		   System.out.println(email);
		   return 1; 
	   }
	   
	  
   }
   /**
    * 상세정보조회서비스 (관리자)
    * @param email
    * @return
    */
   public Member getInfoByAdmin(String email) {
      return dao.getMemberOne(email);   
   }
   /**
    * 전체정보조회서비스 (관리자)
    * @return
    */
   public ArrayList<Member> getAllInfoByAdmin() {
      return dao.getMembers();
   }
   /**
    * 회원탈퇴서비스 (관리자)
    * @param email
    * @return
    */
   public int removeMemberByAdmin(String email) {
      return dao.removeMemberByAdmin(email);
   }
   
   
}