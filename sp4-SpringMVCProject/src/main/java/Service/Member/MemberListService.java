package Service.Member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Model.DAO.MemberDAO;
import Model.DTO.MemberDTO;

@Service
public class MemberListService {
	@Autowired
	MemberDAO memberDAO;
	
	public void memberList(Model model) {
			List<MemberDTO> memberDTO = 
											memberDAO.selectList();				
			if(memberDTO != null) {
				model.addAttribute("memberList" , memberDTO);	
				model.addAttribute("count",  memberDAO.count());
			}
		
		
	}

}
