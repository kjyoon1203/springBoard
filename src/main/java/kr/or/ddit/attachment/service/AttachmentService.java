package kr.or.ddit.attachment.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.attachment.model.AttachmentVO;
import kr.or.ddit.attachment.repository.IAttachmentDao;

@Service
public class AttachmentService implements IAttachmentService {
	
	@Resource(name = "attachmentDao")
	private IAttachmentDao attachmentDao;

	public AttachmentService() {
	}
	
	public AttachmentService(IAttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}
	
	@Override
	public List<AttachmentVO> getAttachedFile(int postNo) {
		return attachmentDao.getAttachedFile(postNo);
	}

	@Override
	public int insertAttachedFile(Map<String, Object> fileParamMap) {
		return attachmentDao.insertAttachedFile(fileParamMap);
	}

	@Override
	public int deleteAttachedFile(int fileNo) {
		return attachmentDao.deleteAttachedFile(fileNo);
	}

	@Override
	public AttachmentVO getFile(int fileNo) {
		return attachmentDao.getFile(fileNo);
	}

}
