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
    		System.out.println("\n## ���� �غ����Դϴ�.");
    		break;
		}
	}
    /**
     * ��� ��ȣ ���� ����
     * ������ ���̽��� �����ϴ� �̸����� ��쿡 ���� �ڵ带 �����Ѵ�.
     * @param request
     * @param response
     * @throws IOException
     */
    private void createNewPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String email=request.getParameter("email");
    	int result = memberService.checkOverlap(email); // ������ db�� ���� �� 0 ����
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
     * ���� �� ���Ǵ� �̸��� ���� �޼���
     * ������ ���̽��� �������� �ʴ� ȸ���� ��� ���� �ڵ带 �����Ѵ�.
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
	 * �� ���� ��ȸ �޼���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("\n## �� ���� ��ȸ ��û");
		
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
     * ��й�ȣ ���� �޼���
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
    		System.out.println("�����ڵ� ����");
			String message = "���� �ڵ带 Ȯ�����ּ���.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("forgot_password.jsp").forward(request, response);
			return ;
		}
    	System.out.println("���� �ڵ�"+originCode +" :: �Է� �ڵ� :"+comparedCode);
    	if(password.equals(confirmedPassword))
    	{
    		int result = memberService.forgotPassword(email, password);
        	
        	if(result != 0) {
    			System.out.println("��й�ȣ ���� ����");
    			String message = "���� �Ǿ����ϴ�";
    			request.setAttribute("message", message);
    			request.getRequestDispatcher("index.jsp").forward(request, response);
    		} else {
    			
    			request.setAttribute("message", "���濡 �����߽��ϴ�.");
    			request.getRequestDispatcher("error.jsp").forward(request, response);
    		}	

    	}else {
    		request.setAttribute("message", "���濡 �����߽��ϴ�.");
    		request.getRequestDispatcher("forgot_password.jsp").forward(request, response);
    	}
    }
	/**
     * ȸ�� ���� ���� �޼���
     * �̸���, �н�����, ��ȭ��ȣ, �̸��� ���� ���� ---> �̸��ϵ� ���������ϰ��Ұ��ΰ�?
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
    			
    			System.out.println("���� ����");
    			String message = "���� �Ǿ����ϴ�";
    			session.setAttribute("name", name);
    			session.setAttribute("email", email);
    			session.setAttribute("password", newPassword);
    			session.setAttribute("mobile", mobile);
    			session.setAttribute("company", company);
    			
    			request.setAttribute("message", message);
    			request.getRequestDispatcher("mainService.jsp").forward(request, response);
    		} else {
    			
    			request.setAttribute("message", "������ �����߽��ϴ�.");
    			request.getRequestDispatcher("error.jsp").forward(request, response);
    		}	


    	}else{
    		request.setAttribute("message", "������ �����߽��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
    		
    	}
    	   	
    }
    /**
     * ȸ�� Ż�� ��û �޼���
     * ȸ���� ���� Ż�� ���� �� ���Ǵ� �޼���
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	private void removeMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("\n## ȸ�� Ż�� ��û");
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	
    	if(email == null || password== null) {
    		request.getRequestDispatcher("deleteMyAccount.jsp").forward(request, response);;
    		return ;
    	}
    	
    	int result = memberService.removeMember(email, password);
    	
    	
    	if(result != 0) {
			//Ż�� ����
			System.out.println("Ż�𼺰�");
			String message = "Ż�� �Ǿ����ϴ�";
			//ȸ������ ���� ������ �̵�
			request.setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			
			request.setAttribute("message", "Ż�� �����߽��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}	
    	
    	
	}
	
	/**
     * �α��� ��û�޼���
     * ������ �̿��� �α���.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("\n## �α��� ��û");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(email==null || email.trim().length()==0)
		{
			String message = "�̸����� �Է����ּ���.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		if(password==null || password.trim().length()==0)
		{
			String message = "��й�ȣ�� �Է����ּ���.";
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
			
			System.out.println("�α��� ����");
		} else {
			request.setAttribute("message", "�α��ο� �����߽��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}
    /**
     * �α׾ƿ� ��û �޼���
     * �α��ζ� ������� ���ǰ� �Է��� �̸����� �̿��ؼ� ������ ���̰� ���ǿ� ����� �̸����� �����.
     * @param request
     * @param response
     * @throws IOException
     */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("\n## �α׾ƿ� ��û");
		
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
	 * ȸ������ ��û�޼���
	 * email, password,name mobile,company �� �Է� �޾� ȸ�� ��ü ���� ��� ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## ȸ������ ��û");
		
		String originCode =code  ;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String comparedCode = request.getParameter("code");
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String company = request.getParameter("company");
		
		if(! password.equals(confirmPassword) ) {
			String message = "��й�ȣ�� Ȯ���� �ּ���.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		if(!originCode.equals(comparedCode) || comparedCode == null) {
			String message = "���� �ڵ带 Ȯ�����ּ���.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		if(email==null || email.trim().length()==0)
		{
			String message = "�̸����� �Է����ּ���.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		if(password==null || password.trim().length()==0)
		{
			String message = "��й�ȣ�� �Է����ּ���.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		if(!(password.trim().length() >= 6 && password.trim().length() <=30)) {
			String message = "��й�ȣ ������ �ؼ����ּ���.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		if(name==null || name.trim().length()==0) {
			String message = "�̸��� �Է����ּ���.";
			request.setAttribute("message", message);
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		if(mobile==null || mobile.trim().length()==0)
		{
			String message = "�ڵ��� ��ȣ�� �Է����ּ���.";
			request.setAttribute("message", message);			
			RequestDispatcher nextView = request.getRequestDispatcher("error.jsp");
			nextView.forward(request, response);
			return ;
		}
		
		
		int result =memberService.join(new Member(email, password, name, mobile, company));
		System.out.println("ȸ�����Ե���� :: "+result+" 0 �� �ƴϸ� ����");
		
			if(result !=0) {
				request.setAttribute("email", email);
				request.setAttribute("password", password);
				request.setAttribute("name", name);
				request.setAttribute("mobile", mobile);
				request.setAttribute("company", company);		
				request.getRequestDispatcher("index.jsp").forward(request, response);;
			} else {
				request.setAttribute("message", "ȸ�����Կ� �����߽��ϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		
	}
	/**
     * ������ ���� �޼��� - ȸ�� Ż�� ��û�޼��� 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void removeMemberByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("\n## ������ - ȸ�� Ż�� ��û");
		
		String email = request.getParameter("email");
		int result = memberService.removeMemberByAdmin(email);
		
		if(result != 0) {
			//Ż�� ����
			System.out.println("Ż�𼺰�");
			String message = "Ż�� �Ǿ����ϴ�";
			//ȸ������ ���� ������ �̵�
			request.setAttribute("message", message);
			request.getRequestDispatcher("managerPage.jsp").forward(request, response);
		} else {
			
			request.setAttribute("message", "Ż�� �����߽��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}	
	}
    /**
     * ������ ���� �޼��� - ��ü ȸ�� ��ȸ
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    private void getAllInfoByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("\n## ������ :: ��ü ȸ�� ��ȸ ��û");
		
		ArrayList<Member> members = new ArrayList<>();
		members = memberService.getAllInfoByAdmin();
		request.setAttribute("members", members);
		
		request.getRequestDispatcher("manageMemberAccount.jsp").forward(request, response);
		// ��ü ���� ��ȸ �������� ����
	}
    /**
     * ������ ���� �޼��� - �Ѹ��� ȸ�� ��ȸ
     * 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
	private void getInfoByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## ȸ�� ���� ��ȸ ��û");
		
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