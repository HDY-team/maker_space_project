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
     * �⺻ ������
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
				System.out.println("\n## ���� �غ����Դϴ�. �������� �ʴ� �����Դϴ�.");
				break;
		}	
		} catch(NullPointerException e){
			e.printStackTrace();
		} finally {
			
		}
	}
    /**
     * �۾��� ����
     */
    protected void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## �۾��� ��û ����");
		HttpSession session = request.getSession(false);
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String result = request.getParameter("result");
		String files = request.getParameter("files");
		String[] hashTag = request.getParameterValues("hashTag");
		String email = (String)session.getAttribute("email");
		int businessBoardsIdx;
		if(errorGenerate(request, response, title, "������ �Է����ּ���!") != true ) {
			return;
		}
		if(errorGenerate(request, response, content, "��й�ȣ�� �Է����ּ���!") != true) {
			return;
		}
		if(errorGenerate(request, response, result, "���̵� �Է����ּ���!") != true) {
			return;
		}
		if(category.equals("it")) {
			businessBoardsIdx = boardService.businessWrite("it", new IdeaBoard(title, content, result, files, 0, email, MyUtility.dateGenerator(), (String) session.getAttribute("name")));
			if(businessBoardsIdx != 0 ) {
				System.out.println("BoardService it boards �۾��� ����");
				boardService.businssRegisterHashTag(businessBoardsIdx, hashTag);
				getBoards(request, response);
			} else {
				System.out.println("BoardService it boards �۾��� ����");
				request.setAttribute("message", "�� ���⿡ ������ ������ϴ�!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
		
	}
    
    /**
     * ���� �۰˻� ����
     */
    protected void findTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## ����۰˻� ��û ����");
		String title = request.getParameter("title");
		
		System.out.println("title: " + title +"//");
		
		if(errorGenerate(request, response, title, "�˻�� �Է����ּ���!") != true ) {
			return;
		}
		
		ArrayList<IdeaBoard> ideaBoards = boardService.businessFindByTitle(title);
		if(ideaBoards != null) {
			System.out.println("�۰˻� ����");
			request.setAttribute("ideaBoards", ideaBoards);
			request.getRequestDispatcher("search.jsp").forward(request, response);
		} else {
			System.out.println("�۰˻� ����");
			request.setAttribute("message", "�ش�Ǵ� �˻��� �����ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
    /**
     * �ۻ��� ����
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## �ۻ��� ��û ����");
		HttpSession session = request.getSession(false);
		String email;
		if (session != null) {
			email = (String) session.getAttribute("email");
		} else {
			email = null;
		}
		int boardIdx = Integer.parseInt(request.getParameter("boardIdx"));
		if(boardIdx == 0) {
			System.out.println("�����ε���  0");
			return;
		}
		if(email == null) {
			System.out.println("email null");
			return;
		}
		int isResult = boardService.businessDelete(boardIdx);
		if(isResult != 0) {
			System.out.println("�ۻ��� ����");
			request.getRequestDispatcher("mainService.jsp").forward(request, response);
		} else {
			System.out.println("�ۻ��� ����");
			request.setAttribute("message", "�̹� ���Ե� ȸ���Դϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
    /**
     * ������ Boards ��������
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBoards(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## getBoards ��û ����");
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
     * ������ Boards ��������
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## getBoard ��û ����");
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
			System.out.println("BoardController getBoard �޼��� Null error");
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
			System.out.println("BoardController getBoard �޼��� category null error");
			return;
		}
		return;
	}
  /**
   * ���� ó��
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
