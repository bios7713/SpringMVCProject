package Service.Answer;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Command.answerBoard.AnswerCommand;
import Controller.Encrypt;
import Model.DAO.AnswerBoardDAO;
import Model.DTO.AnswerBoardDTO;
import Model.DTO.AuthInfo;

@Service
public class AnswerWriteService {
	@Autowired
	AnswerBoardDAO answerBoardDAO;
	
	final String PATH = "WEB-INF\\view\\answerBoard\\update\\";
	public String answerInsert(AnswerCommand answerCommand, HttpServletRequest request ,HttpSession session) {
		AnswerBoardDTO dto = new AnswerBoardDTO();
		
	
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		
		dto.setUserId(authInfo.getId());
		dto.setBoardContent(answerCommand.getBoardContent());
		dto.setBoardName(answerCommand.getBoardName());
		dto.setBoardPass(Encrypt.getEncryption(answerCommand.getBoardPass()));
		dto.setBoardSubject(answerCommand.getBoardSubject());
		dto.setIpAddr(request.getRemoteAddr());
		String originalTotal = "";
		String storeTotal = "";
		String fileSizeTotal = "";
		
		
		String realPath = request.getServletContext().getRealPath(PATH);		
		System.out.println(realPath);
		for( MultipartFile mf  : answerCommand.getBoardFile() ) {
			
		    String original = mf.getOriginalFilename();
		    //확장자  										    마지막 .부터						
		    String originalFileExtension =    
		    										original.substring(original.lastIndexOf("."));
		    //저장하기 위한 이름                               문자로 가져오기  바꾸기  -를 지우기  
			String store = UUID.randomUUID().toString().replace("-","") + originalFileExtension;
			Long fileSize = mf.getSize();
			
			//originalTotal에  -를 붙여서 추가한다.
	         originalTotal += original + "-";
	         //storeTotal에 구분자(-)를 붙임
			 storeTotal += store + "-";
		 
			 fileSizeTotal += fileSize + "-";	
			 
			 File file = new File(realPath + "\\"+ store);  //파일 객체 생성!
			 
			 try {
				 //파일 저장하기 ?
				mf.transferTo(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("fileError", "용량이 초과했슴.");
		     	e.printStackTrace();
				return  "answerBoard/ans_board_write";				
			}
		}
		
		
		dto.setOriginalFileName(originalTotal);
		dto.setStoreFileName(storeTotal);
		dto.setFileSize(fileSizeTotal);
		
		answerBoardDAO.insertAnswer(dto);
		
		
		return "redirect:/answer/answerList";
	}
}
