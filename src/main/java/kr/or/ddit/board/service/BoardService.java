package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.repository.IBoardDao;

@Service
public class BoardService implements IBoardService {
	
	@Resource(name = "boardDao")
	private IBoardDao boardDao;
	
	public BoardService() {
	}
	
	public BoardService(IBoardDao boardDao) {
		this.boardDao = boardDao;
	}
		
	@Override
	public List<BoardVO> getBoardList() {
		return boardDao.getBoardList();
	}

	@Override
	public int insertBoard(Map<String, Object> map) {
		return boardDao.insertBoard(map);
	}

	@Override
	public int updateBoard(Map<String, Object> map) {
		return boardDao.updateBoard(map);
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		return boardDao.getBoard(boardNo);
	}
}
