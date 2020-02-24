package Controller.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Service.Library.LibraryDetailService;
@Controller
public class LibraryDetailController {
	@Autowired
	 private LibraryDetailService libraryDetailService;
	@RequestMapping("library/libraryDetail")
	public String libraryDetail(@RequestParam("num") Integer boardNum ,Model model) {
		String tableName= "libraryboard";
		libraryDetailService.libraryDeatil(boardNum,model,tableName,0);
		return "library/board_view";
	}
	
	
	@RequestMapping("/library/libraryModify")
	public String libraryModify(@RequestParam(value="num") Integer boardNum , Model model) {
		String tableName =  "libraryBoard";
		
		libraryDetailService.libraryDeatil(boardNum, model , tableName,1);
		
		return "library/board_modify";

	}
}
