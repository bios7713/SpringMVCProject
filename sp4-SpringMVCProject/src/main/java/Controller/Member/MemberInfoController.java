package Controller.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import Service.Member.MemberDetailService;


@Controller
public class MemberInfoController {
	@Autowired
	private MemberDetailService memberDetailService;
	
		@RequestMapping("/member/memberInfo/{aa}")
		public String memberInfo (@PathVariable("aa") String userId , Model model) {
			try {
				 memberDetailService.memberDetail(userId ,model);			
				return "redirect:/member/memberInfo";
				
			}catch(Exception e) {				
				
			
				e.printStackTrace();
				return "redirect:/member/memberList";					
			}
				
		}
		
		
}
