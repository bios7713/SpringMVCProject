package Service.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Command.Board.BoardCommand;
import Model.DAO.BoardDAO;
import Model.DTO.BoardDTO;

@Service
public class BoardModifyService {
	@Autowired
	private BoardDAO boardDAO;
	
	public Integer boardModify(BoardCommand boardCommnd, Model model) {
		BoardDTO boardDTO = new BoardDTO();
	
		boardDTO.getBoardName();
		boardDTO.getBoardSubject();
		boardDTO.getBoardContent();
		
		Integer i=boardDAO.boardModify(boardDTO);
		
		return i;
	}
	

}
