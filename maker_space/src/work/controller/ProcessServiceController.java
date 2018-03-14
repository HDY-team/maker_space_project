/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import work.model.dto.IdeaBoard;
import work.model.service.BoardService;
import work.model.service.ProcessService;
import work.util.MyUtility;

/**
 * Servlet Controller - BoardServiceController
 */
public class ProcessServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProcessService processService;

	/**
	 * 기본 생성자
	 */
	public ProcessServiceController() {
		processService = ProcessService.getInstance();
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		response.setContentType("text/html;charset=EUC-KR");
		try {
			String action = request.getParameter("action");
			System.out.println("ProcessController proccess action: " + action);
			switch (action) {
			case "getProcessBoard":
				getProcessBoard(request, response);
				break;
			case "getProcessBoards":
				getProcessBoards(request, response);
				break;
			case "mySelectedCancel":
				mySelectedCancel(request, response);
				break;
			case "mySelectedComplete":
				mySelectedComplete(request, response);
				break;
			case "mySelectCancel":
				mySelectCancel(request, response);
				break;
			case "complete":
				complete(request, response);
				break;	
			default:
				System.out.println("\n## 서비스 준비중입니다. 제공되지 않는 서비스입니다.");
				break;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * 각각의 Boards 가져오기
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getProcessBoards(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getProcessBoards 요청 서비스");
		// 채택받은 나의 아이디어 mySelected
		// 채택한 나의 아이디어 mySelect
		// 채택완료 selectComplete
		String category = request.getParameter("category");
		System.out.println("getProcessBoards category:" + category);
		HttpSession session = request.getSession(false);
		int page = 1;
		if (request.getParameter("page") != null && request.getParameter("page").trim().equals("") == false) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (category == null) {
			System.out.println("category is Null");
			return;
		}
		if (session == null) {
			System.out.println("session is Null");
			return;
		}
		String email = session.getAttribute("email").toString();
		Map<String, Object> map = processService.getProcessBoards(page, category, email);
		map.put("sessionName", session.getAttribute("name"));
		request.setAttribute("category", category);
		request.setAttribute("map", map);
		if (category.equals("mySelected")) {
			request.getRequestDispatcher("mySelected.jsp").forward(request, response);
		} else if (category.equals("mySelect")) {
			request.getRequestDispatcher("mySelect.jsp").forward(request, response);
		} else if (category.equals("selectComplete")) {
			System.out.println("category:!!!!!: " + category);
			request.setAttribute("category", "selectComplete");
			request.getRequestDispatcher("selectComplete.jsp").forward(request, response);
		}
		return;
	}

	/**
	 * 각각의 Boards 가져오기
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void mySelectedCancel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getProcessBoard 요청 서비스");
		String category = request.getParameter("category");
		HttpSession session = request.getSession(false);
		String name = (String) session.getAttribute("name");
		int businessBoardsIdx = Integer.parseInt(request.getParameter("businessBoardsIdx"));
		System.out.println("getProcessBoard category: " + category);
		System.out.println("getProcessBoard businessBoardsIdx: " + businessBoardsIdx);
		
		processService.mySelectedCancel(businessBoardsIdx);
		request.setAttribute("category", "mySelected");
		request.setAttribute("businessBoardsIdx", businessBoardsIdx);
		getProcessBoards(request, response);
		return;
	}
	protected void getProcessBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getProcessBoard 요청 서비스");
		String category = request.getParameter("category");
		HttpSession session = request.getSession(false);
		String name = (String) session.getAttribute("name");
		// String email = (String)session.getAttribute("email");
		IdeaBoard ideaBoard = null;
		int businessBoardsIdx = Integer.parseInt(request.getParameter("businessBoardsIdx"));
		System.out.println("getProcessBoard category: " + category);
		System.out.println("getProcessBoard businessBoardsIdx: " + businessBoardsIdx);
		if (category.equals("mySelected")) {
			ideaBoard = processService.getProcessBoard(businessBoardsIdx, category);
		} else if (category.equals("mySelect")) {
			ideaBoard = processService.getProcessBoard(businessBoardsIdx, category);
		} else if (category.equals("selectComplete")) {
			ideaBoard = processService.getProcessBoard(businessBoardsIdx, category);
		}
		if (ideaBoard != null) {
			request.setAttribute("category", category);
			request.setAttribute("businessBoardsIdx", ideaBoard.getIndex());
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
		request.setAttribute("category", category);
		if (category.equals("mySelected")) {
			request.getRequestDispatcher("insideMySelected.jsp").forward(request, response);
		} else if (category.equals("mySelect")) {
			request.getRequestDispatcher("insideMySelect.jsp").forward(request, response);
		} else if (category.equals("selectComplete")) {
			request.getRequestDispatcher("insideSelectComeplete.jsp").forward(request, response);
		} else {
			System.out.println("ProcessController getProcessBoard 메서드 category null error");
			return;
		}
		return;
	}
	
	protected void mySelectCancel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## mySelectCancel 요청 서비스");
		String category = request.getParameter("category");
		int businessBoardsIdx = Integer.parseInt(request.getParameter("businessBoardsIdx"));
		System.out.println("mySelectCancel category: " + category);
		System.out.println("mySelectCancel businessBoardsIdx: " + businessBoardsIdx);

		processService.mySelectCancel(businessBoardsIdx);
		request.setAttribute("category", "mySelected");
		request.setAttribute("businessBoardsIdx", businessBoardsIdx);
		System.out.println("!@#@!#@#@!SS");
		getProcessBoards(request, response);
		return;
	}
	protected void mySelectedComplete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## mySelectedComplete 요청 서비스");
		String category = request.getParameter("category");
		HttpSession session = request.getSession(false);
		String name = (String) session.getAttribute("name");
		int businessBoardsIdx = Integer.parseInt(request.getParameter("businessBoardsIdx"));
		System.out.println("mySelectCancel category: " + category);
		System.out.println("mySelectCancel businessBoardsIdx: " + businessBoardsIdx);
		
		processService.mySelectedComplete(businessBoardsIdx);
		category = "mySelect";
		request.setAttribute("category", "mySelect");
		request.setAttribute("businessBoardsIdx", businessBoardsIdx);
		getProcessBoards(request, response);
		return;
	}
	protected void complete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## complete 요청 서비스");
		String category = request.getParameter("category");
		HttpSession session = request.getSession(false);
		String email = (String) session.getAttribute("email");
		int businessBoardsIdx = Integer.parseInt(request.getParameter("businessBoardsIdx"));
		System.out.println("complete category: " + category);
		System.out.println("complete businessBoardsIdx: " + businessBoardsIdx);
		
		processService.complete(businessBoardsIdx, email);
		request.setAttribute("category", "selectComplete");
		request.setAttribute("businessBoardsIdx", businessBoardsIdx);
		getProcessBoards(request, response);
		return;
	}
	
	/**
	 * 에러 처리
	 * 
	 * @param request
	 * @param response
	 * @param valueName
	 * @param message
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	protected boolean errorGenerate(HttpServletRequest request, HttpServletResponse response, String valueName,
			String message) throws ServletException, IOException {
		if (valueName == null || valueName.trim().length() == 0) {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}
}
