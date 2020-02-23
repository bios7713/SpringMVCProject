package Service.Library;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Model.DAO.LibraryDAO;
import Model.DTO.LibraryDTO;

@Service
public class LibraryListService {
		@Autowired
	private	LibraryDAO libraryDAO;
		public void libraryList(Model model) {
			
			
			
		List<LibraryDTO> dto = 	libraryDAO.libraryListSelect();
			
		model.addAttribute("library" , dto);
		
		}
		
}
