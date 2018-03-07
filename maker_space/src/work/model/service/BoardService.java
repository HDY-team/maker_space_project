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
 * ## ��� 
 * 1. �۵�ϼ���
 * 2. �ۻ�������
 * 3. �ۼ�������
 * 4. �۰˻�����(����, ����, �̸�, �ؽ��±�)
 * 5. �ؽ��±׵�ϼ��� 
 */
public class BoardService {

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
	 * @return
	 */
	public static BoardService getInstance() {
		return instance;
	}
	
	/**
	 * �۵�� ����
	 * @param email
	 * @param password
	 * @return
	 */
	public int businessWrite(IdeaBoard dto) {
		return businessDao.registerBoard(dto);
	}
	
	/**
	 * �ۻ��� ����
	 * @param boardIdxs
	 * @return
	 */
	public int businessDelete(int boardIdx) {
		return businessDao.removeBoard(boardIdx);
	}
	/**
	 * �ۼ��� ����
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
	 * �ؽ��±� ��� ����
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
