package Service.Library;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Command.Library.LibraryCommand;
import Controller.Encrypt;
import Model.DAO.LibraryDAO;
import Model.DTO.AuthInfo;
import Model.DTO.LibraryDTO;

@Service
public class LibraryWriteService {
	@Autowired
	private LibraryDAO libraryDAO;
	
	public void libraryWrite(LibraryCommand libraryCommand , HttpSession session, HttpServletRequest request) {
		LibraryDTO dto = new LibraryDTO();
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		
		dto.setUserId(authInfo.getId());
		dto.setBoardName(libraryCommand.getBoardName());
		dto.setBoardPass(Encrypt.getEncryption(libraryCommand.getBoardPass()));
		dto.setBoardSubject(libraryCommand.getBoardSubject());
		dto.setBoardContent(libraryCommand.getBoardContent());
		dto.setIpAddr(request.getRemoteAddr());
		dto.setOriginalFileName(libraryCommand.getOriginalFileName());
		dto.setStoreFileName(libraryCommand.getStoreFileName());
		dto.setFileSize(libraryCommand.getFileSize());
		
		libraryDAO.insertLibrary(dto);
		
	}
	
	
}
