package Controller.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import Service.Board.BoardListService;

@Controller
public class BoardListController {
	@Autowired
	BoardListService boardListService;	
	@RequestMapping("/board/boardList")
	public String boardList(Model model) {
		boardListService.boardList(model);
		
		return "/board/qna_board_list";
	}

}
