package work.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import work.model.dto.Member;
import work.model.service.MemberService;

/**
 * Servlet implementation class MembermemberServiceController
 */
public class MemberServiceController extends HttpServlet {

	String code;
    private MemberService memberService;

    public MemberServiceController(){
    	memberService = MemberService.getInstance();
	}
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String action = request.getParameter("action");	
		System.out.println(action);
		
		switch(action) {
		case "confirmEmail":
			confirmEmail(request,response);
			break;
		case "login":
			login(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "join" :
			join(request, response);
			break;
		case "removeMember" :
			removeMember(request, response);
			break;
		case "updateMember" :
			updateMember(request, response);
			break;
		case "forgotPassword" :
			forgotPassword(request, response);
			break;
		case "createNewPassword" :
			createNewPassword(request, response);
			break;
		case "getMyInfo" :
			getMyInfo(request, response);
			break;
		case "removeMemberByAdmin" :
			removeMemberByAdmin(request, response);
			break;
		case "getInfoByAdmin" :
			getInfoByAdmin(request, response);
			break;
		case "getAllInfoByAdmin" :
			getAllInfoByAdmin(request, response);
			break;
    	default :
    		System.out.println("\n## 서비스 준비중입니다.");
    		break;
		}
	}
    /**
     * 비밀 번호 변경 서비스
     * 데이터 베이스에 존재하는 이메일일 경우에 인증 코드를 제공한다.
     * @param request
     * @param response
     * @throws IOException
     */
    private void createNewPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String email=request.getParameter("email");
    	int result = memberService.checkOverlap(email); // 메일이 db에 있을 떄 0 리턴
    	PrintWriter out=response.getWriter();
    	if(result==0) {
    		code=memberService.confirmEmail(email);
    		out.print(code);
    		out.close();
    		
    	}else {
    		out.println("Create your new Account");
    		out.close();
    	}
	}
    /**
     * 가입 시 사용되는 이메일 인증 메서드
     * 데이터 베이스에 존재하지 않는 회원인 경우 인증 코드를 제공한다.
     * @param request
     * @param response
     * @throws IOException
     */
	private void confirmEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String email=request.getParameter("email");
    	int result = memberService.checkOverlap(email);
    	PrintWriter out=response.getWriter();
    	if(result!=0) {
    		code=memberService.confirmEmail(email);
    		out.print(code);
    		out.close();
    		
    	}else {
    		out.println("Duplicated Email");
    		out.close();
    	}
    	
    	
    }
	/**
	 * 내 정보 조회 메서드
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("\n## 내 정보 조회 요청");
		
		HttpSession session = request.getSession(false); 
		
		Member member = new Member();
		member = memberService.getMyInfo((String)session.getAttribute("email"));
		//System.out.println(member.getMemberId());
		
		request.setAttribute("email", member.getEmail());
		request.setAttribute("name", member.getName());
		request.setAttribute("password", member.getPassword());
		request.setAttribute("mobile", member.getMobile());
		request.setAttribute("company", member.getCompany());
		request.setAttribute("grade", member.getGrade());
		request.setAttribute("point", member.getPoint());
		
		request.getRequestDispatcher("myAccount.jsp").forward(request, response); 

	}
	/**
     * 비밀번호 변경 메서드
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void forgotPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String originCode =code;
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	String comparedCode = request.getParameter("code");
    	String confirmedPassword = request.getParameter("confirmedPassword");
    	System.out.println();
    	if(!originCode.equals(comparedCode) || comparedCode == null) {
    		System.out.println("인증코드 오류");
			String message = "인증 코드를 확인해주세요.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("forgot_password.jsp").forward(request, response);
			return ;
		}
    	System.out.println("생성 코드"+originCode +" :: 입력 코드 :"+comparedCode);
    	if(password.equals(confirmedPassword))
    	{
    		int result = memberService.forgotPassword(email, password);
        	
        	if(result != 0) {
    			System.out.println("비밀번호 변경 성공");
    			String message = "변경 되었습니다";
    			request.setAttribute("message", message);
    			request.getRequestDispatcher("index.jsp").forward(request, response);
    		} else {
    			
    			request.setAttribute("message", "변경에 실패했습니다.");
    			request.getRequestDispatcher("error.jsp").forward(request, response);
    		}	

    	}else {
    		request.setAttribute("message", "변경에 실패했습니다.");
    		request.getRequestDispatcher("forgot_password.jsp").forward(request, response);
    	}
    }
	/**
     * 회원 정보 수정 메서드
     * 이메일, 패스워드, 전화번호, 이메일 수정 가능 ---> 이메일도 수정가능하게할것인가?
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void updateMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);
    	
    	String password = session.getAttribute("password").toString();
    	String name = session.getAttribute("name").toString();
		String email = session.getAttribute("email").toString();
    	String originalPassword = request.getParameter("originalPassword");
    	String newPassword = request.getParameter("newPassword");
    	String mobile = request.getParameter("mobile");
    	String company = request.getParameter("company");
    	
    	if(newPassword == null || originalPassword== null || mobile == null) {
    		request.getRequestDispatcher("editMyAccount.jsp").forward(request, response);;
    		return;
    	}
    	
    	System.out.println(password);
    	if(password.equals(originalPassword)) {
    		int result = memberService.updateMember(email, newPassword, mobile, company);
        	
        	if(result != 0) {
    			
    			System.out.println("수정 성공");
    			String message = "수정 되었습니다";
    			session.setAttribute("name", name);
    			session.setAttribute("email", email);
    			session.setAttribute("password", newPassword);
    			session.setAttribute("mobile", mobile);
    			session.setAttribute("company", company);
    			
    			request.setAttribute("message", message);
    			request.getRequestDispatcher("mainService.jsp").forward(request, response);
    		} else {
    			
    			request.setAttribute("message", "수정에 실패했습니다.");
    			request.getRequestDispatcher("error.jsp").forward(request, response);
    		}	


    	}else{
    		request.setAttribute("message", "수정에 실패했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
    		
    	}
    	   	
    }
    /**
     * 회원 탈퇴 요청 메서드
     * 회원이 직접 탈퇴를 원할 떄 사용되는 메서드
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	private void removeMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("\n## 회원 탈퇴 요청");
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	
    	if(email == null || password== null) {
    		request.getRequestDispatcher("deleteMyAccount.jsp").forward(request, response);;
    		return ;
    	}
    	
    	int result = memberService.removeMember(email, password);
    	
    	
    	if(result != 0) {
			//탈퇴 성공
			System.out.println("탈퇴성공");
			String message = "탈퇴 되었습니다";
			//회원전용 서비스 페이지 이동
			request.setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			
			request.setAttribute("message", "탈퇴에 실패했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}	
    	
    	
	}
	
	/**
     * 로그인 요청메서드
     * 세션을 이용해 로그인.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("\n## 로그인 요청");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(email==null || email.trim().length()==0)
		{
			String message = "이메일을 입력해주세요.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		if(password==null || password.trim().length()==0)
		{
			String message = "비밀번호를 입력해주세요.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		
		String grade = memberService.login(email, password);
		if(grade !=null) {
			
			HttpSession session = request.getSession(true);
			session.setAttribute("email", email);
			session.setAttribute("grade", grade);
			
			Member member = memberService.getMyInfo(email);

			if(member != null) {
				session.setAttribute("name", member.getName());
				session.setAttribute("company", member.getCompany());
				session.setAttribute("password", member.getPassword());
				session.setAttribute("mobile", member.getMobile());
			}
			
			request.getRequestDispatcher("mainService.jsp").forward(request, response);
			
			System.out.println("로그인 성공");
		} else {
			request.setAttribute("message", "로그인에 실패했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}
    /**
     * 로그아웃 요청 메서드
     * 로그인때 만들어진 세션과 입력한 이메일을 이용해서 세션을 죽이고 세션에 저장된 이메일을 지운다.
     * @param request
     * @param response
     * @throws IOException
     */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("\n## 로그아웃 요청");
		
		HttpSession session = request.getSession(false);
		
		if(session != null ) {
			if(session.getAttribute("grade")!=null) {
				session.removeAttribute("grade");	
			}
			
			session.invalidate();
		}
		response.sendRedirect("index.jsp");
		
	}
		
	/**
	 * 회원가입 요청메서드
	 * email, password,name mobile,company 를 입력 받아 회원 객체 생성 디비에 저장
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## 회원가입 요청");
		
		String originCode =code  ;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String comparedCode = request.getParameter("code");
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String company = request.getParameter("company");
		
		if(! password.equals(confirmPassword) ) {
			String message = "비밀번호를 확인해 주세요.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		if(!originCode.equals(comparedCode) || comparedCode == null) {
			String message = "인증 코드를 확인해주세요.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		if(email==null || email.trim().length()==0)
		{
			String message = "이메일을 입력해주세요.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		if(password==null || password.trim().length()==0)
		{
			String message = "비밀번호를 입력해주세요.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		if(!(password.trim().length() >= 6 && password.trim().length() <=30)) {
			String message = "비밀번호 규정을 준수해주세요.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		if(name==null || name.trim().length()==0) {
			String message = "이름을 입력해주세요.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		if(mobile==null || mobile.trim().length()==0)
		{
			String message = "핸드폰 번호를 입력해주세요.";
			request.setAttribute("message", message);			
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		
		
		int result =memberService.join(new Member(email, password, name, mobile, company));
		System.out.println("회원가입디버깅 :: "+result+" 0 이 아니면 성공");
		
			if(result !=0) {
				request.setAttribute("email", email);
				request.setAttribute("password", password);
				request.setAttribute("name", name);
				request.setAttribute("mobile", mobile);
				request.setAttribute("company", company);		
				request.getRequestDispatcher("index.jsp").forward(request, response);;
			} else {
				request.setAttribute("message", "회원가입에 실패했습니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		
	}
	/**
     * 관리자 전용 메서드 - 회원 탈퇴 요청메서드 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void removeMemberByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("\n## 관리자 - 회원 탈퇴 요청");
		
		String email = request.getParameter("email");
		int result = memberService.removeMemberByAdmin(email);
		
		if(result != 0) {
			//탈퇴 성공
			System.out.println("탈퇴성공");
			String message = "탈퇴 되었습니다";
			//회원전용 서비스 페이지 이동
			request.setAttribute("message", message);
			request.getRequestDispatcher("managerPage.jsp").forward(request, response);
		} else {
			
			request.setAttribute("message", "탈퇴에 실패했습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}	
	}
    /**
     * 관리자 전용 메서드 - 전체 회원 조회
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    private void getAllInfoByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("\n## 관리자 :: 전체 회원 조회 요청");
		
		ArrayList<Member> members = new ArrayList<>();
		members = memberService.getAllInfoByAdmin();
		request.setAttribute("members", members);
		
		request.getRequestDispatcher("manageMemberAccount.jsp").forward(request, response);
		// 전체 정보 조회 페이지로 전송
	}
    /**
     * 관리자 전용 메서드 - 한명의 회원 조회
     * 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
	private void getInfoByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## 회원 정보 조회 요청");
		
		String email = request.getParameter("email");
		
		Member dto = memberService.getMyInfo(email);
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("memberAccount.jsp").forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		process(request, response);
	}	
}