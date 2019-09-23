package kr.or.ddit.attachment.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.attachment.service.IAttachmentService;
import kr.or.ddit.post.service.IPostService;

@Controller
public class AttachmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(AttachmentController.class);
	
	@Resource(name = "attachmentService")
	private IAttachmentService attachmentService;
	
	@Resource(name = "postService")
	private IPostService postService;
	
	@RequestMapping("fileDelete")
	public String fileDelete(@RequestParam(defaultValue = "1") int boardNo
							,@RequestParam(defaultValue = "1") int postNo
							,@RequestParam(defaultValue = "1") int fileNo
							,Model model) {
		
		int res = attachmentService.deleteAttachedFile(fileNo);
		logger.debug("첨부파일 삭제 결과: {}", res);
		
		model.addAttribute("boardNo", boardNo);
		model.addAttribute("post", postService.getPost(postNo));
		model.addAttribute("attachedFileList", attachmentService.getAttachedFile(postNo));
		
		return "redirect:/postDetail?boardNo="+boardNo+"&postNo="+postNo;
	}
	
}
