package kr.or.ddit.common;

public class Page {
	private int page = 1;	//현재 페이지
	
	private int pageSize     = 10; // 페이지크기
	private int pagePerCount = 10; // 한 페이지 게시글 수
	private int startIdx     = 0;  
	private int endIdx       = 0;
	private int paginationSize = 0;
	
	public Page() {
	}
	
	public Page(int page) {
		this.page = page;
		setStartIdx();
		setEndIdx();
	}
	
	public Page(int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
		setStartIdx();
		setEndIdx();
	}
	
	public int getPaginationSize() {
		return paginationSize;
	}

	public void setPaginationSize(int totCnt) {
		this.paginationSize = (int)Math.ceil((double)totCnt/this.pageSize);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPagePerCount() {
		return pagePerCount;
	}

	public void setPagePerCount(int pagePerCount) {
		this.pagePerCount = pagePerCount;
	}

	public int getStartIdx() {
		return startIdx;
	}

	public void setStartIdx() {
		this.startIdx = (this.page-1)*this.pagePerCount + 1;
	}

	public int getEndIdx() {
		return endIdx;
	}

	public void setEndIdx() {
		this.endIdx = this.page*this.pagePerCount;
	}
	

	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	

}
