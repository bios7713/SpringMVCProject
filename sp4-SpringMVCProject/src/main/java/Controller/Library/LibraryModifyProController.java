package Controller.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import Command.Library.LibraryCommand;
import Service.Library.LibraryModifyService;
import Validator.LibraryModifyValidator;

@Controller
public class LibraryModifyProController {
	@Autowired
	private LibraryModifyService libraryModifyService;
	@RequestMapping("/library/libraryModifyPro")
	public String libraryModifyPro(LibraryCommand libraryCommand, Errors errors) {
		new LibraryModifyValidator().validate(libraryCommand, errors);
	
     	if(errors.hasErrors()) {			
			return "library/board_modify";
		}
     	String path = libraryModifyService.modifyPro(libraryCommand,errors);
     
     	
     	return path;

	}
}
