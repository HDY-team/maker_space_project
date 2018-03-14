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
	 * �⺻ ������
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
				System.out.println("\n## ���� �غ����Դϴ�. �������� �ʴ� �����Դϴ�.");
				break;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {

		}
	}
	protected void getTipBoardEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getTipBoardEdit ��û ����");
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
			System.out.println("BoardController getEditBoard �޼��� Null error");
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
		System.out.println("\n## �ۼ��� ��û ����");
		int tipBoardsIdx = Integer.parseInt(request.getParameter("tipBoardsIdx"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String result = request.getParameter("result");
		String files = request.getParameter("files");
		int isResult = tipBoardService.tipChange(tipBoardsIdx, title, content, result, files);
		if (isResult != 0) {
			getTipBoardEdit(request, response);
		} else {
			System.out.println("�ۼ��� ����");
			request.setAttribute("message", "�ۼ����� �����Ͽ����ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	/**
	 * �ۻ��� ����
	 */
	protected void getTipBoardDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getTipBoardDelete ��û ����");
		System.out.println("category: " + (String) request.getParameter("category"));
		int tipBoardsIdx = Integer.parseInt(request.getParameter("tipBoardsIdx"));
		int isResult = tipBoardService.tipDelete(tipBoardsIdx);
		if (isResult != 0) {
			System.out.println("�ۻ��� ����");
			getTipBoards(request, response);
		} else {
			System.out.println("�ۻ��� ����");
			request.setAttribute("message", "�ۻ����� �����Ͽ����ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/**
	 * ���� ���忡�� ��ũ�� �ϴ� �޼���
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */

	private void scrap(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("\n## �� ��ũ�� ��û ����");

		HttpSession session = request.getSession(false);
		String email = (String) session.getAttribute("email");
		if (!isLoginMember(request, response)) {
			PrintWriter out = response.getWriter();
			response.resetBuffer();
			response.setContentType("text/html;charset=euc-kr");
			out.println("<script language='javascript'>");
			out.print("alert(\"");
			out.print("ȸ�� ���� �����Դϴ�. ȸ������ �� �̿����ּ���.");
			out.println("\");");
			out.println("history.go(-1)");
			out.println("</script>");
			response.flushBuffer();
			return;
		}
		String category = request.getParameter("category");
		int tipBoardsIdx = Integer.parseInt(request.getParameter("tipBoardsIdx"));
		System.out.println(" ��ũ�� ����  ��Ʈ�ѷ� :: boardIdx : " + tipBoardsIdx);
		if (tipBoardsIdx == 0) {
			System.out.println("�����ε���  0");
			return;
		}

		int isResult = tipBoardService.registerMyScraps(category, tipBoardsIdx, email);
		if (isResult != 0) {
			int scraps = tipBoardService.updateTipHits(tipBoardsIdx);
			request.setAttribute("scraps", scraps);
			request.setAttribute("category", category);
			System.out.println("scraps: " + scraps);
			System.out.println("�� ��ũ�� ����");
			// request.getRequestDispatcher("myProcess.jsp").forward(request, response);
			PrintWriter out = response.getWriter();
			response.resetBuffer();
			response.setContentType("text/html;charset=euc-kr");
			out.println("<script language='javascript'>");
			out.print("alert(\"");
			out.print("��ũ�� �Ǿ����ϴ�.");
			out.println("\");");
			out.println("history.go(-1)");
			out.println("</script>");
			response.flushBuffer();

		} else {
			System.out.println("�� ä�� ����");
			request.setAttribute("message", "�̹� ��ũ���Ǿ����ϴ�.");
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
			System.out.println("BoardController getTipBoard �޼��� Null error");
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
			System.out.println("BoardController getBoard �޼��� category null error");
			return;
		}
		return;
	}

	private void getScrapBoards(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getScrapBoards ��û ����");
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
			System.out.println("BoardController getTipBoard �޼��� Null error");
			return;
		}
		request.setAttribute("category", category);
		request.getRequestDispatcher("insideScrapBoardOthers.jsp").forward(request, response);
		return;
	}

	private void getTipBoards(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## getTipBoards ��û ����");
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
		System.out.println("\n## �۾��� ��û ����");
		HttpSession session = request.getSession(false);
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String result = request.getParameter("result");
		String files = request.getParameter("files");
		String[] hashTag = request.getParameterValues("hashTag");
		String email = (String) session.getAttribute("email");
		int tipBoardIdx;
		if (errorGenerate(request, response, title, "������ �Է����ּ���!") != true) {
			return;
		}
		if (errorGenerate(request, response, content, "��й�ȣ�� �Է����ּ���!") != true) {
			return;
		}
		if (errorGenerate(request, response, result, "���̵� �Է����ּ���!") != true) {
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
				System.out.println("tipBoards �۾��� ����");
				tipBoardService.businssRegisterHashTag(tipBoardIdx, hashTag);

			} else {
				System.out.println("tipBoards �۾��� ����");
				request.setAttribute("message", "�� ���⿡ ������ ������ϴ�!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
	}

	/**
	 * �α��� ���� Ȯ�� �޼��� ����ó��
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
		System.out.println("\n## �˻� ��û ����");
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
		if (errorGenerate(request, response, searchContent, "�˻�� �Է����ּ���!") != true) {
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
	 * ���� ó��
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
