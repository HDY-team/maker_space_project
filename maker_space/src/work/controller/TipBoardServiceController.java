package work.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import work.model.dto.IdeaBoard;
import work.model.dto.TipIdeaBoard;
import work.model.service.BoardService;
import work.util.MyUtility;

/**
 * Servlet implementation class TipBoardServiceController
 */
public class TipBoardServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService tipBoardService;

	/**
	 * 기본 생성자
	 */
	public TipBoardServiceController() {
		tipBoardService = BoardService.getInstance();
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			System.out.println("Tip controller process action :" + action);
			switch (action) {
			case "write":
				write(request, response);
				break;
			case "getTipBoards":
				getTipBoards(request, response);
				break;
			case "getTipBoard":
				getTipBoard(request, response);
				break;
			case "getScrapBoards":
				getScrapBoards(request, response);
				break;
			case "getScrapBoard":
				getScrapBoard(request, response);
				break;
			case "scrap":
				scrap(request, response);
				break;
			case "getTipBoardEdit":
				getTipBoardEdit(request, response);
				break;
			case "getTipBoardDelete":
				getTipBoardDelete(request, response);
				break;
			case "edit":
				edit(request, response);
				break;
			case "find":
				find(request, response);
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
	protected void getTipBoardEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getTipBoardEdit 요청 서비스");
		String category = request.getParameter("category");
		String function = request.getParameter("function");
		HttpSession session = request.getSession(false);
		int tipBoardsIdx = Integer.parseInt(request.getParameter("tipBoardsIdx"));

		TipIdeaBoard tipBoard = tipBoardService.getTipBoard(tipBoardsIdx);
		if (tipBoard != null) {
			request.setAttribute("tipBoardsIdx", tipBoard.getTipIdx());
			request.setAttribute("title", tipBoard.getTitle());
			request.setAttribute("content", tipBoard.getContent());
			request.setAttribute("result", tipBoard.getResult());
			request.setAttribute("files", tipBoard.getFiles());
			request.setAttribute("hits", tipBoard.getHits());
			request.setAttribute("scraps", tipBoard.getScraps());
			request.setAttribute("email", tipBoard.getEmail());
			request.setAttribute("write_date", tipBoard.getWriteDate());
			request.setAttribute("name", tipBoard.getName());
		} else {
			System.out.println("BoardController getEditBoard 메서드 Null error");
			return;
		}
		if (function != null) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!here");
			request.getRequestDispatcher("tipBoardEdit.jsp").forward(request, response);
		} else {
			if (!session.getAttribute("name").equals(tipBoard.getName())) {
				request.getRequestDispatcher("InsideTipBoardOthers.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("InsideTipBoardMy.jsp").forward(request, response);
			}
		}
		return;
	}
	protected void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## 글수정 요청 서비스");
		int tipBoardsIdx = Integer.parseInt(request.getParameter("tipBoardsIdx"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String result = request.getParameter("result");
		String files = request.getParameter("files");
		int isResult = tipBoardService.tipChange(tipBoardsIdx, title, content, result, files);
		if (isResult != 0) {
			getTipBoardEdit(request, response);
		} else {
			System.out.println("글수정 실패");
			request.setAttribute("message", "글수정을 실패하였습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	/**
	 * 글삭제 서블렛
	 */
	protected void getTipBoardDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getTipBoardDelete 요청 서비스");
		System.out.println("category: " + (String) request.getParameter("category"));
		int tipBoardsIdx = Integer.parseInt(request.getParameter("tipBoardsIdx"));
		int isResult = tipBoardService.tipDelete(tipBoardsIdx);
		if (isResult != 0) {
			System.out.println("글삭제 성공");
			getTipBoards(request, response);
		} else {
			System.out.println("글삭제 실패");
			request.setAttribute("message", "글삭제가 실패하였습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/**
	 * 꿀팁 보드에서 스크랩 하는 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */

	private void scrap(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("\n## 글 스크랩 요청 서비스");

		HttpSession session = request.getSession(false);
		String email = (String) session.getAttribute("email");
		if (!isLoginMember(request, response)) {
			PrintWriter out = response.getWriter();
			response.resetBuffer();
			response.setContentType("text/html;charset=euc-kr");
			out.println("<script language='javascript'>");
			out.print("alert(\"");
			out.print("회원 가입 서비스입니다. 회원가입 후 이용해주세요.");
			out.println("\");");
			out.println("history.go(-1)");
			out.println("</script>");
			response.flushBuffer();
			return;
		}
		String category = request.getParameter("category");
		int tipBoardsIdx = Integer.parseInt(request.getParameter("tipBoardsIdx"));
		System.out.println(" 스크랩 서비스  컨트롤러 :: boardIdx : " + tipBoardsIdx);
		if (tipBoardsIdx == 0) {
			System.out.println("보드인덱스  0");
			return;
		}

		int isResult = tipBoardService.registerMyScraps(category, tipBoardsIdx, email);
		if (isResult != 0) {
			int scraps = tipBoardService.updateTipHits(tipBoardsIdx);
			request.setAttribute("scraps", scraps);
			request.setAttribute("category", category);
			System.out.println("scraps: " + scraps);
			System.out.println("글 스크랩 성공");
			// request.getRequestDispatcher("myProcess.jsp").forward(request, response);
			PrintWriter out = response.getWriter();
			response.resetBuffer();
			response.setContentType("text/html;charset=euc-kr");
			out.println("<script language='javascript'>");
			out.print("alert(\"");
			out.print("스크랩 되었습니다.");
			out.println("\");");
			out.println("history.go(-1)");
			out.println("</script>");
			response.flushBuffer();

		} else {
			System.out.println("글 채택 실패");
			request.setAttribute("message", "이미 스크랩되었습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	private void getTipBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String category = request.getParameter("category");
		HttpSession session = request.getSession(false);
		int tipBoardsIdx = Integer.parseInt(request.getParameter("tipBoardsIdx"));
		System.out.println("tipBoardsIdx : " + tipBoardsIdx);
		TipIdeaBoard tipBoard = tipBoardService.getTipBoard(tipBoardsIdx);
		if (tipBoard != null) {
			request.setAttribute("tipBoardsIdx", tipBoardsIdx);
			request.setAttribute("title", tipBoard.getTitle());
			request.setAttribute("content", tipBoard.getContent());
			request.setAttribute("result", tipBoard.getResult());
			request.setAttribute("files", tipBoard.getFiles());
			request.setAttribute("hits", tipBoard.getHits());
			request.setAttribute("scraps", tipBoard.getScraps());
			request.setAttribute("email", tipBoard.getEmail());
			request.setAttribute("write_date", tipBoard.getWriteDate());
			request.setAttribute("name", tipBoard.getName());
		} else {
			System.out.println("BoardController getTipBoard 메서드 Null error");
			return;
		}
		request.setAttribute("category", category);
		if (category.equals("tips")) {
			if (session.getAttribute("name").equals(tipBoard.getName())) {
				request.getRequestDispatcher("InsideTipBoardMy.jsp").forward(request, response);
			} else {
				int hits = tipBoardService.updateTipHits(tipBoardsIdx);
				request.setAttribute("hits", hits);
				int scraps = tipBoardService.updateScrapHits(tipBoardsIdx);
				request.setAttribute("scraps", scraps);
				request.setAttribute("category", category);
				request.getRequestDispatcher("InsideTipBoardOthers.jsp").forward(request, response);
			}
		} else if (category.equals("myTips")) {
			if (session.getAttribute("name").equals(tipBoard.getName())) {
				request.getRequestDispatcher("InsideTipBoardMy.jsp").forward(request, response);
			}
		} else {
			System.out.println("BoardController getBoard 메서드 category null error");
			return;
		}
		return;
	}

	private void getScrapBoards(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getScrapBoards 요청 서비스");
		HttpSession session = request.getSession(false);
		String email = (String) session.getAttribute("email");
		String category = request.getParameter("category");
		int page = 1;
		if (request.getParameter("page") != null && request.getParameter("page").trim().equals("") == false) {
			page = Integer.parseInt(request.getParameter("page"));
			System.out.println(">>>" + page);
		}
		Map<String, Object> map = tipBoardService.getTipBoards(page, category, email);
		System.out.println("!!!!!!!!!!!!!!!>>>> " + map.get("lists").toString());
		request.setAttribute("map", map);
		request.getRequestDispatcher("myScraps.jsp").forward(request, response);
		return;
	}

	private void getScrapBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String category = request.getParameter("category");
		int tipBoardsIdx = Integer.parseInt(request.getParameter("tipBoardsIdx"));
		System.out.println("tipBoardsIdx : " + tipBoardsIdx);
		TipIdeaBoard tipBoard = tipBoardService.getTipBoard(tipBoardsIdx);
		if (tipBoard != null) {
			request.setAttribute("tipBoardsIdx", tipBoardsIdx);
			request.setAttribute("title", tipBoard.getTitle());
			request.setAttribute("content", tipBoard.getContent());
			request.setAttribute("result", tipBoard.getResult());
			request.setAttribute("files", tipBoard.getFiles());
			request.setAttribute("hits", tipBoard.getHits());
			request.setAttribute("scraps", tipBoard.getScraps());
			request.setAttribute("email", tipBoard.getEmail());
			request.setAttribute("write_date", tipBoard.getWriteDate());
			request.setAttribute("name", tipBoard.getName());
		} else {
			System.out.println("BoardController getTipBoard 메서드 Null error");
			return;
		}
		request.setAttribute("category", category);
		request.getRequestDispatcher("insideScrapBoardOthers.jsp").forward(request, response);
		return;
	}

	private void getTipBoards(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getTipBoards 요청 서비스");
		HttpSession session = request.getSession(false);
		String email = session.getAttribute("email").toString();
		String category = request.getParameter("category");
		System.out.println("!!!!!!!!!!category: " + category);
		int page = 1;
		if (request.getParameter("page") != null && request.getParameter("page").trim().equals("") == false) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		Map<String, Object> map = tipBoardService.getTipBoards(page, category, email);
		//map.put("sessionName", (String) session.getAttribute("name"));
		request.setAttribute("tipMap", map);
		request.setAttribute("category", category);
		if (category.equals("myTips")) {
			request.getRequestDispatcher("myTips.jsp").forward(request, response);
		} else if (category.equals("tips")) {
			request.getRequestDispatcher("coolTips.jsp").forward(request, response);
		}
		return;
	}

	private void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## 글쓰기 요청 서비스");
		HttpSession session = request.getSession(false);
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String result = request.getParameter("result");
		String files = request.getParameter("files");
		String[] hashTag = request.getParameterValues("hashTag");
		String email = (String) session.getAttribute("email");
		int tipBoardIdx;
		if (errorGenerate(request, response, title, "제목을 입력해주세요!") != true) {
			return;
		}
		if (errorGenerate(request, response, content, "비밀번호를 입력해주세요!") != true) {
			return;
		}
		if (errorGenerate(request, response, result, "아이디를 입력해주세요!") != true) {
			return;
		}
		if (category.equals("tips")) {
			// (String title, String content, String result, String files, int hits, String
			// email,String writeDate, String name, int scraps)
			tipBoardIdx = tipBoardService.tipWrite(category, new TipIdeaBoard(title, content, result, files, 0, email,
					MyUtility.dateGenerator(), (String) session.getAttribute("name"), 0));
			getTipBoards(request, response);
			System.out.println("tipBoardIDx ::: " + tipBoardIdx);
			if (tipBoardIdx != 0) {
				System.out.println("tipBoards 글쓰기 성공");
				tipBoardService.businssRegisterHashTag(tipBoardIdx, hashTag);

			} else {
				System.out.println("tipBoards 글쓰기 실패");
				request.setAttribute("message", "글 쓰기에 오류가 생겼습니다!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
	}

	/**
	 * 로그인 유무 확인 메서드 보안처리
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean isLoginMember(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("name") != null) {
			return true;
		}
		return false;
	}
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
		Map<String, Object> map = tipBoardService.tipFind(page, searchMethod, searchContent, category);
		map.put("sessionName", session.getAttribute("name"));
		request.setAttribute("map", map);
		request.setAttribute("searchMethod", searchMethod);
		request.setAttribute("searchContent", searchContent);
		request.setAttribute("category", category);
		request.getRequestDispatcher("tipSearch.jsp").forward(request, response);
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
