package Controller.Library;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import Command.Library.LibraryCommand;
import Validator.LibraryModifyValidator;

@Controller
public class LibraryModifyProController {
	@RequestMapping("/library/libraryModifyPro")
	public String libraryModifyPro(LibraryCommand librarys, Errors errors) {
		new LibraryModifyValidator().validate(librarys, errors);

		if(errors.hasErrors()) {			
			return "library/board_modify";
		}
		return "library/board_view";

	}
}
