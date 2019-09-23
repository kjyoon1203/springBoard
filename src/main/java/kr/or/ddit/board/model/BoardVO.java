package kr.or.ddit.board.model;

import java.util.Date;

public class BoardVO {
	private int boardNo;
	private String boardNm;
	private String useYN;
	private Date boardRegiDate;
	
	public BoardVO() {
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardNm() {
		return boardNm;
	}
	public void setBoardNm(String boardNm) {
		this.boardNm = boardNm;
	}
	public String getUseYN() {
		return useYN;
	}
	public void setUseYN(String useYN) {
		this.useYN = useYN;
	}
	public Date getBoardRegiDate() {
		return boardRegiDate;
	}
	public void setBoardRegiDate(Date boardRegiDate) {
		this.boardRegiDate = boardRegiDate;
	}
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardNm=" + boardNm + ", useYN=" + useYN + ", boardRegiDate="
				+ boardRegiDate + "]";
	}	
}
