/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import work.model.dao.BusinessDao;
import work.model.dao.HashTagDao;
import work.model.dao.MemberDao;
import work.model.dao.TipDao;
import work.model.dto.IdeaBoard;
import work.model.dto.TipIdeaBoard;

/**
 * ## ��� 1. �۵�ϼ��� 2. �ۻ������� 3. �ۼ������� 4. �۰˻�����(����, ����, �̸�, �ؽ��±�) 5. �ؽ��±׵�ϼ���
 */
public class BoardService {
	private static final int SEARCH_TITLE = 0;
	private static final int SEARCH_CONTENT = 1;
	private static final int SEARCH_NAME = 2;
	private final static int LIST_SIZE = 10;
	private final static int PAGE_SIZE = 5;
	private static BoardService instance = new BoardService();
	private BusinessDao businessDao;
	private HashTagDao hashTagDao;

	/**
	 * �⺻������
	 */
	public BoardService() {
		businessDao = BusinessDao.getInstance();
		hashTagDao = HashTagDao.getInstance();
	}

	/**
	 * Singleton ����
	 * 
	 * @return
	 */
	public static BoardService getInstance() {
		return instance;
	}

	/**
	 * �۵�� ����
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public int businessWrite(String category, IdeaBoard dto) {
		return businessDao.registerBoard(category, dto);
	}

	/**
	 * �ۻ��� ����
	 * 
	 * @param boardIdxs
	 * @return
	 */
	public int businessDelete(int boardIdx) {
		return businessDao.removeBoard(boardIdx);
	}

	/**
	 * �ۼ��� ����
	 * 
	 * @param boardIdx
	 * @param title
	 * @param content
	 * @param result
	 * @param files
	 * @return
	 */
	public int businessChange(int boardIdx, String title, String content, String result, String files) {
		return businessDao.changeBoard(boardIdx, title, content, result, files);
	}
	public Map<String, Object> businessFind(int currentPage, int searchMethod ,String searchContent, String category) {
		// 1. �ʱⰪ ���
		int totalCount=0;
		if(searchMethod == SEARCH_TITLE) {
			totalCount = businessDao.getSearchTitleCount(searchContent, category);
			System.out.println("SEARCH_TITLE totalcount:" + totalCount);
		} else if(searchMethod == SEARCH_CONTENT) {
			totalCount = businessDao.getSearchContentCount(searchContent, category);
			System.out.println("SEARCH_CONTENT totalcount:" + totalCount);
		} else if(searchMethod == SEARCH_NAME) {
			totalCount = businessDao.getSearchNameCount(searchContent, category);
			System.out.println("SEARCH_NAME totalcount:" + totalCount);
		} else {
			return null;
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
		System.out.println("beginPage : " + beginPage + ", endPage : " + endPage);
		System.out.println("prevPage = " + prevPage);
		System.out.println("nextPage = " + nextPage);
		System.out.println("endPage = " + endPage);
		// 4. ��� ����Ʈ ��������
		Map<String, Object> map = new HashMap<String, Object>();
		if(searchMethod == SEARCH_TITLE) {
			// 5. ����Ʈ ������ �ʿ� ����
			ArrayList<IdeaBoard> lists = businessDao.findBoardTitle(currentPage, LIST_SIZE, searchContent, category);
			map.put("lists", lists);
		} else if(searchMethod == SEARCH_CONTENT) {
			ArrayList<IdeaBoard> lists = businessDao.findBoardContent(currentPage, LIST_SIZE, searchContent, category);
			map.put("lists", lists);
		} else if(searchMethod == SEARCH_NAME) {
			ArrayList<IdeaBoard> lists = businessDao.findBoardName(currentPage, LIST_SIZE, searchContent, category);
			map.put("lists", lists);
		} else {
			ArrayList<IdeaBoard> lists =null;
			map.put("lists", lists);
		}
		// 6. ���� ������ ���� �ʿ� ����
		System.out.println("total: " + String.valueOf( totalCount-((currentPage-1)*10)));
		map.put("pageTotalCount", totalCount-((currentPage-1)*10));
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
	public ArrayList<IdeaBoard> businessFindByHashTag(String hashTag) {
		return businessDao.findBoardHashTag(hashTag);
	}
	/**
	 * �ؽ��±� ��� ����
	 * 
	 * @param primaryKey
	 * @param hashTag
	 * @return
	 */
	public int businssRegisterHashTag(int primaryKey, String[] hashTag) {
		if (hashTag != null)
			for (int index = 0; index < hashTag.length; index++) {
				if (hashTagDao.insertHashTag(primaryKey, hashTag[index]) == 0) {
					return 0;
				}
			}
		return 1;
	}

	public Map<String, Object> getBoards(int currentPage, String category) {
		// 1. �ʱⰪ ���
		int totalCount=0;
		if(category.equals("it")) {
			totalCount = businessDao.getTotalItCount();
		} else if(category.equals("market")) {
			totalCount = businessDao.getTotalMarketCount();
		} else if(category.equals("media")) {
			totalCount = businessDao.getTotalMediaCount();
		} else if(category.equals("etc")) {
			totalCount = businessDao.getTotalEtcCount();
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
		System.out.println("beginPage : " + beginPage + ", endPage : " + endPage);
		System.out.println("prevPage = " + prevPage);
		System.out.println("nextPage = " + nextPage);
		System.out.println("endPage = " + endPage);
		// 4. ��� ����Ʈ ��������
		ArrayList<IdeaBoard> lists = businessDao.getBoards(currentPage, LIST_SIZE, category);
		// 5. ����Ʈ ������ �ʿ� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lists", lists);
		// 6. ���� ������ ���� �ʿ� ����
		System.out.println("curPage:" + currentPage);

		System.out.println("pageTotalCount:" + String.valueOf(totalCount-((currentPage-1)*10)));
		
		map.put("pageTotalCount", totalCount-((currentPage-1)*10));
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

	public IdeaBoard getBoard(int businessBoardsIdx, String category) {
		return businessDao.getBoard(businessBoardsIdx, category);
	}

	public int updateHits(int businessBoardsIdx) {
		return businessDao.updateHits(businessBoardsIdx);
	}
	
	public int registerMySelect(String category, int boardIdx) {
		return businessDao.registerMySelect(category, boardIdx);
	}
	public Map<String, Object> getTipBoards(int currentPage, String category) {
		int totalCount = businessDao.getTotalTipCount();	
		int pageCount = (int) Math.ceil((double) totalCount / LIST_SIZE);
		int blockCount = (int) Math.ceil((double) pageCount / PAGE_SIZE);
		int currentBlock = (int) Math.ceil((double) currentPage / PAGE_SIZE);

		System.out.println("totalcount = "+ totalCount);
		System.out.println("pageCount = "+ pageCount);
		System.out.println("blockCount = "+ blockCount);
		System.out.println("currentBlock = "+ currentBlock);
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
		System.out.println("beginPage : " + beginPage + ", endPage : " + endPage);
		System.out.println("prevPage = "+ prevPage);
		System.out.println("nextPage = "+ nextPage);
		System.out.println("endPage = "+ endPage);
		// 4. ��� ����Ʈ ��������
		ArrayList<TipIdeaBoard> lists = businessDao.getTipBoards(currentPage, LIST_SIZE, category);
		// 5. ����Ʈ ������ �ʿ� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lists", lists);

		// 6. ���� ������ ���� �ʿ� ����
		map.put("pageTotalCount", totalCount-((currentPage-1)*10));
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
}
