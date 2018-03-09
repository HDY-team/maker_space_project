package work.controller;

import java.io.IOException;
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
 * Servlet implementation class MemberServiceController
 */
public class MemberServiceController extends HttpServlet {

	int unusedEmail = 0;
	private MemberService memberService;

	public MemberServiceController() {
		memberService = MemberService.getInstance();
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		switch (action) {
		case "login":
			login(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "join":
			join(request, response);
			break;
		case "checkOverlap":
			checkOverlap(request, response);
			break;
		case "removeMember":
			removeMember(request, response);
			break;
		case "updateMember":
			updateMember(request, response);
			break;
		case "forgotPassword":
			forgotPassword(request, response);
			break;
		case "getMyInfo":
			getMyInfo(request, response);
			break;
		case "removeMemberByAdmin":
			removeMemberByAdmin(request, response);
			break;
		case "getInfoByAdmin":
			getInfoByAdmin(request, response);
			break;
		case "getAllInfoByAdmin":
			getAllInfoByAdmin(request, response);
			break;
		default:
			System.out.println("\n## ���� �غ����Դϴ�.");
			break;
		}
	}

	private void getAllInfoByAdmin(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("\n## ������ :: ��ü ȸ�� ��ȸ ��û");

		ArrayList<Member> members = new ArrayList<>();
		members = memberService.getAllInfoByAdmin();
		request.setAttribute("members", members);

		// request.getRequestDispatcher("allInfo.jsp").forward(request, response);
		// ��ü ���� ��ȸ �������� ����
	}

	private void getInfoByAdmin(HttpServletRequest request, HttpServletResponse response) {

	}

	private void getMyInfo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("\n## �� ���� ��ȸ ��û");

		HttpSession session = request.getSession(false); // false�� ���� ������ �޶�.
	
		Member member = new Member();
		member = memberService.getMyInfo((String) session.getAttribute("email"));
		// System.out.println(member.getMemberId());

		request.setAttribute("email", member.getEmail());
		request.setAttribute("name", member.getName());
		request.setAttribute("password", member.getPassword());
		request.setAttribute("mobile", member.getMobile());
		request.setAttribute("company", member.getCompany());
		request.setAttribute("grade", member.getGrade());
		request.setAttribute("point", member.getPoint());

		// request.getRequestDispatcher("").forward(request, response);
		// ���� ������ jsp�� ����
	}

	/**
	 * ��й�ȣ ���� �޼���
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void forgotPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		int result = memberService.forgotPassword(email, password);

		if (result != 0) {
			// Ż�� ����
			System.out.println("��й�ȣ ���� ����");
			String message = "���� �Ǿ����ϴ�";
			// ȸ������ ���� ������ �̵�
			request.setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {

			request.setAttribute("message", "���濡 �����߽��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * ȸ�� ���� ���� �޼��� �̸���, �н�����, ��ȭ��ȣ, �̸��� ���� ���� ---> �̸��ϵ� ���������ϰ��Ұ��ΰ�?
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String company = request.getParameter("company");

		int result = memberService.updateMember(email, password, mobile, company);

		if (result != 0) {
			// Ż�� ����
			System.out.println("���� ����");
			String message = "���� �Ǿ����ϴ�";
			// ȸ������ ���� ������ �̵�
			request.setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {

			request.setAttribute("message", "������ �����߽��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * ȸ�� Ż�� ��û �޼��� ȸ���� ���� Ż�� ���� �� ���Ǵ� �޼���
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void removeMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## ȸ�� Ż�� ��û");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		int result = memberService.removeMember(email, password);

		if (result != 0) {
			// Ż�� ����
			System.out.println("Ż�𼺰�");
			String message = "Ż�� �Ǿ����ϴ�";
			// ȸ������ ���� ������ �̵�
			request.setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {

			request.setAttribute("message", "Ż�� �����߽��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

	/**
	 * ȸ�� Ż�� ��û�޼��� (������)
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void removeMemberByAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## ������ - ȸ�� Ż�� ��û");

		String email = request.getParameter("email");
		int result = memberService.removeMemberByAdmin(email);

		if (result != 0) {
			// Ż�� ����
			System.out.println("Ż�𼺰�");
			String message = "Ż�� �Ǿ����ϴ�";
			// ȸ������ ���� ������ �̵�
			request.setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {

			request.setAttribute("message", "Ż�� �����߽��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * �̸��� �ߺ�Ȯ�� �޼��� �����ͺ��̽����� ������ Ȯ���� ���������� �ľ��Ѵ�.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * 
	 *             -- ���Կ�û���������� �������� �Է��ϰ� -- �̸����ߺ���û -- �������� �̸��� �ߺ� ������ �ؼ� -- �ߺ��� ����
	 *             �ʾ����� ��û�� ���޹��� ������ ��ü�����ͼ� dto ��ü ��� -- dto ��ü�� �������� ������ ����(��������������
	 *             �����Ϸ�� ������ �ʱ�ȭ ������ ����) -- ������������ ȸ�������������� �����ؼ� -- ����������������
	 *             �̸����ߺ������� �ϷῩ�θ� Ȯ���ؼ� -- �Ϸ�� �����̸� �����Ǿ��ִ� dto ��ü�� �Ӽ��� �����ͼ� --
	 *             ȸ������������ �Է¾���� �ش� �׸��� value="<%= dto.getEmail() %> �⺻������ ����
	 */
	private void checkOverlap(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n## ���� �ߺ�Ȯ�� ��û");
		Member member = new Member();

	}

	/**
	 * �α��� ��û
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## �α��� ��û");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (errorGenerate(request, response, email, "�̸����� �Է����ּ���!") != true) {
			return;
		}
		if (errorGenerate(request, response, password, "�н����带 �Է����ּ���!") != true) {
			return;
		}
		String grade = memberService.login(email, password);
		System.out.println(">>>!!");
		if (grade != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("email", email);
			session.setAttribute("grade", grade);
			Member getMember = memberService.getMyInfo(email);
			session.setAttribute("name", getMember.getName());
			if(getMember != null) {
				session.setAttribute("name", getMember.getName());
				session.setAttribute("company", getMember.getCompany());
			}
			request.getRequestDispatcher("mainService.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "�߸��� �����Է��Դϴ�!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * �α׾ƿ� ��û
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("\n## �α׾ƿ� ��û");

		HttpSession session = request.getSession(false);
		if (session != null) {
			if (session.getAttribute("grade") != null) {
				session.removeAttribute("grade");
			}
			session.invalidate();
		}
		response.sendRedirect("index.jsp");
	}

	/**
	 * ȸ������ ��û �޼���
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n## ȸ������ ��û");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String company = request.getParameter("company");

		if (errorGenerate(request, response, email, "�̸��ϸ� �Է����ּ���!") != true) {
			return;
		}
		if (errorGenerate(request, response, password, "��й�ȣ�� �Է����ּ���!") != true) {
			return;
		}
		if (errorGenerate(request, response, name, "�̸��� �Է����ּ���!") != true) {
			return;
		}
		if (errorGenerate(request, response, mobile, "�޴���ȭ��ȣ�� �Է����ּ���!") != true) {
			return;
		}
		if (errorGenerate(request, response, company, "ȸ����� �Է����ּ���!") != true) {
			return;
		}
		if (!(password.trim().length() >= 6 && password.trim().length() <= 30)) {
			String message = "��й�ȣ�� 6�ڸ� �̻� �ٽ� �Է����ּ���!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		if (!password.equals(confirmPassword)) {
			String message = "��й�ȣ �Է��� ��ġ���� �ʽ��ϴ�!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		int result = memberService.join(new Member(email, password, name, mobile, company, "G", 0));
		if (result != 0) {
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			request.setAttribute("name", name);
			request.setAttribute("mobile", mobile);
			request.setAttribute("company", company);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			;
		} else {
			request.setAttribute("message", "�̹� �����ϴ� �̸��� Ȥ�� �̹� �����ϴ� ��ȭ��ȣ �Դϴ�!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	protected boolean errorGenerate(HttpServletRequest request, HttpServletResponse response, String valuableName,
			String message) throws ServletException, IOException {
		if (valuableName == null || valuableName.trim().length() == 0) {
			// �����޼��� �������� ����
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
		request.setCharacterEncoding("euc-kr");
		process(request, response);
	}
}