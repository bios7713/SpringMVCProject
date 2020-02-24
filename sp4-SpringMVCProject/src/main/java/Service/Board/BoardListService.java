package Service.Board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Model.DAO.BoardDAO;
import Model.DTO.BoardDTO;

@Service
public class BoardListService {
	@Autowired
	BoardDAO boardDAO;
	
	
	public void boardList(Model model , Integer page) {
		//癒쇱� DAO�뿉 select硫붿냼�뱶瑜� 留뚮벉 .
		//List�뒗 List濡� 諛쏆븘�삱�닔�엳�떎..
		// request = model ;;		
		int nowPage = 1;
		
		if(page != null) {			
			nowPage = page;
        }
		
		int limit = 10;
		int limitPage = 10;
		
		System.out.println("1page: " + nowPage + " limit: " + limit);
	    List<BoardDTO> boardDTO = boardDAO.boardListSelect(nowPage, limit);
	    int totalCount = boardDAO.boardCount();
		System.out.println("2page: " + nowPage + " limit: " + limit);
		int maxPage = (int)((double)totalCount / limit + 0.95);
		int startPage = (int)(((double)nowPage /limitPage + 0.95)-1) * limitPage+1;
		int endPage = startPage + limitPage -1;
		if(endPage > maxPage) endPage = maxPage;		
			
		
		System.out.println("3page: " + nowPage + " limit: " + limit);
	
			
			model.addAttribute("nowPage" , nowPage);
			model.addAttribute("maxPage" , maxPage);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			
			model.addAttribute("boards",boardDTO );
			model.addAttribute("totalcount",totalCount);


		
		
	}




}
