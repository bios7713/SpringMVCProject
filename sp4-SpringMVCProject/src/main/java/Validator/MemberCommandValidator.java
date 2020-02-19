package Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import Command.Member.MemberCommand;

public class MemberCommandValidator implements Validator{
	//정규표현식!
	private static final String emailRegExp = 
			"^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; 
	private Pattern pattern;
	
	public MemberCommandValidator() {
		pattern = Pattern.compile(emailRegExp);
	}
	public boolean supports(Class<?> clazz) {
		return false;
	}
	public void validate(Object target, Errors errors) {
		MemberCommand regReq = (MemberCommand)target;
		if(regReq.getUserEmail() == null || regReq.getUserEmail().trim().isEmpty()) {
			  errors.rejectValue("userEmail", "required");
		}else {
			Matcher matcher = pattern.matcher(regReq.getUserEmail());
			if(!matcher.matches()) {
				errors.rejectValue("userEmail", "bad");			
			}
			
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "required");
		ValidationUtils.rejectIfEmpty(errors, "userPw", "required");
		ValidationUtils.rejectIfEmpty(errors, "userPwCon", "required");
		ValidationUtils.rejectIfEmpty(errors, "userId", "required");
		ValidationUtils.rejectIfEmpty(errors, "userBirth", "required");
		ValidationUtils.rejectIfEmpty(errors, "userPh1", "required");
		ValidationUtils.rejectIfEmpty(errors, "userAddr", "required");
		if(regReq.getUserPw().isEmpty()) {
			if(!regReq.isUserPwEqualToUserPwCon()) {			
				errors.rejectValue("userPwCon", "nomatch");			
			}
			
		}
		
		
		
		
	}



}
