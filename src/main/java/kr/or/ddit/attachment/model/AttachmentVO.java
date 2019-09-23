package kr.or.ddit.attachment.model;

public class AttachmentVO {
	private int fileNo;
	private String fileUploadNm;
	private String fileRealNm;
	
	public AttachmentVO() {
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileUploadNm() {
		return fileUploadNm;
	}
	public void setFileUploadNm(String fileUploadNm) {
		this.fileUploadNm = fileUploadNm;
	}
	public String getFileRealNm() {
		return fileRealNm;
	}
	public void setFileRealNm(String fileRealNm) {
		this.fileRealNm = fileRealNm;
	}
	@Override
	public String toString() {
		return "AttachmentVO [filetNo=" + fileNo + ", fileUploadNm=" + fileUploadNm + ", fileRealNm=" + fileRealNm
				+ "]";
	}
}
