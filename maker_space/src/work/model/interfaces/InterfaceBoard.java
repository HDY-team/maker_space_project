/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.model.interfaces;

import java.util.ArrayList;

import work.model.dto.IdeaBoard;

/**
 * Board �������̽�
 * ��� ����
 * 1) �߰�
 * 2) ����
 * 3) ����
 * 4) ��ȸ 
 * - �̸�
 * - ����
 * - �ؽ��±� 
 */
public interface InterfaceBoard {

	/**
	 * 1. �߰�
	 * @param dto
	 * @return
	 */
	public int registerBoard(String category, IdeaBoard dto);
	/**
	 * 2. ����
	 * @param title
	 * @param content
	 * @param result
	 * @param files
	 * @return
	 */
	public int changeBoard(int boardIdx, String title, String content, String result, String files);
	/**
	 * 3. ����
	 * @param boardIdx
	 * @return
	 */
	public int removeBoard(int boardIdx);
	/**
	 * 4. ��ȸ
	 * @param name
	 * @return
	 */
	public ArrayList<IdeaBoard> findBoardTitle(int currentPage, int listSize, String title, String category);
	public ArrayList<IdeaBoard> findBoardName(int currentPage, int listSize, String title, String category);
	public ArrayList<IdeaBoard> findBoardContent(int currentPage, int listSize, String title, String category);
	public ArrayList<IdeaBoard> findBoardHashTag(String hashTag);
}
