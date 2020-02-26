package Controller.AnswerBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Command.answerBoard.AnswerCommand;
import Service.Answer.AnswerReplyService;
import Validator.AnswerCommandValidate;

@Controller
public class AnswerReplyController {
	@Autowired
	private AnswerReplyService answerReplyService;
	@RequestMapping("/answer/answerReplyPro")
	public String reply( AnswerCommand answerCommand , Errors errors ,HttpSession session ,HttpServletRequest request ) {
		new AnswerCommandValidate().validate(answerCommand,errors);
		if(errors.hasErrors()){
			
			return "answerBoard/ans_board_reply";
		}
		answerReplyService.replysvc(answerCommand ,session ,request );
		
		return "redirect:/answer/answerList";
	}
	
}
