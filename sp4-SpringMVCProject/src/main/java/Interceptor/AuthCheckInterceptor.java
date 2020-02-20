package Interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

		HttpSession session = request.getSession();
		
		if(session != null) {
			Object authInfo = session.getAttribute("authInfo");
			if(authInfo != null) {				
				return true;
			}			
		}	
		
		response.sendRedirect(request.getContextPath()+"/main");
	
		return false;
	}


}
