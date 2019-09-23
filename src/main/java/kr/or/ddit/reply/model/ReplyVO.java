package kr.or.ddit.reply.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReplyVO {
	private int replyNo;
	private String replyCont;
	private Date replyRegiDate;
	private String deleteYN;
	
	public ReplyVO() {
	}
		
	public String getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public String getReplyCont() {
		return replyCont;
	}
	public void setReplyCont(String replyCont) {
		this.replyCont = replyCont;
	}
	public String getReplyRegiDate() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		return sdf.format(replyRegiDate);
	}
	public void setReplyRegiDate(Date replyRegiDate) {
		this.replyRegiDate = replyRegiDate;
	}

	@Override
	public String toString() {
		return "ReplyVO [replyNo=" + replyNo + ", replyCont=" + replyCont
				+ ", replyRegiDate=" + replyRegiDate + ", deleteYN=" + deleteYN + "]";
	}
	
}
