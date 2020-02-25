package Service.Answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Model.DAO.AnswerBoardDAO;
import Model.DTO.AnswerBoardDTO;

@Service
public class AnswerListService {
	@Autowired
	AnswerBoardDAO dao;
	
	public void list(Model model) {
		
		List<AnswerBoardDTO> dto  = dao.answerAllSelect();
		
		model.addAttribute("answer", dto);
		
		
		
	}

}
