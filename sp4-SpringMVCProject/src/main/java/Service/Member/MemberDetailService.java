package Service.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Model.DAO.MemberDAO;
import Model.DTO.MemberDTO;

@Service
public class MemberDetailService {
	@Autowired
		  private MemberDAO memberDAO;
		  
		 public void memberDetail(String userId, Model model) {
			 System.out.println("service= "+ userId);
			 MemberDTO memberDTO = new MemberDTO();
			 memberDTO.setUserId(userId);
			 memberDTO = memberDAO.selectByUserId(memberDTO);	 
			
			 
			 model.addAttribute("memberCommand", memberDTO);
		 }
}
