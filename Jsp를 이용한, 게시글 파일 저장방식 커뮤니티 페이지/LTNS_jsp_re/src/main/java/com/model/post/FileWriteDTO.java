package main.java.com.model.post;

import main.java.com.model.DTO;

public class FileWriteDTO implements DTO{

	//파일테이블 
	public int file_id ;
	public String filekind;
	public String real_filename;
	public String filename;
	
	
	public FileWriteDTO() {
		// TODO Auto-generated constructor stub
	}


	public FileWriteDTO(int file_id, String filekind, String real_filename, String filename) {
		super();
		this.file_id = file_id;
		this.filekind = filekind;
		this.real_filename = real_filename;
		this.filename = filename;
	}


	public int getFile_id() {
		return file_id;
	}


	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}


	public String getFilekind() {
		return filekind;
	}


	public void setFilekind(String filekind) {
		this.filekind = filekind;
	}


	public String getReal_filename() {
		return real_filename;
	}


	public void setReal_filename(String real_filename) {
		this.real_filename = real_filename;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	@Override
	public String toString() {
		return "FileWriteDTO [file_id=" + file_id + ", filekind=" + filekind + ", real_filename=" + real_filename
				+ ", filename=" + filename + "]";
	}
	
	
	
	
	
	
}
