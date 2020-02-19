package Service.Board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Model.DAO.BoardDAO;
import Model.DTO.BoardDTO;

@Service
public class BoardListService {
	@Autowired
	BoardDAO boardDAO;
	
	
	public void boardList(Model model) {
		List<BoardDTO> boardDTO = boardDAO.boardListSelect();
		if(boardDTO != null) {
			model.addAttribute("boards",boardDTO );

		}
		
		
	}




}
