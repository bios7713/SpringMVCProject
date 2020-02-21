package Controller.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Service.Board.BoardListService;

@Controller
public class BoardListController {
	@Autowired
	BoardListService boardListService;	
	          //DB의 값을 ListPage로 보내려면 Model이 필요하다 !!
	@RequestMapping("/board/boardList")
	public String boardList(Model model , @RequestParam(value="page" , required = false) Integer page ) {
		
		
		
		boardListService.boardList(model, page);
	
		return "/board/qna_board_list";
	}

}
