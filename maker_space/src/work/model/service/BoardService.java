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
 * ## 기능 1. 글등록서비스 2. 글삭제서비스 3. 글수정서비스 4. 글검색서비스(제목, 내용, 이름, 해시태그) 5. 해시태그등록서비스
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
	private TipDao tipDao;
	/**
	 * 기본생성자
	 */
	public BoardService() {
		businessDao = BusinessDao.getInstance();
		hashTagDao = HashTagDao.getInstance();
		tipDao = TipDao.getInstance();
	}

	/**
	 * Singleton 패턴
	 * 
	 * @return
	 */
	public static BoardService getInstance() {
		return instance;
	}

	/**
	 * 글등록 서비스
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public int businessWrite(String category, IdeaBoard dto) {
		return businessDao.registerBoard(category, dto);
	}
	public int tipWrite(String category, TipIdeaBoard dto) {
		return tipDao.registerBoard(category, dto);
	}
	public int myBoardWrite(String category, int businessBoardsIdx) {
		return businessDao.registerMyBoard(category, businessBoardsIdx);
	}
	/**
	 * 글삭제 서비스
	 * 
	 * @param boardIdxs
	 * @return
	 */
	public int businessDelete(int boardIdx) {
		return businessDao.removeBoard(boardIdx);
	}
	public int tipDelete(int boardIdx) {
		return businessDao.removeTipBoard(boardIdx);
	}
	/**
	 * 글수정 서비스
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
	public int tipChange(int boardIdx, String title, String content, String result, String files) {
		return businessDao.changeTipBoard(boardIdx, title, content, result, files);
	}
	public Map<String, Object> businessFind(int currentPage, int searchMethod ,String searchContent, String category) {
		// 1. 초기값 계산
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
		System.out.println("beginPage : " + beginPage + ", endPage : " + endPage);
		System.out.println("prevPage = " + prevPage);
		System.out.println("nextPage = " + nextPage);
		System.out.println("endPage = " + endPage);
		// 4. 멤버 리스트 가져오기
		Map<String, Object> map = new HashMap<String, Object>();
		if(searchMethod == SEARCH_TITLE) {
			// 5. 리스트 정보를 맵에 저장
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
		// 6. 각종 페이지 정보 맵에 저장
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
	/**
	 * Tip find
	 * @param currentPage
	 * @param searchMethod
	 * @param searchContent
	 * @param category
	 * @return
	 */
	public Map<String, Object> tipFind(int currentPage, int searchMethod ,String searchContent, String category) {
		// 1. 초기값 계산
		int totalCount=0;
		if(searchMethod == SEARCH_TITLE) {
			totalCount = businessDao.getTipSearchTitleCount(searchContent, category);
			System.out.println("SEARCH_TITLE totalcount:" + totalCount);
		} else if(searchMethod == SEARCH_CONTENT) {
			totalCount = businessDao.getTipSearchContentCount(searchContent, category);
			System.out.println("SEARCH_CONTENT totalcount:" + totalCount);
		} else if(searchMethod == SEARCH_NAME) {
			totalCount = businessDao.getTipSearchNameCount(searchContent, category);
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
		System.out.println("beginPage : " + beginPage + ", endPage : " + endPage);
		System.out.println("prevPage = " + prevPage);
		System.out.println("nextPage = " + nextPage);
		System.out.println("endPage = " + endPage);
		// 4. 멤버 리스트 가져오기
		Map<String, Object> map = new HashMap<String, Object>();
		if(searchMethod == SEARCH_TITLE) {
			// 5. 리스트 정보를 맵에 저장
			ArrayList<TipIdeaBoard> lists = businessDao.findTipBoardTitle(currentPage, LIST_SIZE, searchContent, category);
			map.put("lists", lists);
		} else if(searchMethod == SEARCH_CONTENT) {
			ArrayList<TipIdeaBoard> lists = businessDao.findTipBoardContent(currentPage, LIST_SIZE, searchContent, category);
			map.put("lists", lists);
		} else if(searchMethod == SEARCH_NAME) {
			ArrayList<TipIdeaBoard> lists = businessDao.findTipBoardName(currentPage, LIST_SIZE, searchContent, category);
			map.put("lists", lists);
		} else {
			ArrayList<TipIdeaBoard> lists =null;
			map.put("lists", lists);
		}
		// 6. 각종 페이지 정보 맵에 저장
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
	 * 해시태그 등록 서비스
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

	public Map<String, Object> getBoards(int currentPage, String category, String _email) {
		// 1. 초기값 계산
		int totalCount=0;
		if(category.equals("it")) {
			totalCount = businessDao.getTotalItCount();
		} else if(category.equals("market")) {
			totalCount = businessDao.getTotalMarketCount();
		} else if(category.equals("media")) {
			totalCount = businessDao.getTotalMediaCount();
		} else if(category.equals("etc")) {
			totalCount = businessDao.getTotalEtcCount();
		} else if(category.equals("myIdea")) { // 내 아이디어 게시판 페이지수 정하기 위한 카운트
			totalCount = businessDao.getTotalMyCount(_email);
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
		System.out.println("beginPage : " + beginPage + ", endPage : " + endPage);
		System.out.println("prevPage = " + prevPage);
		System.out.println("nextPage = " + nextPage);
		System.out.println("endPage = " + endPage);
		// 4. 멤버 리스트 가져오기
		ArrayList<IdeaBoard> lists = businessDao.getBoards(currentPage, LIST_SIZE, category, _email);
		// 5. 리스트 정보를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lists", lists);
		// 6. 각종 페이지 정보 맵에 저장
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
	public IdeaBoard getMyBoard(int businessBoardsIdx, String category, String email) {
		return businessDao.getMyBoard(businessBoardsIdx, category, email);
	}
	public int updateHits(int businessBoardsIdx) {
		return businessDao.updateHits(businessBoardsIdx);
	}
	public int updateTipHits(int tipBoardsIndex) {
		return businessDao.updateTipHits(tipBoardsIndex);
	}
	public int updateScrapHits(int tipBoardsIndex) {
		return businessDao.updateScrapHits(tipBoardsIndex);
	}
	/**
	 * 
	 * @param currentPage
	 * @param category
	 * @param email
	 * @return
	 */
	public Map<String, Object> getTipBoards(int currentPage, String category, String _email) {
		int totalCount = 0;
		if(category.equals("tips")) {
			totalCount = businessDao.getTotalTipCount();
		} else if(category.equals("myTips")) {
			totalCount = businessDao.getTotalMyTipCount(_email);	
		} else if(category.equals("scrap")) { // 내 아이디어 게시판 페이지수 정하기 위한 카운트
			totalCount = businessDao.getTotalMyScrapsCount(_email);
		} else {
			return null;
		}
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

		// 3. view에서 페이지 리스트를 렌더링 하기위한 데이터 값 계산
		int beginPage = currentBlock == 0 ? 1 : (currentBlock - 1) * PAGE_SIZE + 1;
		int prevPage = (currentBlock > 1) ? (currentBlock - 1) * PAGE_SIZE : 0;
		int nextPage = (currentBlock < blockCount) ? currentBlock * PAGE_SIZE + 1 : 0;
		int endPage = (nextPage > 0) ? (beginPage - 1) + PAGE_SIZE : pageCount;
		System.out.println("beginPage : " + beginPage + ", endPage : " + endPage);
		System.out.println("prevPage = "+ prevPage);
		System.out.println("nextPage = "+ nextPage);
		System.out.println("endPage = "+ endPage);
		// 4. 멤버 리스트 가져오기
		ArrayList<TipIdeaBoard> lists = businessDao.getTipBoards(currentPage, LIST_SIZE, category, _email);
		
		for(int i = 0 ; i < lists.size() ; i++) {
			System.out.println(lists.get(i));
		}
		// 5. 리스트 정보를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lists", lists);
		map.put("pageTotalCount", totalCount-((currentPage-1)*10));
		// 6. 각종 페이지 정보 맵에 저장
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
	/**
	 * 
	 * @param TipBoardsIndex
	 * @return
	 */
	public TipIdeaBoard getTipBoard(int TipBoardsIndex) {
		return businessDao.getTipBoard(TipBoardsIndex);
	}
	public int registerMySelect(String category, int boardIdx, String email) {
		return businessDao.registerMySelect(category, boardIdx, email);
	}
	public int registerMyScraps(String category ,int tipBoardsIndex, String email) {
		return businessDao.registerMyScraps(category, tipBoardsIndex, email);
	}
	
}
