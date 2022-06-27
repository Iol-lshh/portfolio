package main.java.com.model.post;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.java.com.model.DAO;
import main.java.com.model.DB;
import main.java.com.model.DTO;
import main.java.com.model.mainpage.Post_DTO;

public class FileWriteDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public static final String SQL_POST_CONTENT_VIEW = 
			"SELECT * FROM FILE_TABLE ft WHERE FILE_ID =  ?";
			
	public static final String SQL_FILE_UPDATE = 
			"UPDATE FILE_TABLE  SET FILENAME = ? , REAL_FILENAME  = ? WHERE file_id = ?";

	public static final String SQL_FILE_DELETE = 
			"DELETE FILE_TABLE WHERE FILE_ID = ?";
	
	
	
	public FileWriteDAO() {
		try {
			Class.forName(DB.DRIVER);
			conn = DriverManager.getConnection(DB.URL, DB.USERID, DB.USERPW);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() throws SQLException {
		if (rs != null)
			rs.close();
		if (psmt != null)
			psmt.close();
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();

	} // end close()

	public int fileInsert(FileWriteDTO dto) throws SQLException {

		int cnt = 0;

		String filekind = dto.getFilekind();
		String real_filename = dto.getReal_filename();
		String filename = dto.getFilename();

		cnt = this.fileInsert(filekind, real_filename, filename);

		return cnt;
	}

	public int fileInsert(String filekind, String real_filename, String filename) throws SQLException {

		int currval = 0;
		String sql = "INSERT INTO file_table" 
				+ "(file_id,filekind,real_filename,filename) " 
				+"VALUES"
				+"(SEQ_FILE_TABLE_FILE_ID.NEXTVAL,?,?,?)";

		String sql2 = "SELECT SEQ_file_table_file_id.CURRVAL FROM DUAL";

		try {
			conn.setAutoCommit(false);
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, filekind);
			psmt.setString(2, real_filename);
			psmt.setString(3, filename);
			psmt.executeUpdate();
			psmt.close();
			
			psmt = conn.prepareStatement(sql2);
			ResultSet tmprs = psmt.executeQuery();
			
			
			if(tmprs.next()) {
				currval=tmprs.getInt("currval");
			}
			
			
			conn.commit();
			System.out.println(currval);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		
			throw e;
		} finally {
			close();
		}
		return currval;

	}

	
	//글 내용 꺼내오기 
	public FileWriteDTO[] view_cotent(int post_contents) throws SQLException {
		
		int cnt = 0;
		FileWriteDTO[] arr = null;
		
		try {
			psmt = conn.prepareStatement(SQL_POST_CONTENT_VIEW);
			psmt.setInt(1, post_contents);
			rs = psmt.executeQuery();
			arr = createFiles(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return arr;
		
	}

	
	//파일업데이트 시키기 
	public int File_Update(String file_name, String file_real_path, int file_id) throws SQLException {
			int cnt = 0;
			try {
				psmt = conn.prepareStatement(SQL_FILE_UPDATE);
			
				psmt.setString(1, file_name);
				psmt.setString(2, file_real_path);
				psmt.setInt(3, file_id);
				cnt = psmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			return cnt;
		}
	
	
	//파일 삭제하기 
	
	 public int file_delete(int post_content) throws SQLException {
				int cnt =0;
				
				try {
					psmt = conn.prepareStatement(SQL_FILE_DELETE);
					psmt.setInt(1, post_content);
					cnt = psmt.executeUpdate();
				} finally {
					close();
				}
				
				return cnt;
	 }
	
	
	
	
	
	
	

	public FileWriteDTO[] createFiles(ResultSet rs) throws SQLException {
		ArrayList<FileWriteDTO> Filelist = new ArrayList<FileWriteDTO>();
		
		while(rs.next()) {

			int file_id = rs.getInt("file_id");
			String filekind = rs.getString("filekind");
			String readl_filename = rs.getString("real_filename");
			String filename = rs.getString("filename");
			FileWriteDTO filedto = new FileWriteDTO(file_id, filekind, readl_filename, filename);
			FileWriteDTO dtos = new FileWriteDTO(file_id, filekind, readl_filename, filename);
			Filelist.add(filedto);
			
		}
		
		int size = Filelist.size();
		if(size == 0 ) return null;
		
		FileWriteDTO arr[] = new FileWriteDTO[size];
		Filelist.toArray(arr);
		return arr;
	}
	
	
	

}
