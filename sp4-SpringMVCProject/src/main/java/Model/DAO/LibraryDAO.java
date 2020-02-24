package Model.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import Model.DTO.LibraryDTO;

public class LibraryDAO {
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public LibraryDAO(DataSource dataSource) {				
		this.jdbcTemplate = new JdbcTemplate(dataSource);				
	}


	final String COLUMNS  = " board_num, user_id, board_name, board_pass, board_subject, "
			+ "board_content, board_date, ip_addr, read_count ,"
			+ " original_file_name, store_file_name, file_size ";



	private RowMapper<LibraryDTO> libraryRowMapper = 
			new RowMapper<LibraryDTO>() {

		public LibraryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			LibraryDTO libraryDTO = new LibraryDTO();
			libraryDTO.setBoardNum(rs.getInt("board_num"));
			libraryDTO.setUserId(rs.getString("user_id"));
			libraryDTO.setBoardName(rs.getString("board_name"));
			libraryDTO.setBoardSubject(rs.getString("board_subject"));
			libraryDTO.setBoardContent(rs.getString("board_content"));
			libraryDTO.setBoardPass(rs.getString("board_pass"));
			libraryDTO.setBoardDate(rs.getTimestamp("board_date"));
			libraryDTO.setIpAddr(rs.getString("ip_addr"));
			libraryDTO.setOriginalFileName(rs.getString("original_file_name"));
			libraryDTO.setStoreFileName(rs.getString("store_file_name"));
			libraryDTO.setFileSize(rs.getLong("file_size"));

			return libraryDTO;
		}				
	};



	public void readCountUpdate(Integer boardNum) {
		String sql = "update libraryboard set read_count  = +1 "
				+ "where board_num = ? ";
		jdbcTemplate.update(sql,boardNum);
		
		
	}
    public LibraryDTO libraryDetail(Integer  boardNum, String tableName) {
    	String sql= "select "  +  COLUMNS + " from  " + tableName
    			+ "		where board_num = ?" ; 	 
    	
    	List<LibraryDTO> list = jdbcTemplate.query(sql,libraryRowMapper,boardNum );
    	
    	return list.isEmpty() ? null : list.get(0);
    	
    }




	public List<LibraryDTO> libraryListSelect(int page , int limit) {

		String sql=  " select * "     				
	                  +  " from ( select rownum rn, board_num, user_id, board_name, board_subject,"   
	                  +  " 	   board_content, board_pass, board_date, ip_addr ,original_file_name, store_file_name, file_size" 				
	                  +  " 	   from ( select board_num, user_id, board_name, board_subject, "
	                  +  " 		   	   board_content, board_pass, board_date, ip_addr ,original_file_name, store_file_name, file_size "
	                  +  "                 from libraryboard order by board_num desc ))" 			       
	                  +  " where rn >= ? and rn <= ? ";

		int startrow = ( page -1) * limit +1;
		int endrow = startrow + limit -1;
		
		
		
		List<LibraryDTO> DTOlist = jdbcTemplate.query(sql,libraryRowMapper ,startrow ,endrow);


		return DTOlist;
	}


	
	public void insertLibrary(LibraryDTO libraryDTO) {
		String sql=" insert into libraryboard ( "+ COLUMNS +" ) "
				+  " values (num_seq.nextval, ?, ?, ?, ?, ?, sysdate, ?, 0, ?, ?, ?) ";
		jdbcTemplate.update(sql,
				libraryDTO.getUserId(),
				libraryDTO.getBoardName(),
				libraryDTO.getBoardPass(),
				libraryDTO.getBoardSubject(),
				libraryDTO.getBoardContent(),				
				libraryDTO.getIpAddr(),								
		
				libraryDTO.getOriginalFileName(),
				libraryDTO.getStoreFileName(),
				libraryDTO.getFileSize()				
				);



	}

	
	public Integer libraryCount() {
		
		String sql=" select count(*) from libraryboard ";
		

		
				return  jdbcTemplate.queryForObject(sql, Integer.class);
	}






}
