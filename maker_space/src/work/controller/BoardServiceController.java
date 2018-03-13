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

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		response.setContentType("text/html;charset=EUC-KR");
		try {
			String action = request.getParameter("action");
			System.out.println("Controller proccess action: " + action);
			switch (action) {
			case "write":
				write(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			case "edit":
				edit(request, response);
				break;
			case "find":
				find(request, response);
				break;
			case "findHashTag":
				// findHashTag(request, response);
				break;
			case "getBoards":
				getBoards(request, response);
				break;
			case "getBoard":
				getBoard(request, response);
				break;
			case "getBoardEdit":
				getBoardEdit(request, response);
				break;
			case "select":
				select(request, response);
				break;
			case "getTipBoards":
				getTipBoards(request, response);
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
	 * 글쓰기 서블렛
	 */
	protected void write(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## 글쓰기 요청 서비스");
		HttpSession session = request.getSession(false);
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String result = request.getParameter("result");
		String files = request.getParameter("files");
		String[] hashTag = request.getParameterValues("hashTag");
		String email = (String) session.getAttribute("email");
		int businessBoardsIdx;
		if (errorGenerate(request, response, title, "제목을 입력해주세요!") != true) {
			return;
		}
		if (errorGenerate(request, response, content, "비밀번호를 입력해주세요!") != true) {
			return;
		}
		if (errorGenerate(request, response, result, "아이디를 입력해주세요!") != true) {
			return;
		}
		if (category.equals("it")) {
			businessBoardsIdx = boardService.businessWrite("it", new IdeaBoard(title, content, result, files, 0, email,
					MyUtility.dateGenerator(), (String) session.getAttribute("name")));
			if (businessBoardsIdx != 0) {
				System.out.println("BoardService it boards 글쓰기 성공");
				boardService.businssRegisterHashTag(businessBoardsIdx, hashTag);
				getBoards(request, response);
			} else {
				System.out.println("BoardService it boards 글쓰기 실패");
				request.setAttribute("message", "글 쓰기에 오류가 생겼습니다!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else if (category.equals("market")) {
			businessBoardsIdx = boardService.businessWrite("market", new IdeaBoard(title, content, result, files, 0,
					email, MyUtility.dateGenerator(), (String) session.getAttribute("name")));
			if (businessBoardsIdx != 0) {
				System.out.println("BoardService market boards 글쓰기 성공");
				boardService.businssRegisterHashTag(businessBoardsIdx, hashTag);
				getBoards(request, response);
			} else {
				System.out.println("BoardService market boards 글쓰기 실패");
				request.setAttribute("message", "글 쓰기에 오류가 생겼습니다!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else if (category.equals("media")) {
			businessBoardsIdx = boardService.businessWrite("media", new IdeaBoard(title, content, result, files, 0,
					email, MyUtility.dateGenerator(), (String) session.getAttribute("name")));
			if (businessBoardsIdx != 0) {
				System.out.println("BoardService media boards 글쓰기 성공");
				boardService.businssRegisterHashTag(businessBoardsIdx, hashTag);
				getBoards(request, response);
			} else {
				System.out.println("BoardService it boards 글쓰기 실패");
				request.setAttribute("message", "글 쓰기에 오류가 생겼습니다!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else if (category.equals("etc")) {
			businessBoardsIdx = boardService.businessWrite("etc", new IdeaBoard(title, content, result, files, 0, email,
					MyUtility.dateGenerator(), (String) session.getAttribute("name")));
			if (businessBoardsIdx != 0) {
				System.out.println("BoardService etc boards 글쓰기 성공");
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
	 * find 종합 서블릿
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## 검색 요청 서비스");
		System.out.println("searchMethod:" + request.getParameter("searchMethod"));
		System.out.println("searchContent:" + request.getParameter("searchContent"));
		int searchMethod = Integer.parseInt(request.getParameter("searchMethod"));
		String category = request.getParameter("category");
		String searchContent = request.getParameter("searchContent");

		HttpSession session = request.getSession(false);
		int page = 1;
		if (request.getParameter("page") != null && request.getParameter("page").trim().equals("") == false) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (errorGenerate(request, response, searchContent, "검색어를 입력해주세요!") != true) {
			return;
		}
		if (category == null) {
			System.out.println("category is Null");
			return;
		}
		Map<String, Object> map = boardService.businessFind(page, searchMethod, searchContent, category);
		map.put("sessionName", session.getAttribute("name"));
		request.setAttribute("map", map);
		request.setAttribute("searchMethod", searchMethod);
		request.setAttribute("searchContent", searchContent);
		request.setAttribute("category", category);
		request.getRequestDispatcher("search.jsp").forward(request, response);
		return;
	}

	/**
	 * 글삭제 서블렛
	 */
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## 글삭제 요청 서비스");
		int businessBoardsIdx = Integer.parseInt(request.getParameter("businessBoardsIdx"));
		int isResult = boardService.businessDelete(businessBoardsIdx);
		if (isResult != 0) {
			System.out.println("글삭제 성공");
			getBoards(request, response);
		} else {
			System.out.println("글삭제 실패");
			request.setAttribute("message", "글삭제가 실패하였습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * 글 수정 서블릿
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## 글수정 요청 서비스");
		int businessBoardsIdx = Integer.parseInt(request.getParameter("businessBoardsIdx"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String result = request.getParameter("result");
		String files = request.getParameter("files");
		int isResult = boardService.businessChange(businessBoardsIdx, title, content, result, files);
		if (isResult != 0) {
			getBoardEdit(request, response);
		} else {
			System.out.println("글수정 실패");
			request.setAttribute("message", "글수정을 실패하였습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
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

	/**
	 * 제목 글검색 서블렛
	 */
	// protected void findTitle(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// System.out.println("\n## 제목글검색 요청 서비스");
	// String category = request.getParameter("category");
	// String searchContent = request.getParameter("searchContent");
	// HttpSession session = request.getSession(false);
	// int page = 1;
	// if (request.getParameter("page") != null &&
	// request.getParameter("page").trim().equals("") == false) {
	// page = Integer.parseInt(request.getParameter("page"));
	// }
	// if (errorGenerate(request, response, searchContent, "제목 검색어를 입력해주세요!") !=
	// true) {
	// return;
	// }
	// if (category == null) {
	// System.out.println("category is Null");
	// return;
	// }
	// Map<String, Object> map =boardService.businessFindByTitle(page,
	// searchContent, category);
	// map.put("sessionName", session.getAttribute("name"));
	// request.setAttribute("category", category);
	// request.setAttribute("map", map);
	// request.getRequestDispatcher("search.jsp").forward(request, response);
	// return;
	// }
	protected void getBoards(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getBoards 요청 서비스");
		String category = request.getParameter("category");
		System.out.println("??" + category);
		HttpSession session = request.getSession(false);
		System.out.println("category:" + category);
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
		Map<String, Object> map = boardService.getBoards(page, category);
		map.put("sessionName", session.getAttribute("name"));
		request.setAttribute("category", category);
		request.setAttribute("map", map);
		request.getRequestDispatcher("businessBoard.jsp").forward(request, response);
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
	protected void getBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getBoard 요청 서비스");
		String category = request.getParameter("category");
		HttpSession session = request.getSession(false);
		int businessBoardsIdx = Integer.parseInt(request.getParameter("businessBoardsIdx"));
		System.out.println("getBoard category: " + category);
		System.out.println("getBoard businessBoardsIdx: " + businessBoardsIdx);
		IdeaBoard ideaBoard = boardService.getBoard(businessBoardsIdx, category);
		if (ideaBoard != null) {
			request.setAttribute("businessBoardsIdx", ideaBoard.getIndex());
			request.setAttribute("title", ideaBoard.getTitle());
			request.setAttribute("content", ideaBoard.getTitle());
			request.setAttribute("result", ideaBoard.getResult());
			request.setAttribute("files", ideaBoard.getFiles());
			// request.setAttribute("hits", ideaBoard.getHits());
			request.setAttribute("email", ideaBoard.getEmail());
			request.setAttribute("write_date", ideaBoard.getWriteDate());
			request.setAttribute("name", ideaBoard.getName());
		} else {
			System.out.println("BoardController getBoard 메서드 Null error");
			return;
		}
		if (!session.getAttribute("name").equals(ideaBoard.getName())) {
			int hits = boardService.updateHits(businessBoardsIdx);
			request.setAttribute("hits", hits);
			request.setAttribute("category", category);
			System.out.println("hits: " + hits);
			request.getRequestDispatcher("insideBoardOthers.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("insideBoardMy.jsp").forward(request, response);
		}
		return;
	}

	/**
	 * Edit에서 특정 게시판 가져오는 서블렛 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getBoardEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getEditBoard 요청 서비스");
		String category = request.getParameter("category");
		String function = request.getParameter("function");
		HttpSession session = request.getSession(false);
		int businessBoardsIdx = Integer.parseInt(request.getParameter("businessBoardsIdx"));

		IdeaBoard ideaBoard = boardService.getBoard(businessBoardsIdx, category);
		if (ideaBoard != null) {
			request.setAttribute("businessBoardsIdx", ideaBoard.getIndex());
			request.setAttribute("title", ideaBoard.getTitle());
			request.setAttribute("content", ideaBoard.getTitle());
			request.setAttribute("result", ideaBoard.getResult());
			request.setAttribute("files", ideaBoard.getFiles());
			// request.setAttribute("hits", ideaBoard.getHits());
			request.setAttribute("email", ideaBoard.getEmail());
			request.setAttribute("write_date", ideaBoard.getWriteDate());
			request.setAttribute("name", ideaBoard.getName());
		} else {
			System.out.println("BoardController getEditBoard 메서드 Null error");
			return;
		}
		if (function != null) {
			request.getRequestDispatcher("boardEdit.jsp").forward(request, response);
		} else {
			if (!session.getAttribute("name").equals(ideaBoard.getName())) {
				request.getRequestDispatcher("insideBoardOthers.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("insideBoardMy.jsp").forward(request, response);
			}
		}
		return;
	}

	/**
	 * 글 채택 서블렛
	 * 
	 * @param request
	 * @param response
	 */

	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## 글 채택 요청 서비스");
		String category = request.getParameter("category");

		int boardIdx = Integer.parseInt(request.getParameter("index"));
		System.out.println(" 스크랩 서비스  컨트롤러 :: boardIdx : " + boardIdx);
		if (boardIdx == 0) {
			System.out.println("보드인덱스  0");
			return;
		}

		int isResult = boardService.registerMySelect(category, boardIdx);
		if (isResult != 0) {
			System.out.println("글 채택 성공");
			// request.getRequestDispatcher("myProcess.jsp").forward(request, response);
			PrintWriter out = response.getWriter();
			response.resetBuffer();
			response.setContentType("text/html;charset=euc-kr");
			out.println("<script language='javascript'>");
			out.print("alert(\"");
			out.print("채택 되었습니다.");
			out.println("\");");
			out.println("history.go(-1)");
			out.println("</script>");
			response.flushBuffer();

		} else {
			System.out.println("글 채택 실패");
			request.setAttribute("message", "이미 가입된 회원입니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	protected void getTipBoards(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getTipBoards 요청 서비스");
		HttpSession session = request.getSession(false);
		String category = request.getParameter("category");
		int page = 1;
		if (request.getParameter("page") != null && request.getParameter("page").trim().equals("") == false) {
			page = Integer.parseInt(request.getParameter("page"));
			System.out.println(">>>" + page);
		}

		Map<String, Object> map = boardService.getTipBoards(page, category);
		map.put("sessionName", session.getAttribute("name"));
		request.setAttribute("map2", map);

		if (category.equals("myTips")) {
			request.getRequestDispatcher("myIdea.jsp").forward(request, response);
		}
		if (category.equals("tips")) {
			request.getRequestDispatcher("coolTips.jsp").forward(request, response);
		}
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
