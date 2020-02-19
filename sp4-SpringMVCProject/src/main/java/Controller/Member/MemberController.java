package Controller.Member;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Command.Member.MemberCommand;
import Service.Member.MemberJoinService;
import Validator.MemberCommandValidator;

@Controller
public class MemberController {
	@Autowired
	private MemberJoinService memberJoinService;

	@RequestMapping("register/agree") 
	public String argee() {			
		return "member/agree";
	}

	@RequestMapping("register/regist")
	public String memberForm(@RequestParam(value="agree" , defaultValue = "false" ) Boolean agree
			 , MemberCommand memberCommand) {
		if(!agree) {
			return "member/agree";					
		}											
		
		return "member/memberForm";
	}

	@RequestMapping(value="register/memberJoinAction" , method = RequestMethod.POST )
	public String joinActionPost( MemberCommand memberCommand, Errors errors) throws ParseException  {
			new MemberCommandValidator().validate(memberCommand, errors);
			if(errors.hasErrors()) {
				return "member/memberForm";
			}
		Integer i =  0;
		try {
			 i =  memberJoinService.execute(memberCommand);
			return"member/memberWelcome";
		}catch(Exception e) {
			e.printStackTrace();
			errors.rejectValue("userId","duplicate");
			return "member/memberForm";		
		}
	}
	@RequestMapping(value="register/memberJoinAction" , method = RequestMethod.GET )
	public String joinActionGet() {		
		return "redirect:agree";
	}
	

}
