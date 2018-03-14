package work.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import work.model.dao.MemberDao;
import work.model.dao.ProcessDao;
import work.model.dto.IdeaBoard;

public class ProcessService {
	private static final int SEARCH_TITLE = 0;
	private static final int SEARCH_CONTENT = 1;
	private static final int SEARCH_NAME = 2;
	private final static int LIST_SIZE = 10;
	private final static int PAGE_SIZE = 5;
	private ProcessDao processDao;
	private static ProcessService instance;

	/**
	 * �⺻������
	 */
	private ProcessService() {
		processDao = ProcessDao.getInstance();
	}

	/**
	 * Singleton ����
	 * 
	 * @return
	 */
	public static ProcessService getInstance() {
		if (instance == null) {
			instance = new ProcessService();
		}
		return instance;
	}

	public Map<String, Object> getProcessBoards(int currentPage, String category, String _email) {
		// 1. �ʱⰪ ���
		int totalCount = 0;
		if (category.equals("mySelected")) {
			totalCount = processDao.getTotalMySelectedCount(_email, 1);
		} else if (category.equals("mySelect")) {
			totalCount = processDao.getTotalMySelectCount(_email, 1);
		} else if (category.equals("selectComplete")){
			totalCount = processDao.getTotalSelectCompleteCount(_email, 3);
		}

		int pageCount = (int) Math.ceil((double) totalCount / LIST_SIZE);
		int blockCount = (int) Math.ceil((double) pageCount / PAGE_SIZE);
		int currentBlock = (int) Math.ceil((double) currentPage / PAGE_SIZE);

		System.out.println("totalcount = " + totalCount);
		System.out.println("pageCount = " + pageCount);
		System.out.println("blockCount = " + blockCount);
		System.out.println("currentBlock = " + currentBlock);

		// 2. �Ķ���� page �� ����
		if (currentPage < 1) {
			currentPage = 1;
			currentBlock = 1;
		} else if (currentPage > pageCount) {
			currentPage = pageCount;
			currentBlock = (int) Math.ceil((double) currentPage / PAGE_SIZE);
		}

		// 3. view���� ������ ����Ʈ�� ������ �ϱ����� ������ �� ���
		int beginPage = currentBlock == 0 ? 1 : (currentBlock - 1) * PAGE_SIZE + 1;
		int prevPage = (currentBlock > 1) ? (currentBlock - 1) * PAGE_SIZE : 0;
		int nextPage = (currentBlock < blockCount) ? currentBlock * PAGE_SIZE + 1 : 0;
		int endPage = (nextPage > 0) ? (beginPage - 1) + PAGE_SIZE : pageCount;
		// 4. ��� ����Ʈ ��������
		ArrayList<IdeaBoard> lists = processDao.getProcessBoards(currentPage, LIST_SIZE, category, _email);
		// 5. ����Ʈ ������ �ʿ� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lists", lists);
		// 6. ���� ������ ���� �ʿ� ����
		System.out.println("pageTotalCount:" + String.valueOf(totalCount - ((currentPage - 1) * 10)));
		map.put("pageTotalCount", totalCount - ((currentPage - 1) * 10));
		map.put("totalCount", totalCount);
		map.put("pageSize", PAGE_SIZE);
		map.put("listSize", LIST_SIZE);
		map.put("pageCount", pageCount);
		map.put("blockCount", blockCount);
		map.put("currentPage", currentPage);
		map.put("beginPage", beginPage);
		map.put("endPage", endPage);
		map.put("prevPage", prevPage);
		map.put("nextPage", nextPage);
		map.put("nextPage", nextPage);
		return map;
	}

	public IdeaBoard getProcessBoard(int businessBoardsIdx, String category) {
		return processDao.getProcessBoard(businessBoardsIdx, category);
	}
	public int mySelectedCancel(int businessBoardsIdx) {
		return processDao.mySelectedCancel(businessBoardsIdx);
	}
	public int mySelectCancel(int businessBoardsIdx) {
		
		return processDao.mySelectCancel(businessBoardsIdx);
	}
	public int mySelectedComplete(int businessBoardsIdx) {
		return processDao.mySelectedComplete(businessBoardsIdx);
	}
	public int complete(int businessBoardsIdx, String email) {
		return processDao.complete(businessBoardsIdx, email);
	}
}
