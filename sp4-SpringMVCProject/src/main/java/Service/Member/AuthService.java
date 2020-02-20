package Service.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import Command.Member.LoginCommand;
import Controller.Encrypt;
import Model.DAO.MemberDAO;
import Model.DTO.AuthInfo;
import Model.DTO.MemberDTO;


@Service
public class AuthService {
	
	@Autowired
	private MemberDAO memberDAO;	
	private AuthInfo authInfo;
	
	public void authenticate(LoginCommand loginCommand,Errors errors , HttpSession session, HttpServletResponse response) {
		MemberDTO memberDTO = new MemberDTO();
		
		Cookie idCookie = new Cookie("idCookie", loginCommand.getId1());
		if(loginCommand.getIdStore()) {			
			idCookie.setMaxAge(60*60 *12);			
		}else {
			idCookie.setMaxAge(0);
		}
		response.addCookie(idCookie);
		
		
		memberDTO.setUserId(loginCommand.getId1());
		memberDTO.setUserPw(  
				  Encrypt.getEncryption(loginCommand.getPw()));
		
		 memberDTO = memberDAO.selectByUserId(memberDTO);
		 
		 try {			 
			 authInfo = new AuthInfo(
				 		memberDTO.getUserId(),
				 		memberDTO.getUserEmail(),
				 		memberDTO.getUserName(),
				        memberDTO.getUserPw()				 	
				 );
			 
			if(authInfo.getPw().equals(
					 Encrypt.getEncryption(loginCommand.getPw()))){
			
				 Cookie autoCookie = new Cookie("autoCookie", loginCommand.getId1());
					if(loginCommand.getIdStore()) {			
						autoCookie.setMaxAge(60*60 *12);			
					}else {
						autoCookie.setMaxAge(0);
					}
					response.addCookie(autoCookie);
					
						 session.setAttribute("authInfo", authInfo);	
				 
			 }else {				 
				 System.out.println("비밀번호가 다른데 ?");
				 errors.rejectValue("pw","wrong");
			 }		 
		 }catch(Exception e) {
			 e.printStackTrace();
			 System.out.println("아이디 없는데?");
			 errors.rejectValue("id1", "notId");
			 
		 }
		 

		 
		 
		 
	
	
		 
		
		
	}
	 

}
