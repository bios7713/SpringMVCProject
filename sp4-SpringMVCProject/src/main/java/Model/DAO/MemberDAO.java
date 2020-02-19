package Model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import Model.DTO.MemberDTO;


public class MemberDAO {
	private JdbcTemplate jdbcTemplate;
	final String COLUMNS = " user_id, user_pw, user_name, user_birth, "
			+ " user_gender, user_email, user_addr, user_ph1, "
			+ " user_ph2 , user_regist";
	private RowMapper<MemberDTO> memRowMapper = 
			new RowMapper<MemberDTO>() {
		public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemberDTO member = new MemberDTO();
			member.setUserId(rs.getString("user_id"));
			member.setUserPw(rs.getString("user_pw"));
			member.setUserName(rs.getString("user_name"));
			member.setUserBirth(rs.getTimestamp("user_birth"));
			member.setUserGender(rs.getString("user_gender"));
			member.setUserEmail(rs.getString("user_email"));														
			member.setUserAddr(rs.getString("user_addr"));												
			member.setUserPh1(rs.getString("user_ph1"));							
			member.setUserPh2(rs.getString("user_ph2"));
			member.setUserRegist(rs.getTimestamp("user_regist"));	
			return member;
		}
	};

	@Autowired
	public MemberDAO(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Integer insertMember ( MemberDTO memberDTO) {
		Integer i = 0;
		String sql = " INSERT INTO MEMBER ("+COLUMNS+")" + 
				" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		i = jdbcTemplate.update(sql, 	 
				memberDTO.getUserId(),
				memberDTO.getUserPw(),
				memberDTO.getUserName(),
				memberDTO.getUserBirth(),
				memberDTO.getUserGender(), 
				memberDTO.getUserEmail(), 
				memberDTO.getUserAddr(),
				memberDTO.getUserPh1(),
				memberDTO.getUserPh2());
		memberDTO.getUserRegist();
		return i;
	} 

	//RowMapper select 하기위에 만들어진것.
	public MemberDTO selectByUserId(MemberDTO memberDTO) {
		
		String sql = " select "+ COLUMNS + " form member where user_Id = ? ";

		List<MemberDTO> results = jdbcTemplate.query(
															sql,memRowMapper,memberDTO.getUserId());

		return results.isEmpty() ? null: results.get(0);
				
		}
	}


