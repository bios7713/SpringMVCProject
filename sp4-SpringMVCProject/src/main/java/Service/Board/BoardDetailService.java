package Service.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Model.DAO.BoardDAO;
import Model.DTO.BoardDTO;

@Service
public class BoardDetailService {
	@Autowired
	private BoardDAO boardDAO;
	
	public void boardDetail(Integer boardNum, Model model) {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBoardNum(boardNum);
		boardDTO = boardDAO.selectBoardNum(boardDTO);
		model.addAttribute("boards" , boardDTO);
		
	}
}
