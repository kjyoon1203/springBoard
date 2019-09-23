package kr.or.ddit.post.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.post.model.PostVO;

@Repository
public class PostDao implements IPostDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<PostVO> getPostAllList( int boardNo) {
		return sqlSession.selectList("post.getPostAllList", boardNo);
	}

	@Override
	public String getPostingUser( int postNo) {
		return sqlSession.selectOne("post.getPostingUser", postNo);
	}

	@Override
	public List<Map> getPostPagingList( Map<String, Object> paramMap) {
		return sqlSession.selectList("post.getPostPagingList", paramMap);
	}

	@Override
	public int getPostTotalCnt( Map<String, Object> paramMap) {
		return sqlSession.selectOne("post.getPostTotalCnt", paramMap);
	}

	@Override
	public Map getPost( int postNo) {
		return sqlSession.selectOne("post.getPost", postNo);
	}

	@Override
	public int insertPost( Map<String, Object> postParamMap) {
		return sqlSession.insert("post.insertPost", postParamMap);
	}

	@Override
	public int deletePost( int postNo) {
		return sqlSession.update("post.deletePost", postNo);
	}

	@Override
	public int updatePost( Map<String, Object> postParamMap) {
		return sqlSession.delete("post.updatePost", postParamMap);
	}
	
}
