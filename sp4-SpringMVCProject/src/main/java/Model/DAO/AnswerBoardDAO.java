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
	private JdbcTemplate jdbcTemplate;
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
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	
	public void insertReply(AnswerBoardDTO dto) {
		sql = "update answerboard "
			+  " set board_re_seq = board_re_seq + 1 "
			+  " where board_re_ref = ? and board_re_seq > ?";
		jdbcTemplate.update(sql,dto.getBoardReRef(), dto.getBoardReSeq());
		
		Integer seq = dto.getBoardReSeq() + 1;
		Integer lev = dto.getBoardReLev() + 1;
		

		  sql = " insert into answerboard ("+ COLUMNS +") "
			        + " values (?, ?, ?, ?, ?, ?, num_seq.nextval,"
			        + " 0,sysdate,?,?,?,?,?,?) ";	      
	      jdbcTemplate.update(sql,   		  
	    		  dto.getBoardContent(),
	    		  dto.getBoardName(),
	    		  dto.getBoardPass(),
	    		  dto.getIpAddr(),
	    		  dto.getUserId(),
	    		  dto.getBoardSubject(),
	    		  dto.getOriginalFileName(),
	    		  dto.getStoreFileName(),
	    		  dto.getFileSize(),
	    		  dto.getBoardReRef(),seq,lev
	    		  );
		
		
		
	}
	
	
	
	public List<AnswerBoardDTO> answerAllSelect() {
		sql = "select " +  COLUMNS + " from answerboard "
			+  "order by board_re_ref DESC, board_re_seq ";
		
		 List<AnswerBoardDTO> dto = jdbcTemplate.query(sql,answerBoardRowMap);

	return dto;
	}
	
	public void insertAnswer(AnswerBoardDTO dto) {
		      sql = " insert into answerboard ("+ COLUMNS +") "
				        + " values (?, ?, ?, ?, ?, ?, num_seq.nextval,"
				        + " 0,sysdate,?,?,?, num_seq.currval,0,0) ";	      
		      jdbcTemplate.update(sql, 
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
	public AnswerBoardDTO answerDetailSelect(Integer boardNum) {
		sql = " select " + COLUMNS + "from answerboard "
				+ " where board_num = ? ";
		
		List<AnswerBoardDTO> dto = jdbcTemplate.query(sql,answerBoardRowMap,boardNum);
				
				
		
		return dto.isEmpty() ? null : dto.get(0);
		
	}
	
	
	
}
