package Service.Library;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Command.Library.LibraryCommand;
import Controller.Encrypt;
import Model.DAO.LibraryDAO;
import Model.DTO.AuthInfo;
import Model.DTO.LibraryDTO;

@Service
public class LibraryWriteService {
	@Autowired
	private LibraryDAO libraryDAO;
	
	final String PATH = "WEB-INF\\view\\library\\update";
	public String libraryWrite(LibraryCommand libraryCommand , HttpSession session, HttpServletRequest request)  {
		LibraryDTO dto = new LibraryDTO();
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		
		dto.setUserId(authInfo.getId());
		dto.setBoardName(libraryCommand.getBoardName());
		dto.setBoardPass(Encrypt.getEncryption(libraryCommand.getBoardPass()));
		dto.setBoardSubject(libraryCommand.getBoardSubject());
		dto.setBoardContent(libraryCommand.getBoardContent());
		dto.setIpAddr(request.getRemoteAddr());
		// 파일을 담기위한 객체를 생성.
		String originalTotal = "";
		String storeTotal = "";
		String fileSizeTotal = "";
		String location = "";
		String filePath = request.getServletContext().getRealPath(PATH);
		System.out.println();
		System.out.println("filepath: " + filePath);
		
		for( MultipartFile mf : libraryCommand.getReport()) {
			
			String original = mf.getOriginalFilename();
			String originalFileExtension =  original.substring(original.lastIndexOf("."));
			String store = UUID.randomUUID().toString().replace("-","") + originalFileExtension;
			String fileSize = Long.toString(mf.getSize());
			
	         originalTotal += original + "-";
			 storeTotal += store + "-";
			 fileSizeTotal += fileSize + "-";	
			 
			 File file = new File(filePath + "\\"+ store);
			 try {
				mf.transferTo(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("fileError", "용량이 초과했슴.");
				location = "library/board_write";
				e.printStackTrace();
			}
		}

	   dto.setOriginalFileName(originalTotal);
	   dto.setStoreFileName(storeTotal);
	   dto.setFileSize(fileSizeTotal);
	   location = "redirect:/library/libraryList";
		
		libraryDAO.insertLibrary(dto);
		
		return location;
		
	}
	
	
}
