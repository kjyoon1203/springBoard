package kr.or.ddit.attachment.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.attachment.model.AttachmentVO;

public interface IAttachmentDao {
	/**
	* Method : getAttachedFile
	* 작성자 : PC-08
	* 변경이력 :
	* @param sqlSession
	* @param postNo
	* @return
	* Method 설명 : 해당 게시글의 첨부파일 조회
	 */
	List<AttachmentVO> getAttachedFile( int postNo);
	
	/**
	* Method : insertAttachedFile
	* 작성자 : PC-08
	* 변경이력 :
	* @param fileParamMap
	* @return
	* Method 설명 : 해당 게시글의 첨부파일 등록
	 */
	int insertAttachedFile( Map<String, Object> fileParamMap);
	
	/**
	* Method : deleteAttachedFile
	* 작성자 : PC-08
	* 변경이력 :
	* Method 설명 : 해당 게시글의 첨부파일 삭제
	 */
	int deleteAttachedFile( int fileNo);
	
	/**
	* Method : getFile
	* 작성자 : PC-08
	* 변경이력 :
	* @param fileNo
	* @return
	* Method 설명 : 첨부파일 조회
	 */
	AttachmentVO getFile( int fileNo);
}
