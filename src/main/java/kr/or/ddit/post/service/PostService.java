package kr.or.ddit.post.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.repository.IPostDao;

@Service
public class PostService implements IPostService {
	
	@Resource(name = "postDao")
	private IPostDao postDao;

	public PostService() {
	}
	
	public PostService(IPostDao postDao) {
		this.postDao = postDao;
	}
	
	@Override
	public List<PostVO> getPostAllList(int boardNo) {
		return postDao.getPostAllList(boardNo);
	}

	@Override
	public String getPostingUser(int postNo) {
		return postDao.getPostingUser(postNo);
	}

	@Override
	public List<?> getPostPagingList(Map<String, Object> paramMap){
		return postDao.getPostPagingList(paramMap);
	}
	
	public int getPostPagingListTotCnt(Map<String, Object> paramMap) {
		return postDao.getPostTotalCnt(paramMap); 
	}

	@Override
	public Map getPost(int postNo) {
		return postDao.getPost(postNo);
	}

	@Override
	public int insertPost(Map<String, Object> postParamMap) {
		return postDao.insertPost(postParamMap);
	}

	@Override
	public int deletePost(int postNo) {
		return postDao.deletePost(postNo);
	}

	@Override
	public int updatePost(Map<String, Object> postParamMap) {
		return postDao.updatePost(postParamMap);
	}

}
