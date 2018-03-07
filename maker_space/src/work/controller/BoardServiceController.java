/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import work.model.dto.IdeaBoard;
import work.model.service.BoardService;
import work.model.service.MemberService;

/**
 * Servlet Controller - BoardServiceController
 */
public class BoardServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService;
	
    /**
     * 기본 생성자
     */
    public BoardServiceController() {
    	boardService = BoardService.getInstance();
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String action = request.getParameter("action");
			switch(action) {
			case "write":
				write(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			case "findName":
				//findName(request, response);
				break;
			case "findTitle":
				//findTitle(request, response);
				break;
			case "findContent":
				//findContent(request, response);
				break;
			case "findHashTag":
				//findHashTag(request, response);
				break;	
			default:
				System.out.println("\n## 서비스 준비중입니다. 제공되지 않는 서비스입니다.");
				break;
		}	
		} catch(NullPointerException e){
			e.printStackTrace();
		} finally {
			
		}
	}
    /**
     * 글쓰기 서블렛
     */
    protected void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## 글쓰기 요청 서비스");
		HttpSession session = request.getSession(false);
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String result = request.getParameter("result");
		String files = request.getParameter("files");
		String[] hashTag = request.getParameterValues("hashTag");
		String email = (String)session.getAttribute("email");
		int primaryKey;
		if(errorGenerate(request, response, title, "제목을 입력해주세요!") != true ) {
			return;
		}
		if(errorGenerate(request, response, content, "비밀번호를 입력해주세요!") != true) {
			return;
		}
		if(errorGenerate(request, response, result, "아이디를 입력해주세요!") != true) {
			return;
		}
		primaryKey = boardService.businessWrite(new IdeaBoard(title, content, result, files, 0, email, null));
		if(primaryKey != 0) {
			System.out.println("글쓰기 성공");
			boardService.businssRegisterHashTag(primaryKey, hashTag);
			//TODO
			//request.getRequestDispatcher("mainService.jsp").forward(request, response);
		} else {
			System.out.println("글쓰기 실패");
			//TODO
			//request.setAttribute("message", "이미 가입된 회원입니다.");
			//request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
    
    /**
     * 글쓰기 서블렛
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## 글삭제 요청 서비스");
		HttpSession session = request.getSession(false);
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String result = request.getParameter("result");
		String files = request.getParameter("files");
		String[] hashTag = request.getParameterValues("hashTag");
		String email = (String)session.getAttribute("email");
		int primaryKey;
		if(errorGenerate(request, response, title, "제목을 입력해주세요!") != true ) {
			return;
		}
		if(errorGenerate(request, response, content, "비밀번호를 입력해주세요!") != true) {
			return;
		}
		if(errorGenerate(request, response, result, "아이디를 입력해주세요!") != true) {
			return;
		}
		primaryKey = boardService.businessWrite(new IdeaBoard(title, content, result, files, 0, email, null));
		if(primaryKey != 0) {
			System.out.println("글쓰기 성공");
			boardService.businssRegisterHashTag(primaryKey, hashTag);
			//TODO
			//request.getRequestDispatcher("mainService.jsp").forward(request, response);
		} else {
			System.out.println("글쓰기 실패");
			//TODO
			//request.setAttribute("message", "이미 가입된 회원입니다.");
			//request.getRequestDispatcher("error.jsp").forward(request, response);
		}
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
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
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
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}

}
