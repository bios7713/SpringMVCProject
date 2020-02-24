package Service.Library;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Model.DAO.LibraryDAO;
import Model.DTO.BoardDTO;
import Model.DTO.LibraryDTO;

@Service
public class LibraryListService {
	@Autowired
	private	LibraryDAO libraryDAO;
	public void libraryList(Model model , Integer page) {

		int nowPage = 1;

		if(page != null) {			
			nowPage = page;
		}

		int limit = 10;
		int limitPage = 10;

		System.out.println("1page: " + nowPage + " limit: " + limit);
		List<LibraryDTO> dto = 	libraryDAO.libraryListSelect(nowPage, limitPage);
		model.addAttribute("library" , dto);

		int totalCount = libraryDAO.libraryCount();
		System.out.println("2page: " + nowPage + " limit: " + limit);
		int maxPage = (int)((double)totalCount / limit + 0.95);
		int startPage = (int)(((double)nowPage /limitPage + 0.9) -1) * 10 +1;
		int endPage = startPage + limitPage -1;
		if(endPage > maxPage) endPage = maxPage;		


		model.addAttribute("nowPage" , nowPage);
		model.addAttribute("maxPage" , maxPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		model.addAttribute("count",totalCount);



	}

}
