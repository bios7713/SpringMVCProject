package Service.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import Command.Library.LibraryCommand;
import Controller.Encrypt;
import Model.DAO.LibraryDAO;
import Model.DTO.LibraryDTO;

@Service
public class LibraryModifyService {
@Autowired
 private LibraryDAO libraryDAO;

public String modifyPro(LibraryCommand libraryCommand , Errors errors) {
	LibraryDTO dto = new LibraryDTO();
	 	dto.setBoardNum(libraryCommand.getBoardNum());
	    dto.setBoardSubject(libraryCommand.getBoardSubject());
	    dto.setBoardContent(libraryCommand.getBoardContent());
	    dto.setBoardPass(Encrypt.getEncryption(libraryCommand.getBoardPass()));
	Integer i  = libraryDAO.libraryUpdate(dto);
	String path = null;
	if(i > 0) {							
		path = "redirect:/library/libraryDetail?num="+dto.getBoardNum();
	}else {
		errors.rejectValue("boardPass", "badPw");		
		path = "library/board_modify"; 	
	}
	
	return path;
	
}




	
	
}
