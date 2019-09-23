package kr.or.ddit.post.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.Util.FileUtil;
import kr.or.ddit.Util.Model.FileInfo;
import kr.or.ddit.attachment.model.AttachmentVO;
import kr.or.ddit.attachment.repository.IAttachmentDao;
import kr.or.ddit.attachment.service.IAttachmentService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.common.Page;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.reply.service.IReplyService;

@Controller
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Resource(name = "boardService")
	private IBoardService boardService;	
	
	@Resource(name = "postService")
	private IPostService postService;
	
	@Resource(name = "attachmentService")
	private IAttachmentService attachmentService;
	
	@Resource(name = "replyService")
	private IReplyService replyService;
	
	@RequestMapping("postList")
	public String postListView(@RequestParam(defaultValue = "1") int boardNo
							  ,@RequestParam(defaultValue = "1") int page
							  ,Model model) {
		
		Page p = new Page(page);
		
		// 쿼리로 보낼 파라메터 설정
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardNo", boardNo);
		paramMap.put("startIdx", p.getStartIdx());
		paramMap.put("endIdx", p.getEndIdx());
		
		// 게시글 총 건수 조회
		int totCnt = postService.getPostPagingListTotCnt(paramMap);
		p.setPaginationSize(totCnt);
		
		model.addAttribute("boardList", boardService.getBoardList());
		model.addAttribute("board", boardService.getBoard(boardNo));
		model.addAttribute("postList", postService.getPostPagingList(paramMap));
		model.addAttribute("pageVo", p);
		model.addAttribute("boardNo", boardNo);
		model.addAttribute("page", page);
		
		return "post/postList";
	}
	
	@RequestMapping(path = "postEnroll", method = RequestMethod.GET)
	public String postEnrollView(@RequestParam(defaultValue = "0") int parentPostNo
								,@RequestParam(defaultValue = "1") int boardNo
								,Model model) {
		
		model.addAttribute("boardList", boardService.getBoardList());
		model.addAttribute("boardNo", boardNo);
		model.addAttribute("parentPostNo", parentPostNo);
		
		return "post/postEnroll";
	}
	
	@RequestMapping(path = "postEnroll", method = RequestMethod.POST)
	public String postEnroll(String userId
							,@RequestParam(defaultValue = "1") int boardNo
							,PostVO post
							,Model model
							,@RequestPart("file") MultipartFile[] files) {
		
		// 게시글 등록
		Map<String, Object> postParamMap = new HashMap<String, Object>();
		postParamMap.put("boardNo", boardNo);
		postParamMap.put("parentPostNo", post.getParentPostNo());
		postParamMap.put("userId", userId);
		postParamMap.put("postTitle", post.getPostTitle());
		postParamMap.put("postCont", post.getPostCont());
		
		int res = 0;
		try {
			res = postService.insertPost(postParamMap);
		} catch (Exception e) {
			e.printStackTrace();
			return "post/postEnroll";
		}
		logger.debug("게시글 등록 결과: {}", res);
		
		// 사용자가 파일을 업로드한 경우
		for(MultipartFile file: files) {
			FileInfo fileInfo = FileUtil.getFileInfo(file.getOriginalFilename());
			if("file".equals(file.getName())) {
				if(file.getSize()>0) {
					try {
						file.transferTo(fileInfo.getFile());

						Map<String, Object> fileParamMap = new HashMap<String, Object>();
						fileParamMap.put("postNo", postParamMap.get("postNo"));
						fileParamMap.put("fileUploadNm", fileInfo.getOrginalFileName()); // originalFileName
						fileParamMap.put("fileRealNm", fileInfo.getFile().getPath());	 // path + uuid
						
						int fileRes = attachmentService.insertAttachedFile(fileParamMap);
						logger.debug("첨부파일 등록 결과: {}", fileRes);
						
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		model.addAttribute("boardNo", boardNo);
		model.addAttribute("postNo", post.getPostNo());
		
		return "redirect:/postDetail?boardNo="+boardNo+"&postNo="+postParamMap.get("postNo");
		
	}
	
	@RequestMapping("postDetail")
	public String postDetail(@RequestParam(defaultValue = "1") int boardNo
							,@RequestParam(defaultValue = "1") int postNo
							,Model model) {
		
		model.addAttribute("boardList", boardService.getBoardList());
		model.addAttribute("post", postService.getPost(postNo));
		model.addAttribute("attachedFileList", attachmentService.getAttachedFile(postNo));
		model.addAttribute("replyList", replyService.getReply(postNo));
		model.addAttribute("boardNo", boardNo);
		
		return "post/postDetail";
	}
	
	@RequestMapping("postPicture")
	public void userPicture(@RequestParam(defaultValue = "1") int postNo
						   ,@RequestParam(defaultValue = "1") int fileNo
						   ,HttpServletResponse response) throws IOException {
		
		AttachmentVO vo = attachmentService.getFile(fileNo);
		
		ServletOutputStream sos = response.getOutputStream();
		
		File picture = new File(vo.getFileRealNm());
		FileInputStream fis = new FileInputStream(picture);
		
		byte[] buff = new byte[512];
		int len = 0;
		while((len = fis.read(buff, 0, 512)) != -1) {
			sos.write(buff, 0, len);
		}
		
		fis.close();
	}
	
	@RequestMapping("postDelete")
	public String postDelete(@RequestParam(defaultValue = "1") int boardNo
							,@RequestParam(defaultValue = "0") int postNo
							,@RequestParam(defaultValue = "1") int page
							,Model model) {
		
		Page p = new Page(page);
		
		postService.deletePost(postNo);
		
		// 쿼리로 보낼 파라메터 설정
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardNo", boardNo);
		paramMap.put("startIdx", p.getStartIdx());
		paramMap.put("endIdx", p.getEndIdx());
		
		int totCnt = postService.getPostPagingListTotCnt(paramMap);
		p.setPaginationSize(totCnt);

		model.addAttribute("boardList", boardService.getBoardList());
		model.addAttribute("postList", postService.getPostPagingList(paramMap));
		model.addAttribute("pageVo", p);
		model.addAttribute("page", page);
		
		return "post/postList";
	}
	
	@RequestMapping(path = "postModify", method = RequestMethod.GET)
	public String postModifyView(@RequestParam(defaultValue = "1") int boardNo
							,@RequestParam(defaultValue = "1") int postNo
							,Model model){
		
		model.addAttribute("boardList", boardService.getBoardList());
		model.addAttribute("boardNo", boardNo);
		model.addAttribute("post", postService.getPost(postNo));
		model.addAttribute("attachedFileList", attachmentService.getAttachedFile(postNo));
		
		return "post/postModify";
	}
	
	@RequestMapping(path = "postModify", method = RequestMethod.POST)
	public String postModify(Model model, String userId
							,@RequestParam(defaultValue = "1") int postNo
							,String postTitle
							,String postCont
							,@RequestParam(defaultValue = "1") int boardNo
							,@RequestPart("file") MultipartFile[] files){
		
				// 게시글 수정
				Map<String, Object> postParamMap = new HashMap<String, Object>();
				postParamMap.put("postTitle", postTitle);
				postParamMap.put("postCont", postCont);
				postParamMap.put("postNo", postNo);
				
				int res = 0;
				try {
					res = postService.updatePost(postParamMap);
				} catch (Exception e) {
					e.printStackTrace();
					return "post/postModify";
				}
				logger.debug("게시글 수정 결과: {}", res);
				
				// 사용자가 파일을 업로드한 경우
				for(MultipartFile file: files) {
					FileInfo fileInfo = FileUtil.getFileInfo(file.getOriginalFilename());
					if("file".equals(file.getName())) {
						if(file.getSize()>0) {
							try {
								file.transferTo(fileInfo.getFile());

								Map<String, Object> fileParamMap = new HashMap<String, Object>();
								fileParamMap.put("postNo", postParamMap.get("postNo"));
								fileParamMap.put("fileUploadNm", fileInfo.getOrginalFileName()); // originalFileName
								fileParamMap.put("fileRealNm", fileInfo.getFile().getPath());	 // path + uuid
								
								int fileRes = attachmentService.insertAttachedFile(fileParamMap);
								logger.debug("첨부파일 수정 결과: {}", fileRes);
								
							} catch (IllegalStateException | IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
				
				model.addAttribute("boardNo", boardNo);
				model.addAttribute("postNo", postNo);
		
		return "redirect:/postDetail?boardNo="+boardNo+"&postNo="+postNo;
	}
}
