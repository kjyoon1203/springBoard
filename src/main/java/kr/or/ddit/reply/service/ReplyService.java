package kr.or.ddit.reply.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.reply.repository.IReplyDao;

@Service
public class ReplyService implements IReplyService {
	
	@Resource(name = "replyDao")
	private IReplyDao replyDao;
	
	public ReplyService() {
	}
	
	public ReplyService(IReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	
	@Override
	public List<Map> getReply(int postNo) {
		return replyDao.getReply(postNo);
	}

	@Override
	public int insertReply(Map paramMap) {
		return replyDao.insertReply(paramMap);
	}

	@Override
	public int deleteReply(int replyNo) {
		return replyDao.deleteReply(replyNo);
	}

}
