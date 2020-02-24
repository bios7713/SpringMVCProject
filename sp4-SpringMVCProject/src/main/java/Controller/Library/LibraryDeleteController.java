package Controller.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Service.Library.LibraryDeleteService;

@Controller
@RequestMapping("/library/libraryDelete")
public class LibraryDeleteController {
	
	@Autowired
	private LibraryDeleteService libraryDeleteService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String libraryDelete(@ModelAttribute(value="num") String boardNum, Model model) {

		return "library/board_delete";
	}
	@RequestMapping(method =RequestMethod.POST)
	public String libraryDeletePro(@RequestParam(value="boardNum") String boardNum, 
												@RequestParam(value="boardPass") String boardPass , Model model) {
		
		
		String path = libraryDeleteService.libraryDelete(boardNum , boardPass ,model);
		return path;
		
		
	}
	
	
	
}
