package kr.or.ddit.board.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVO;

@Repository
public class BoardDao implements IBoardDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<BoardVO> getBoardList() {
		return sqlSession.selectList("board.getBoardList");
	}

	@Override
	public int insertBoard( Map<String, Object> map) {
		return sqlSession.insert("board.insertBoard", map);
	}

	@Override
	public int updateBoard( Map<String, Object> map) {
		return sqlSession.update("board.updateBoard", map);
	}

	@Override
	public BoardVO getBoard( int boardNo) {
		return sqlSession.selectOne("board.getBoard", boardNo);
	}
}
