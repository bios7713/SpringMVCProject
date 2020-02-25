package Command.answerBoard;

import org.springframework.web.multipart.MultipartFile;

public class AnswerCommand {
	
	private String boardPass;
	private String BoardSubject;
	private String BoardContent;
	private Integer boardNum;
	private String boardName;
	private MultipartFile [] boardFile;
	private String ipAddr;
	
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

	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public MultipartFile[] getBoardFile() {
		return boardFile;
	}
	public void setBoardFile(MultipartFile[] boardFile) {
		this.boardFile = boardFile;
	}


}
