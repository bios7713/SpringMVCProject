package Service.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Controller.Encrypt;
import Model.DAO.LibraryDAO;


@Service
public class LibraryDeleteService {
	@Autowired
	LibraryDAO libraryDAO;

	public String libraryDelete(
			           String boardNum ,String boardPass,Model model) {

			Integer i = libraryDAO.libraryDel(boardNum,
													Encrypt.getEncryption(boardPass));
					System.out.println("i: " + i);	
			String path = null;
		if(i > 0 ) {
			
			path= "redirect:/library/libraryList";
			
		}else {
			
			model.addAttribute("passwordError" ," 비빌번호가 다름.");
			model.addAttribute("num" , boardNum); 
			path="library/board_delete";
		}
		
		return path;
			
			
	}

}
