package kr.or.ddit.board.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name = "boardService")
	private IBoardService boardService;
	
	@RequestMapping(path = "boardManage", method = RequestMethod.GET)
	public String boardManageView(Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
		return "board/boardManage";
	}
	
	@RequestMapping(path = "boardManage", method = RequestMethod.POST)
	public String boardManage(@RequestParam(name="boardNo", defaultValue="1") int boardNo
							 ,String boardNm, String useYN, String userId, String btn) {
		BoardVO board = new BoardVO();
		board.setBoardNm(boardNm);
		board.setUseYN(useYN);
		
		if("insert".equals(btn)) {
			Map<String, Object> map = new HashMap<String, Object>();		
			map.put("userId", userId);
			map.put("board", board);
			
			int res = boardService.insertBoard(map);
			logger.debug("게시판 insert 결과: {}", res);	
			
		}else if("modify".equals(btn)) {
			board.setBoardNo(boardNo);
			
			Map<String, Object> map = new HashMap<String, Object>();		
			map.put("userId", userId);
			map.put("board", board);
			
			int res = boardService.updateBoard(map);
			logger.debug("게시판 update 결과: {}", res);
		}
		
		return "redirect:/boardManage";
	}
	
}
