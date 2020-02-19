package Service.Member;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Model.DAO.MemberDAO;
import Model.DTO.MemberDTO;

@Service
public class MemberDetailService {
		  private MemberDAO memberDAO;
		  
		 public void memberDetail(String userId, Model model) {
			 MemberDTO memberDTO = new MemberDTO();
			 memberDTO.setUserId(userId);
			 memberDTO = memberDAO.selectByUserId(memberDTO);
			 model.addAttribute("member", memberDTO);
		 }
}
