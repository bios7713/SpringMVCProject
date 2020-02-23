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
	          //DB�쓽 媛믪쓣 ListPage濡� 蹂대궡�젮硫� Model�씠 �븘�슂�븯�떎 !!
	@RequestMapping("/board/boardList")
	public String boardList(Model model , @RequestParam(value="page" , required = false) Integer page ) {
		
		
		
		boardListService.boardList(model, page);
	
		return "/board/qna_board_list";
	}

}
