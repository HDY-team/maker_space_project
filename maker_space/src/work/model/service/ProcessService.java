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
	 * 기본생성자
	 */
	private ProcessService() {
		processDao = ProcessDao.getInstance();
	}

	/**
	 * Singleton 패턴
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
		// 1. 초기값 계산
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

		// 2. 파라미터 page 값 검증
		if (currentPage < 1) {
			currentPage = 1;
			currentBlock = 1;
		} else if (currentPage > pageCount) {
			currentPage = pageCount;
			currentBlock = (int) Math.ceil((double) currentPage / PAGE_SIZE);
		}

		// 3. view에서 페이지 리스트를 렌더링 하기위한 데이터 값 계산
		int beginPage = currentBlock == 0 ? 1 : (currentBlock - 1) * PAGE_SIZE + 1;
		int prevPage = (currentBlock > 1) ? (currentBlock - 1) * PAGE_SIZE : 0;
		int nextPage = (currentBlock < blockCount) ? currentBlock * PAGE_SIZE + 1 : 0;
		int endPage = (nextPage > 0) ? (beginPage - 1) + PAGE_SIZE : pageCount;
		// 4. 멤버 리스트 가져오기
		ArrayList<IdeaBoard> lists = processDao.getProcessBoards(currentPage, LIST_SIZE, category, _email);
		// 5. 리스트 정보를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lists", lists);
		// 6. 각종 페이지 정보 맵에 저장
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
