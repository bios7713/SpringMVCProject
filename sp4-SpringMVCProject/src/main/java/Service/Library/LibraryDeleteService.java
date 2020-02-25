package Service.Library;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Controller.Encrypt;
import Model.DAO.LibraryDAO;
import Model.DTO.LibraryDTO;


@Service
public class LibraryDeleteService {
	@Autowired
	LibraryDAO libraryDAO;

	public String libraryDelete(
			           String boardNum ,String boardPass, HttpServletRequest request) {
		
		
		    LibraryDTO dto= libraryDAO.libraryDetail(Integer.parseInt(boardNum), "libraryboard");
			Integer i = libraryDAO.libraryDel(boardNum,
													Encrypt.getEncryption(boardPass));
					System.out.println("i: " + i);	
			String path = " ";
		if(i > 0 ) {		
			
			
			String RealPath = "WEB-INF\\view\\library\\update\\";
			String filePath = request.getServletContext().getRealPath(RealPath);
			String [] storeFileName =  dto.getStoreFileName().split("-");
			for(String f : storeFileName) {
				File file = new File(filePath +"\\" +f);
				file.delete();			
			}
			path= "redirect:/library/libraryList";
			
		}else {
			
			request.setAttribute("passwordError", "비밀번호 땡");
			request.setAttribute("num", boardNum); 
			path="library/board_delete";
		}
		
		return path;
			
			
	}

}
