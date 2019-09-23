package kr.or.ddit.attachment.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import kr.or.ddit.attachment.service.IAttachmentService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.service.IPostService;

@Controller
public class AttachmentController {

	@Resource(name = "attachmentService")
	private IAttachmentService attachmentService;
	
	@Resource(name = "postService")
	private IPostService postService;
	
	@Resource(name = "boardService")
	private IBoardService boardService;
	
	
}
