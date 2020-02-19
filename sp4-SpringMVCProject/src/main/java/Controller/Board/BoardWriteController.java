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
  public String boardWrite(BoardCommand boardCommand ) {
 
	  return "board/qna_board_write";
  }
  @RequestMapping(value= "/board/boardWritePro" , method = RequestMethod.POST)
  public String boardWritePro(BoardCommand boardCommand ,
		                                 HttpServletRequest request, HttpSession session) {
	  Integer i = 0;
	  try {	 
		 
		  i =  boardWriteService.insertBoard(boardCommand, request, session);
		  return "redirect:/board/boardList";
		  
	  }catch(Exception e) {	  
		  System.out.println("boardname" + boardCommand.getBOARD_NAME());
		  System.out.println("boardPass" + boardCommand.getBOARD_PASS());
		  System.out.println("boardContent" + boardCommand.getBOARD_CONTENT());
		  System.out.println("boardSubject" + boardCommand.getBOARD_SUBJECT());
		  
		  e.printStackTrace();
		  return "board/qna_board_write";		  
	  }	 
  }
}
