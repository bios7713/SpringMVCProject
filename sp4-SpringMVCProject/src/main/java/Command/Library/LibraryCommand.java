package Command.Library;

import org.springframework.web.multipart.MultipartFile;

public class LibraryCommand {
	private String boardPass;
	private String BoardSubject;
	private String BoardContent;
	private Integer boardNum;
	private String ipAddr;
	private String boardName;
	private MultipartFile [] report;
	
	

	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardPass() {
		return boardPass;
	}
	public void setBoardPass(String boardPass) {
		this.boardPass = boardPass;
	}
	public String getBoardSubject() {
		return BoardSubject;
	}
	public void setBoardSubject(String boardSubject) {
		BoardSubject = boardSubject;
	}
	public String getBoardContent() {
		return BoardContent;
	}
	public void setBoardContent(String boardContent) {
		BoardContent = boardContent;
	}
	public Integer getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(Integer boardNum) {
		this.boardNum = boardNum;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public MultipartFile [] getReport() {
		return report;
	}
	public void setReport(MultipartFile [] report) {
		this.report = report;
	}
	

	

}
