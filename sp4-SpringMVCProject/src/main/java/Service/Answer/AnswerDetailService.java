package Service.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Command.answerBoard.AnswerCommand;
import Model.DAO.AnswerBoardDAO;
import Model.DTO.AnswerBoardDTO;

@Service
public class AnswerDetailService {
	@Autowired
	AnswerBoardDAO answerBoardDAO;

	public String detailView(Integer boardNum, Model model, int i ) {
		AnswerBoardDTO dto = new AnswerBoardDTO();	
		dto = answerBoardDAO.answerDetailSelect(boardNum);	
		if(dto == null) {
			return "redirect:/answer/answerList";
		}
		if(i == 1) {
		dto.setBoardContent(dto.getBoardContent().replace("\n", "<br />"));
		}else {
			dto.setBoardSubject(" ↪ RE :" + dto.getBoardSubject());
			dto.setBoardContent( "\n\n===========\n" + dto.getBoardContent());
		}
		
		if(dto.getOriginalFileName() != null) {
		String [] original = dto.getOriginalFileName().split("-");
		String [] store = dto.getStoreFileName().split("-");	
		model.addAttribute("original", original);
		model.addAttribute("store", store);
		}	
		model.addAttribute("answerCommand", dto);
		
		
		if(i == 1) {			
			return "answerBoard/ans_board_view";
		}else {				
		    return "answerBoard/ans_board_reply";
		}
		
	}
}
