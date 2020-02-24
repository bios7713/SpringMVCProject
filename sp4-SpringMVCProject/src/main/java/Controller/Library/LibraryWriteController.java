package Controller.Library;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Command.Library.LibraryCommand;
import Service.Library.LibraryWriteService;

@Controller
public class LibraryWriteController {
	@Autowired
	private LibraryWriteService libraryWriteService;
	@RequestMapping("/library/libraryWrite")
	public String libraryWrite(LibraryCommand libraryCommand) {

		return "/library/board_write";
				
	}
	@RequestMapping(value="/library/libraryWritePro" , method = RequestMethod.POST)
	public String libraryWritePro(LibraryCommand libraryCommand, HttpSession session, HttpServletRequest request) {
		
		libraryWriteService.libraryWrite(libraryCommand, session, request);
	
		return "redirect:/library/libraryList";
	}
	
}
