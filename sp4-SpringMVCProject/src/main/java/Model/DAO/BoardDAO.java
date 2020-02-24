package Model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.ui.Model;

import Model.DTO.BoardDTO;

public class BoardDAO {
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public BoardDAO(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper<BoardDTO> boardRowMapper = 		
			new RowMapper<BoardDTO>() {
		public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardDTO boardDTO  = new BoardDTO();
			boardDTO.setBoardNum(rs.getInt("board_num"));
			boardDTO.setUserId(rs.getString("user_id"));
			boardDTO.setBoardName(rs.getString("board_name"));
			boardDTO.setBoardSubject(rs.getString("board_subject"));
			boardDTO.setBoardContent(rs.getString("board_content"));
			boardDTO.setBoardPass(rs.getString("board_pass"));
			boardDTO.setBoardDate(rs.getTimestamp("board_date"));
			boardDTO.setIpAddr(rs.getString("ip_addr"));
			boardDTO.setBoardPass(rs.getString("board_pass"));					
			return boardDTO; 
		} 
	};


	public Integer boardModify(BoardDTO boardDTO) {
		String sql = " update board set board_subject= ?, "
				+        " board_content = ?, board_num= ? "
				+        " where board_pass  = ? ";

		Integer i= jdbcTemplate.update(sql, 
				boardDTO.getBoardSubject(),
				boardDTO.getBoardContent(),
				boardDTO.getBoardNum(),
				boardDTO.getBoardPass()				
				);
		return i;		
	}


	public BoardDTO selectBoardNum(BoardDTO boardDTO) {
		String sql= " select board_num, user_id, board_name, board_subject, " + 
				" board_content, board_date, ip_addr, board_pass from board where board_num = ? " ;
		List<BoardDTO> results = jdbcTemplate.query(
				sql,boardRowMapper,boardDTO.getBoardNum());

		return results.isEmpty() ? null : results.get(0);
	}
	//boardListService�뿉�꽌 List<BoardDTO> 瑜� 諛섑솚�빐以��떎..?

	public List<BoardDTO> boardListSelect(int page , int limit) {
	    String sql =  " select * "     				
				       +  " from ( select rownum rn, board_num, user_id, board_name, board_subject,"   
				       +  " 	   board_content,  board_pass,board_date, ip_addr " 				
				       +  " 	   from ( select board_num, user_id, board_name, board_subject, "
				       +  " 		   	   board_content, board_pass, board_date, ip_addr from board order by board_num desc ))" 			       
				       +  " where rn >= ? and rn <= ? ";
		
		int startrow = ( page -1) * limit +1;
		int endrow = startrow + limit -1;
		System.out.println("DAO Page: " + page);
		System.out.println("DAO limit: "+ limit);
	  
		List<BoardDTO>  DTOlist = jdbcTemplate.query(sql, new RowMapper<BoardDTO>() {
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO boardDTO = new BoardDTO();
			
				boardDTO.setBoardNum(rs.getInt("board_num"));
				boardDTO.setUserId(rs.getString("user_id"));
				boardDTO.setBoardName(rs.getString("board_name"));
				boardDTO.setBoardSubject(rs.getString("board_subject"));
				boardDTO.setBoardContent(rs.getString("board_content"));
				boardDTO.setBoardDate(rs.getTimestamp("board_date"));
				boardDTO.setIpAddr(rs.getString("ip_addr"));
				
				return boardDTO;
			}
		} , startrow,endrow);

		return DTOlist;
	}
	


	public void insertBoard(BoardDTO boardDTO) {

		String sql=" insert into Board ( board_num, user_id, board_name, board_pass, board_subject,"
				+" board_content, board_date, ip_addr, read_count )"
				+" values(num_seq.nextval,?,?,?,?,?,default,?,0)";

		jdbcTemplate.update(sql, 
				boardDTO.getUserId(),
				boardDTO.getBoardName(),
				boardDTO.getBoardPass(),
				boardDTO.getBoardSubject(),
				boardDTO.getBoardContent(),
				boardDTO.getIpAddr());
		

	}
	
	public Integer boardCount() {
		
		String sql=" select count(*) from board ";
		
		return  jdbcTemplate.queryForObject(sql, Integer.class);
		
		
	}





}
