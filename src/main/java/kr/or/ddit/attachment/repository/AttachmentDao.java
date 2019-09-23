package kr.or.ddit.attachment.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.attachment.model.AttachmentVO;

@Repository
public class AttachmentDao implements IAttachmentDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<AttachmentVO> getAttachedFile(int postNo) {
		return sqlSession.selectList("attachment.getAttachedFile", postNo);
	}

	@Override
	public int insertAttachedFile(Map<String, Object> fileParamMap) {
		return sqlSession.insert("attachment.insertAttachedFile", fileParamMap);
	}

	@Override
	public int deleteAttachedFile(int fileNo) {
		return sqlSession.delete("attachment.deleteAttachedFile", fileNo);
	}

	@Override
	public AttachmentVO getFile(int fileNo) {
		return sqlSession.selectOne("attachment.getFile", fileNo);
	}

}
