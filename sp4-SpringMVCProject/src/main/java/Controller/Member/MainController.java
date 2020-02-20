package Controller.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Command.Member.LoginCommand;
import Service.Member.MainService;

@Controller
@RequestMapping("/main")
public class MainController {
	@Autowired
	private MainService mainService;
	
	@RequestMapping( method =  RequestMethod.GET )
	public String main(LoginCommand  loginCommand , 
			 @CookieValue(value="idCookie",required = false ) Cookie idCookie ,
	         @CookieValue(value="autoCookie",required = false ) Cookie autoCookie , 
	         HttpSession session) {
			
		if(idCookie != null) {
			loginCommand.setId1(idCookie.getValue());
			loginCommand.setIdStore(true);		
		}
		if(autoCookie != null) {
			mainService.autoLogin(autoCookie.getValue() ,session);
		
		}
		
		
		return "main";
	}
	

}
