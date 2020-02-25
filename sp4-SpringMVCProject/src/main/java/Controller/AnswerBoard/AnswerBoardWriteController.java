package Controller.AnswerBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Command.answerBoard.AnswerCommand;
import Service.Answer.AnswerWriteService;

@Controller
@RequestMapping("/answer/answerWrite")
public class AnswerBoardWriteController {
	@Autowired
	private AnswerWriteService answerWriteService;
															// new AnswerCommand();
															// @ModelAttribute("answerCommand") AnswerCommand answerCommand
		@RequestMapping(method = RequestMethod.GET)
		public String writeform(@ModelAttribute("answerCommand") AnswerCommand answerCommand ) {
		
			
			return "answerBoard/ans_board_write";
			}
		
		
		@RequestMapping(method = RequestMethod.POST)
		public String writeSubmit(AnswerCommand answerCommand, HttpServletRequest request, HttpSession session) {
		String path=	answerWriteService.answerInsert(answerCommand, request,session);
			
			return path;
		}
		

		

}
