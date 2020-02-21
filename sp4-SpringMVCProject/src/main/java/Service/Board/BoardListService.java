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
	
	
	public void boardList(Model model , Integer page) {
		//먼저 DAO에 select메소드를 만듬 .
		//List는 List로 받아올수있다..
		// request = model ;;		
		int nowPage = 1;
		if(page != null) {			
			nowPage = page;
        }		
		int limit = 10;
		int limitPage = 10;
		
		
		
		List<BoardDTO> boardDTO = boardDAO.boardListSelect(page, limit);					
		if(boardDTO != null) {
			model.addAttribute("boards",boardDTO );

		}
		
		
	}




}
