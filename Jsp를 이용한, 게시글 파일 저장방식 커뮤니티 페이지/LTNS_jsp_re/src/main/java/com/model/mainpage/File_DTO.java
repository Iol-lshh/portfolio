package main.java.com.model.mainpage;

import main.java.com.model.DTO;

public class File_DTO implements DTO {
	private int file_id;
	private String filekind;
	private String filename;
	private String real_filename;
	private String title="";



	public File_DTO(int file_id, String filekind, String filename, String real_filename) {
		super();
		this.file_id = file_id;
		this.filekind = filekind;
		this.filename = filename;
		this.real_filename = real_filename;
	}
	
	
	public int getFile_id() {return file_id;}
	public void setFile_id(int file_id) {this.file_id = file_id;}
	public String getFilekind() {return filekind;}
	public void setFilekind(String filekind) {this.filekind = filekind;}
	public String getFilename() {return filename;}
	public void setFilename(String filename) {this.filename = filename;}
	public String getReal_filename() {return real_filename;}
	public void setReal_filename(String real_filename) {this.real_filename = real_filename;}
	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
}
