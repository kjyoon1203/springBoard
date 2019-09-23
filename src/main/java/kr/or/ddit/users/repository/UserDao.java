package kr.or.ddit.users.repository;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.users.model.UsersVO;

@Repository
public class UserDao implements IUserDao{

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public UsersVO getUser( String userId) {
		return sqlSession.selectOne("users.getUser", userId);
	}

}
