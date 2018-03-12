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

	private final static int LIST_SIZE = 10;
	private final static int PAGE_SIZE = 5;
	private static BoardService instance = new BoardService();
	private BusinessDao businessDao;
	private HashTagDao hashTagDao;
	private MemberDao memberDao;

	/**
	 * �⺻������
	 */
	public BoardService() {
		businessDao = BusinessDao.getInstance();
		hashTagDao = HashTagDao.getInstance();
		memberDao = MemberDao.getInstance();
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
	public int myBoardWrite(String category, int businessBoardsIdx) {
		return businessDao.registerMyBoard(category, businessBoardsIdx);
	}
	public int businessWrite(String category, IdeaBoard dto) {
		return businessDao.registerBoard(category, dto);
	}
	public int tipWrite(String category, TipIdeaBoard dto) {
		return businessDao.registerTipBoard(category, dto);
	}
	public int myTipWrite(String category, int tipIdx) {
		return businessDao.registerMyBoard(category, tipIdx);
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

	/**
	 * �� �˻�����
	 * 
	 * @param name
	 * @return
	 */
	public ArrayList<IdeaBoard> businessFindByName(String name) {
		return businessDao.findBoardName(name);
	}

	public ArrayList<IdeaBoard> businessFindByTitle(String title) {
		return businessDao.findBoardTitle(title);
	}

	public ArrayList<IdeaBoard> businessFindByContent(String content) {
		return businessDao.findBoardContent(content);
	}

	public ArrayList<IdeaBoard> businessFindByHashTag(String hashTag) {
		return businessDao.findBoardHashTag(hashTag);
	}
	public int registerMySelect(String category, int boardIdx) {
		return businessDao.registerMySelect(category, boardIdx);
	}

	/**
	 * �ؽ��±� ��� ����
	 * 
	 * @param primaryKey
	 * @param hashTag
	 * @return
	 */
	public int businssRegisterHashTag(int primaryKey, String[] hashTag) {
		if(hashTag!=null)
		for (int index = 0; index < hashTag.length; index++) {
			if (hashTagDao.insertHashTag(primaryKey, hashTag[index]) == 0) {
				return 0;
			}
		}
		return 1;
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
	public Map<String, Object> getBoards(int currentPage, String category) {
		// 1. �ʱⰪ ���
		int totalCount = businessDao.getTotalCount();	
		int pageCount = (int) Math.ceil((double) totalCount / LIST_SIZE);
		int blockCount = (int) Math.ceil((double) pageCount / PAGE_SIZE);
		int currentBlock = (int) Math.ceil((double) currentPage / PAGE_SIZE);

		System.out.println("totalcount = "+ totalCount);
		System.out.println("pageCount = "+ pageCount);
		System.out.println("blockCount = "+ blockCount);
		System.out.println("currentBlock = "+ currentBlock);
		
		
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
		System.out.println("prevPage = "+ prevPage);
		System.out.println("nextPage = "+ nextPage);
		System.out.println("endPage = "+ endPage);
		// 4. ��� ����Ʈ ��������
		ArrayList<IdeaBoard> lists = businessDao.getBoards(currentPage, LIST_SIZE, category);
		// 5. ����Ʈ ������ �ʿ� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lists", lists);

		// 6. ���� ������ ���� �ʿ� ����
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
	public IdeaBoard getBoard(int businessBoardsIndex) {
		return businessDao.getBoard(businessBoardsIndex);
	}
	public TipIdeaBoard getTipBoard(int TipBoardsIndex) {
		return businessDao.getTipBoard(TipBoardsIndex);
	}
	
}
