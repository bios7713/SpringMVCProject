package Controller.AnswerBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Command.answerBoard.AnswerCommand;
import Service.Answer.AnswerDetailService;

@Controller
public class AnswerBoardDetailController {
      @Autowired
      private AnswerDetailService answerDetailService; //써주면 protected 아님
      
      @RequestMapping(value="/answer/answerDetail/{id}")
      public String detail(@PathVariable(value= "id") Integer boardNum , Model model) {
    	 
    	 
         return answerDetailService.detailView(boardNum,model, 1 );
      }
      
      @RequestMapping(value="/answer/answerReply")
      public String reply(@RequestParam(value= "num") Integer boardNum,Model model) {
    	    
    	  
    	  return answerDetailService.detailView(boardNum,model,2);
 
      }
      
      
      
      
      
}