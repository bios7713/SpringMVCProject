package Controller.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class LogoutController {
	@RequestMapping("/logout")
	public String logout(HttpSession session , HttpServletResponse response) {
		Cookie autoCookie = new Cookie ("AutoCookie", " ");
		autoCookie.setMaxAge(0);
		response.addCookie(autoCookie);
		session.invalidate();	
		return "redirect:/main";
	
	}
	
}
