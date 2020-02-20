package Service.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Command.Member.MemberCommand;
import Controller.Encrypt;
import Model.DAO.MemberDAO;
import Model.DTO.MemberDTO;

@Service
public class MemberModifyService {
	@Autowired
		MemberDAO memberDAO;
	public Integer memberModify(MemberCommand memberCommand , Model model) {
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setUserId(memberCommand.getUserId());
		memberDTO.setUserEmail(memberCommand.getUserEmail());
		memberDTO.setUserAddr(memberCommand.getUserAddr());
		memberDTO.setUserPh1(memberCommand.getUserPh1());
		memberDTO.setUserPh2(memberCommand.getUserPh2());
		memberDTO.setUserPw(
				Encrypt.getEncryption(memberCommand.getUserPw()));
		Integer i = memberDAO.memberModify(memberDTO);
	
		
		
		return i;
	}
}
