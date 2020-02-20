package Controller.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import Service.Board.BoardDetailService;

@Controller
public class BoardDetailController {
	@Autowired
	private BoardDetailService boardDetailService;
	                   
	@RequestMapping("/board/boardDetail/{num}")
	public String boardDetail (@PathVariable("num") Integer boardNum, Model model) {
			try {
				boardDetailService.boardDetail(boardNum, model);
				return "board/qna_board_view";
			}catch(Exception e){
				e.printStackTrace();
				return "redirect:/board/boardList";
			}
			
			
	}
	
	

}
