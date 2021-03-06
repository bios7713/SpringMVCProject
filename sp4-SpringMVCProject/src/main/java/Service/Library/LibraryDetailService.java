package Service.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Model.DAO.LibraryDAO;
import Model.DTO.LibraryDTO;

@Service
public class LibraryDetailService {
	@Autowired
	private LibraryDAO libraryDAO;

	public void libraryDeatil(Integer boardNum , Model model , String tableName,Integer num) {
		libraryDAO.readCountUpdate(boardNum);
		LibraryDTO libraryDTO = new LibraryDTO();	
		libraryDTO.setBoardNum(boardNum);

		LibraryDTO dto = libraryDAO.libraryDetail(boardNum,tableName);

		if(num!=1 ) {
			dto.setBoardContent(dto.getBoardContent().replace("\n", "<br>"));
		}
	      String[] oriFile = dto.getOriginalFileName().split("-");
	      String[] strFile = dto.getStoreFileName().split("-");
	      String[] fileSize = dto.getFileSize().split("-");
		
		  model.addAttribute("libraryCommand" ,dto);
	      model.addAttribute("originalFileName" ,oriFile);
	      model.addAttribute("storeFileName" ,strFile);
	      model.addAttribute("fileSize" ,fileSize);
		

	}


}
