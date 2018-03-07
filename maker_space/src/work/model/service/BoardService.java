/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.model.service;

import java.util.ArrayList;

import work.model.dao.BusinessDao;
import work.model.dao.HashTagDao;
import work.model.dao.MemberDao;
import work.model.dao.TipDao;
import work.model.dto.IdeaBoard;

/**
 * ## 기능 
 * 1. 글등록서비스
 * 2. 글삭제서비스
 * 3. 글수정서비스
 * 4. 글검색서비스(제목, 내용, 이름, 해시태그)
 * 5. 해시태그등록서비스 
 */
public class BoardService {

	private static BoardService instance = new BoardService();
	private BusinessDao businessDao;
	private HashTagDao hashTagDao;
	/**
	 * 기본생성자
	 */
	public BoardService() {
		businessDao = BusinessDao.getInstance();
		hashTagDao = HashTagDao.getInstance();
	}
	/**
	 * Singleton 패턴
	 * @return
	 */
	public static BoardService getInstance() {
		return instance;
	}
	
	/**
	 * 글등록 서비스
	 * @param email
	 * @param password
	 * @return
	 */
	public int businessWrite(IdeaBoard dto) {
		return businessDao.registerBoard(dto);
	}
	
	/**
	 * 글삭제 서비스
	 * @param boardIdxs
	 * @return
	 */
	public int businessDelete(int boardIdx) {
		return businessDao.removeBoard(boardIdx);
	}
	/**
	 * 글수정 서비스
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
	 * 글 검색서비스
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
	/**
	 * 해시태그 등록 서비스
	 * @param primaryKey
	 * @param hashTag
	 * @return
	 */
	public int businssRegisterHashTag(int primaryKey, String[] hashTag) {
		for(int index = 0 ; index < hashTag.length ; index++ ) {
			if(hashTagDao.insertHashTag(primaryKey, hashTag[index]) == 0) {
				return 0;
			}
		}
		return 1;
	}
}
