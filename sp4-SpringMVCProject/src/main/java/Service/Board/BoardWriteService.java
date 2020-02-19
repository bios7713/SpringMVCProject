package Service.Board;

import java.text.SimpleDateFormat;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Command.Board.BoardCommand;
import Model.DAO.BoardDAO;
import Model.DTO.AuthInfo;
import Model.DTO.BoardDTO;

@Service
public class BoardWriteService {
	@Autowired
	private BoardDAO boardDAO;
	
	public Integer insertBoard(BoardCommand boardCommand ,
			              HttpServletRequest request , HttpSession session) {
		Integer result =0;
		BoardDTO boardDTO = new BoardDTO();
		
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		
		boardDTO.setUserId(authInfo.getId());
		boardDTO.setBoardName(boardCommand.getBOARD_NAME());
		boardDTO.setBoardPass(boardCommand.getBOARD_PASS());
		boardDTO.setBoardSubject(boardCommand.getBOARD_SUBJECT());
		boardDTO.setBoardContent(boardCommand.getBOARD_CONTENT());
		boardDTO.setIpAddr(request.getRemoteAddr());
		
		
		result = boardDAO.insertBoard(boardDTO);
		return result;
		
		
		//boardDTO.setIpAddr( );
		
		
//		pstmt.setString(1, dto.getUserId());
//		pstmt.setString(2, dto.getBoardName());
//		pstmt.setString(3, dto.getBoardPass());
//		pstmt.setString(4, dto.getBoardSubject());
//		pstmt.setString(5, dto.getBoardContent());
//		pstmt.setString(6, dto.getIpAddr());
		
		
	}
	
	

}
