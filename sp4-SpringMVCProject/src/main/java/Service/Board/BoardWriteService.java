package Service.Board;

import java.text.SimpleDateFormat;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Command.Board.BoardCommand;
import Controller.Encrypt;
import Model.DAO.BoardDAO;
import Model.DTO.AuthInfo;
import Model.DTO.BoardDTO;

@Service
public class BoardWriteService {
	@Autowired
	private BoardDAO boardDAO;
	
	public void insertBoard(BoardCommand boardCommand ,
			              HttpServletRequest request , HttpSession session) {		
		BoardDTO boardDTO = new BoardDTO();
		
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		
		boardDTO.setUserId(authInfo.getId());
		boardDTO.setBoardName(boardCommand.getBoardName());
		boardDTO.setBoardPass(Encrypt.getEncryption(boardCommand.getBoardPass()));
		boardDTO.setBoardSubject(boardCommand.getBoardSubject());
		boardDTO.setBoardContent(boardCommand.getBoardContent());
		boardDTO.setIpAddr(request.getRemoteAddr());
			
		 boardDAO.insertBoard(boardDTO);
		
		
	}
	
	

}
