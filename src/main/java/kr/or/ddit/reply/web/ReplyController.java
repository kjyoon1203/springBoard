package kr.or.ddit.reply.web;

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

import kr.or.ddit.reply.service.IReplyService;

@Controller
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Resource(name = "replyService")
	private IReplyService replyService;
	
	@RequestMapping(path = "replySave", method = RequestMethod.POST)
	public String replySave(@RequestParam(defaultValue = "1") int boardNo
						   ,@RequestParam(defaultValue = "1") int postNo
						   ,String userId
						   ,String replyCont) {
		
		// 쿼리로 보낼 파라미터 설정
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("postNo", postNo);
		paramMap.put("userId", userId);
		paramMap.put("replyCont", replyCont);
		
		int res = replyService.insertReply(paramMap);
		logger.debug("댓글 등록 결과: {}", res);
		
		return "redirect:/postDetail?boardNo=" + boardNo + "&postNo=" + postNo;
	}
	
	@RequestMapping("replyDelete")
	public String replyDelete(@RequestParam(defaultValue = "1") int boardNo
						   	 ,@RequestParam(defaultValue = "1") int postNo
						   	 ,@RequestParam(defaultValue = "1") int replyNo
						   	 ,Model model) {
		
		int res = replyService.deleteReply(replyNo);
		logger.debug("댓글 삭제 결과: {}", res);
		
		model.addAttribute(replyService.getReply(postNo));
		
		return "redirect:/postDetail?boardNo=" + boardNo + "&postNo=" + postNo;
	}
}
