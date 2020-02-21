package Controller.Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Command.Board.BoardCommand;
import Service.Board.BoardWriteService;

@Controller
public class BoardWriteController {
  @Autowired
  private BoardWriteService boardWriteService;
  
  @RequestMapping("/board/boardWrite")
  public String boardWrite(BoardCommand boardCommand) {
 
	  return "board/qna_board_write";
  }
  @RequestMapping(value= "/board/boardWritePro" , method = RequestMethod.POST)
  public String boardWritePro(BoardCommand boardCommand ,
		                                 HttpServletRequest request, HttpSession session) {
	 
	  try {	 
		 // DB >> DAO >> Service 만들어야댐...
		  //service = Controller의 의존객체
		  //DAO = service의 의존객체
		  boardWriteService.insertBoard(boardCommand, request, session);
		  return "redirect:/board/boardList";
		  
	  }catch(Exception e) {	  
	
		  e.printStackTrace();
		  return "board/qna_board_write";		  
	  }	 
  }
}
