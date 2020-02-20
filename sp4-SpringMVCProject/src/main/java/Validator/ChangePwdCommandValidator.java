package Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import Command.Member.ChangePwdCommand;

public class ChangePwdCommandValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ChangePwdCommand.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "pw", "required");
		ValidationUtils.rejectIfEmpty(errors, "newPw", "required");
		ValidationUtils.rejectIfEmpty(errors, "reNewPw", "required");
		
		ChangePwdCommand regreq = (ChangePwdCommand) target;
		if(!regreq.getNewPw().isEmpty()) {
			if(!regreq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("reNewPw", "pwNomatch");
				
			}
		}
		
		
	}

}
