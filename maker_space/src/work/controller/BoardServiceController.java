/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import work.model.dto.IdeaBoard;
import work.model.dto.Member;
import work.model.service.BoardService;
import work.model.service.MemberService;
import work.util.MyUtility;

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
			System.out.println("Controller proccess action: " + action);
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
				findTitle(request, response);
				break;
			case "findContent":
				//findContent(request, response);
				break;
			case "findHashTag":
				//findHashTag(request, response);
				break;
			case "getBoards":
				getBoards(request, response);
				break;
			case "getBoard":
				getBoard(request, response);
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
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String result = request.getParameter("result");
		String files = request.getParameter("files");
		String[] hashTag = request.getParameterValues("hashTag");
		String email = (String)session.getAttribute("email");
		int businessBoardsIdx;
		if(errorGenerate(request, response, title, "제목을 입력해주세요!") != true ) {
			return;
		}
		if(errorGenerate(request, response, content, "비밀번호를 입력해주세요!") != true) {
			return;
		}
		if(errorGenerate(request, response, result, "아이디를 입력해주세요!") != true) {
			return;
		}
		if(category.equals("it")) {
			businessBoardsIdx = boardService.businessWrite("it", new IdeaBoard(title, content, result, files, 0, email, MyUtility.dateGenerator(), (String) session.getAttribute("name")));
			if(businessBoardsIdx != 0 ) {
				System.out.println("BoardService it boards 글쓰기 성공");
				boardService.businssRegisterHashTag(businessBoardsIdx, hashTag);
				getBoards(request, response);
			} else {
				System.out.println("BoardService it boards 글쓰기 실패");
				request.setAttribute("message", "글 쓰기에 오류가 생겼습니다!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
		
	}
    
    /**
     * 제목 글검색 서블렛
     */
    protected void findTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## 제목글검색 요청 서비스");
		String title = request.getParameter("title");
		
		System.out.println("title: " + title +"//");
		
		if(errorGenerate(request, response, title, "검색어를 입력해주세요!") != true ) {
			return;
		}
		
		ArrayList<IdeaBoard> ideaBoards = boardService.businessFindByTitle(title);
		if(ideaBoards != null) {
			System.out.println("글검색 성공");
			request.setAttribute("ideaBoards", ideaBoards);
			request.getRequestDispatcher("search.jsp").forward(request, response);
		} else {
			System.out.println("글검색 없음");
			request.setAttribute("message", "해당되는 검색이 없습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
    /**
     * 글삭제 서블렛
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## 글삭제 요청 서비스");
		HttpSession session = request.getSession(false);
		String email;
		if (session != null) {
			email = (String) session.getAttribute("email");
		} else {
			email = null;
		}
		int boardIdx = Integer.parseInt(request.getParameter("boardIdx"));
		if(boardIdx == 0) {
			System.out.println("보드인덱스  0");
			return;
		}
		if(email == null) {
			System.out.println("email null");
			return;
		}
		int isResult = boardService.businessDelete(boardIdx);
		if(isResult != 0) {
			System.out.println("글삭제 성공");
			request.getRequestDispatcher("mainService.jsp").forward(request, response);
		} else {
			System.out.println("글삭제 실패");
			request.setAttribute("message", "이미 가입된 회원입니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
    /**
     * 각각의 Boards 가져오기
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBoards(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## getBoards 요청 서비스");
		String category = request.getParameter("category");
		HttpSession session = request.getSession(false);
		int page = 1;
		if (request.getParameter("page") != null && request.getParameter("page").trim().equals("") == false) {
			page = Integer.parseInt(request.getParameter("page"));
			System.out.println(">>>" + page);
		}	
		if(category == null) {
			System.out.println("category is Null");
			return;
		}
		Map<String, Object> map = boardService.getBoards(page, category);
		map.put("sessionName", session.getAttribute("name"));
		request.setAttribute("map", map);
		
		if(category.equals("it")) {
			request.getRequestDispatcher("businessIt.jsp").forward(request, response);
		} else if(category.equals("media")) {
			request.getRequestDispatcher("businessMedia.jsp").forward(request, response);
		} else if(category.equals("market")) {
			request.getRequestDispatcher("businessSalesMarketing.jsp").forward(request, response);
		} else if(category.equals("etc")) {
			request.getRequestDispatcher("businessEtc.jsp").forward(request, response);
		}
		return;
	}
    /**
     * 각각의 Boards 가져오기
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## getBoard 요청 서비스");
		String category = request.getParameter("category");
		HttpSession session = request.getSession(false);
		int businessBoardsIndex = Integer.parseInt(request.getParameter("boardIndex"));		
		IdeaBoard ideaBoard = boardService.getBoard(businessBoardsIndex);
		if (ideaBoard != null) {
			request.setAttribute("businessBoardsIndex", ideaBoard.getIndex());
			request.setAttribute("title", ideaBoard.getTitle());
			request.setAttribute("content", ideaBoard.getTitle());
			request.setAttribute("result", ideaBoard.getResult());
			request.setAttribute("files", ideaBoard.getFiles());
			request.setAttribute("hits", ideaBoard.getHits());
			request.setAttribute("email", ideaBoard.getEmail());
			request.setAttribute("write_date", ideaBoard.getWriteDate());
			request.setAttribute("name", ideaBoard.getName());
		} else {
			System.out.println("BoardController getBoard 메서드 Null error");
			return;
		}
		if(category.equals("it")) {
			if(!session.getAttribute("name").equals(ideaBoard.getName())) {
				request.getRequestDispatcher("itInside.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("itInsideMine.jsp").forward(request, response);
			}
			
		} else if(category.equals("media")) {
			if(!session.getAttribute("name").equals(ideaBoard.getName())) {
				request.getRequestDispatcher("mediaInside.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("mediaInsideMine.jsp").forward(request, response);
			}
		} else if(category.equals("market")) {
			if(!session.getAttribute("name").equals(ideaBoard.getName())) {
				request.getRequestDispatcher("marketInside.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("marketInsideMine.jsp").forward(request, response);
			}
		} else if(category.equals("etc")) {
			if(!session.getAttribute("name").equals(ideaBoard.getName())) {
				request.getRequestDispatcher("etcInside.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("etcInsideMine.jsp").forward(request, response);
			}
		} else {
			System.out.println("BoardController getBoard 메서드 category null error");
			return;
		}
		return;
	}
  /**
   * 에러 처리
   * @param request
   * @param response
   * @param valueName
   * @param message
   * @return
   * @throws ServletException
   * @throws IOException
   */
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
