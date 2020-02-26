package Service.Answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Command.answerBoard.AnswerCommand;
import Controller.Encrypt;
import Model.DAO.AnswerBoardDAO;
import Model.DTO.AnswerBoardDTO;
import Model.DTO.AuthInfo;

@Service
public class AnswerReplyService {
	@Autowired
	AnswerBoardDAO answerBoardDAO;

	public void replysvc(AnswerCommand answerCommand ,HttpSession session ,HttpServletRequest request ) {
		AnswerBoardDTO dto = new AnswerBoardDTO();
	
		dto.setBoardContent(answerCommand.getBoardContent());
		dto.setBoardName(answerCommand.getBoardName());
		dto.setBoardNum(answerCommand.getBoardNum());
		dto.setBoardPass(Encrypt.getEncryption(answerCommand.getBoardPass()));
		dto.setBoardReRef(answerCommand.getBoardReRef());
		dto.setBoardReLev(answerCommand.getBoardReLev());
		dto.setBoardReSeq(answerCommand.getBoardReSeq());
		dto.setBoardSubject(answerCommand.getBoardSubject());
		dto.setIpAddr(request.getRemoteAddr());
	    AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		dto.setUserId(authInfo.getId());
		answerBoardDAO.insertReply(dto);
		

		
		
		
	}
	
}
