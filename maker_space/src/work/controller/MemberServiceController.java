package work.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import work.model.dto.Member;
import work.model.service.MemberService;

/**
 * Servlet implementation class MemberServiceController
 */
public class MemberServiceController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MemberService service;

    public MemberServiceController(){
    	service = MemberService.getInstance();
	}
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("process");
    	String action = request.getParameter("action");	
		System.out.println(action);
		switch(action) {
		case "login":
			login(request, response);
			break;
		case "join" :
			join(request, response);
			break;
    	default :
    		System.out.println("\n## 서비스 준비중입니다.");
    		break;
		}
	}
	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String cofirmPassword = request.getParameter("confirmPassword");
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String company = request.getParameter("company");
		
		if(errorGenerate(request, response, email, "이메일을 입력해주세요.") != true ) {
			return;
		}
		if(errorGenerate(request, response, password, "비밀번호를 입력해주세요.") != true ) {
			return;
		}
		if(errorGenerate(request, response, name, "이름을 입력해주세요.") != true ) {
			return;
		}
		if(errorGenerate(request, response, mobile, "휴대전화번호를 입력해주세요.") != true ) {
			return;
		}
		int result = service.join(new Member(email, password, name, mobile, company, "G", 0));
		System.out.println("쿼리실행결과 :" + result);
		if(result!=0) {
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
	private void login(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	protected boolean errorGenerate(HttpServletRequest request, HttpServletResponse response, String valueName, String message) throws ServletException, IOException {
		if(valueName == null || valueName.trim().length() == 0) {
			System.out.println("errorGenerate function");
			request.setAttribute("message", message);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return false;
		}
		return true;
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