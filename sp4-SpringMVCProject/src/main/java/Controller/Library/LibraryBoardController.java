package Controller.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibraryBoardController {
	
	@RequestMapping("library/libraryList")
	public String libraryList(Model model ) {
		
		
		
		return "/library/lib_board_list";
	}
	@RequestMapping("library/libraryWrite")
	public String libraryWrite() {
		
		
	return "/library/lib_boardWrite";	
	}

}
