package Controller.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import Service.Library.LibraryListService;

@Controller
public class LibraryListController {
	@Autowired
	
	LibraryListService libraryListService;
	                  
	@RequestMapping("/library/libraryList")
	public String libraryList(Model model ) {
		libraryListService.libraryList(model);
		
		
		return "/library/board_list";
	}

}