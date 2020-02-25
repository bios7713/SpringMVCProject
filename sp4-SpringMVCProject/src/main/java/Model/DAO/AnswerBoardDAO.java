package Model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import Model.DTO.AnswerBoardDTO;

public class AnswerBoardDAO {
	private JdbcTemplate JdbcTemplate;
	private String sql;           
	private final String COLUMNS = " board_content, board_name, board_pass, ip_addr, user_id, board_subject, "
		                       	                + " board_num, read_count, board_date, "
                                                + " original_file_name, store_file_name, file_size, "
                                                + " board_re_ref, board_re_lev, board_re_seq ";
	private RowMapper<AnswerBoardDTO> answerBoardRowMap =  new RowMapper<AnswerBoardDTO>() {

		public AnswerBoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
					AnswerBoardDTO dto = new AnswerBoardDTO();
					dto.setBoardContent(rs.getString("board_content"));
					dto.setBoardName(rs.getString("board_name"));
					dto.setBoardPass(rs.getString("board_pass"));
					dto.setIpAddr(rs.getString("ip_addr"));
					dto.setUserId(rs.getString("user_id"));
					dto.setBoardSubject(rs.getString("board_subject"));
					dto.setBoardNum(rs.getInt("board_num"));
					dto.setReadCount(rs.getInt("read_count"));
					dto.setBoardDate(rs.getTimestamp("board_date"));
					dto.setOriginalFileName(rs.getString("original_file_name"));
					dto.setStoreFileName(rs.getString("store_file_name"));
					dto.setFileSize(rs.getString("file_size"));
					dto.setBoardReRef(rs.getInt("board_re_ref"));
					dto.setBoardReLev(rs.getInt("board_re_lev"));
					dto.setBoardReSeq(rs.getInt("board_re_seq"));
			return dto;
		}	
	};
	
	@Autowired
	public AnswerBoardDAO(DataSource dataSource) {
		JdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<AnswerBoardDTO> answerAllSelect() {
		sql = "select " +  COLUMNS + " from answerboard ";
		
		 List<AnswerBoardDTO> dto = JdbcTemplate.query(sql,answerBoardRowMap);
		
		
		
	return dto;
	}
	
	public void insertAnswer(AnswerBoardDTO dto) {
		      sql = " insert into answerboard ("+ COLUMNS +") "
				        + " values (?, ?, ?, ?, ?, ?, num_seq.nextval,"
				        + " 0,sysdate,?,?,?, num_seq.currval,0,0) ";	      
		      JdbcTemplate.update(sql, 
		    		  dto.getBoardContent(),
		    		  dto.getBoardName(),
		    		  dto.getBoardPass(),
		    		  dto.getIpAddr(),
		    		  dto.getUserId(),
		    		  dto.getBoardSubject(),
		    		  dto.getOriginalFileName(),
		    		  dto.getStoreFileName(),
		    		  dto.getFileSize()		  
		    		  );
		   
	}

	
	
	
}
