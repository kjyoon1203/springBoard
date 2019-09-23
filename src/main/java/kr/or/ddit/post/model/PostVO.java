package kr.or.ddit.post.model;

import java.util.Date;

public class PostVO {
	private int postNo;
	private int parentPostNo;
	private String postTitle;
	private String postCont;
	private Date postRegiDate;
	private String deleteYN;
	
	public PostVO() {
	}
	
	public int getParentPostNo() {
		return parentPostNo;
	}

	public void setParentPostNo(int parentPostNo) {
		this.parentPostNo = parentPostNo;
	}

	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostCont() {
		return postCont;
	}
	public void setPostCont(String postCont) {
		this.postCont = postCont;
	}
	public Date getPostRegiDate() {
		return postRegiDate;
	}
	public void setPostRegiDate(Date postRegiDate) {
		this.postRegiDate = postRegiDate;
	}
	public String getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}
	@Override
	public String toString() {
		return "PostVO [postNo=" + postNo + ", postTitle=" + postTitle + ", postCont=" + postCont + ", postRegiDate="
				+ postRegiDate + ", deleteYN=" + deleteYN + "]";
	}
}
