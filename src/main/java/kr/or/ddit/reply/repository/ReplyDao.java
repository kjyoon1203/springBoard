package kr.or.ddit.reply.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDao implements IReplyDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<Map> getReply( int postNo) {
		return sqlSession.selectList("reply.getReply", postNo);
	}

	@Override
	public int insertReply( Map paramMap) {
		return sqlSession.insert("reply.insertReply", paramMap);
	}

	@Override
	public int deleteReply( int replyNo) {
		return sqlSession.update("reply.deleteReply", replyNo);
	}

}
