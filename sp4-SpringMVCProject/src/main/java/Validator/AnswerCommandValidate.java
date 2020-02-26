package Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import Command.answerBoard.AnswerCommand;

public class AnswerCommandValidate implements Validator{

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return AnswerCommand.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "boardName","required");
		ValidationUtils.rejectIfEmpty(errors, "boardPass","required");
		ValidationUtils.rejectIfEmpty(errors, "boardSubject","required");
		
		
		
		
		
		
		
	}
	
	

}
