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
			boardDTO.setBoardDate(rs.getTimestamp("board_date"));
			boardDTO.setIpAddr(rs.getString("ip_addr"));						
			return boardDTO; } };
			
			
			
	public BoardDTO selectBoardNum(BoardDTO boardDTO) {
		String sql= " select board_num, user_id, board_name, board_subject, " + 
				         " board_content, board_date, ip_addr from board where board_num = ? " ;
		List<BoardDTO> results = jdbcTemplate.query(
				                                sql,boardRowMapper,boardDTO.getBoardNum());
		
		return results.isEmpty() ? null : results.get(0);
	}

	public List<BoardDTO> boardListSelect() {
		String sql = " select board_num, user_id, board_name, board_subject, "
				+        " board_content, board_date, ip_addr from board " ;

		List<BoardDTO > result = jdbcTemplate.query(sql, new RowMapper<BoardDTO>() {
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
		});

		return result;
	}
	public Integer insertBoard(BoardDTO boardDTO) {
		Integer i= 0;
		String sql=" insert into Board (board_num, user_id, board_name, board_pass, board_subject,"
				+ "    board_content, board_date, ip_addr, read_count )"
				+ "values(num_seq.nextval,?,?,?,?,?,default,?,0)";
		 
		i = jdbcTemplate.update(sql, 
				boardDTO.getUserId(),
				boardDTO.getBoardName(),
				boardDTO.getBoardPass(),
				boardDTO.getBoardSubject(),
				boardDTO.getBoardContent(),
				boardDTO.getIpAddr());
		return i;
	}





}
