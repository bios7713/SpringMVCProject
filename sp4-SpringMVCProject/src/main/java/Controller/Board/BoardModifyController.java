package Controller.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Command.Board.BoardCommand;
import Service.Board.BoardDetailService;
import Service.Board.BoardModifyService;

@Controller
public class BoardModifyController {
	@Autowired
	private BoardDetailService boardDetailService;
	@Autowired
	private BoardModifyService boardModifyServise;
	
	@RequestMapping(value="/board/boardModify")
	public String boardModify(@RequestParam(value="num") Integer boardNum , Model model,
			BoardCommand boardCommand) {
		System.out.println("boardModifyConroller:" + boardCommand.getBoardNum());
		boardDetailService.boardDetail(boardNum, model);

		return "board/qna_board_modify";
	}
	
	@RequestMapping(value="/board/boardModifyPro")
	public String boardModifyPro(BoardCommand boardCommand , Model model) {
		System.out.println("boardModifyProController"+boardCommand);
	 boardModifyServise.boardModify(boardCommand, model);
	 	System.out.println();
	return"redirect:/board/boardDetail/"+boardCommand.getBoardNum();	
	}

}
