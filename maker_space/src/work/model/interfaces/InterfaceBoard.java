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
 * Board 인터페이스
 * 기능 명세서
 * 1) 추가
 * 2) 수정
 * 3) 삭제
 * 4) 조회 
 * - 이름
 * - 제목
 * - 해시태그 
 */
public interface InterfaceBoard {

	/**
	 * 1. 추가
	 * @param dto
	 * @return
	 */
	public int registerBoard(IdeaBoard dto);
	/**
	 * 2. 수정
	 * @param title
	 * @param content
	 * @param result
	 * @param files
	 * @return
	 */
	public int changeBoard(int boardIdx, String title, String content, String result, String files);
	/**
	 * 3. 삭제
	 * @param boardIdx
	 * @return
	 */
	public int removeBoard(int boardIdx);
	/**
	 * 4. 조회
	 * @param name
	 * @return
	 */
	public ArrayList<IdeaBoard> findBoardName(String name);
	public ArrayList<IdeaBoard> findBoardTitle(String title);
	public ArrayList<IdeaBoard> findBoardContent(String content);
	public ArrayList<IdeaBoard> findBoardHashTag(String hashTag);
}
