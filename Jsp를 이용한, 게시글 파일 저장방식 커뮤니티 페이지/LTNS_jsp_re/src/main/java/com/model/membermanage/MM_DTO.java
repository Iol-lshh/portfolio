package main.java.com.model.membermanage;

import main.java.com.model.DTO;

public class MM_DTO implements DTO {
	private int mm_id;
	private String id;
	private String password;
	private String nickname;
	private String email;
	private String grade;
	
	
	public MM_DTO(int mm_id) {
		this.mm_id = mm_id;
	}

	public MM_DTO(int mm_id, String id, String password, String nickname, String email, String grade) {
		this.mm_id = mm_id;
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.grade = grade;
	}
	
	//insert용도
	public MM_DTO(String id, String password, String nickname, String email, String grade) {
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.grade = grade;
	}
	
	public int getMm_id() {return mm_id;}
	public void setMm_id(int mm_id) {this.mm_id = mm_id;}
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public String getNickname() {return nickname;}
	public void setNickname(String nickname) {this.nickname = nickname;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getGrade() {return grade;}
	public void setGrade(String grade) {this.grade = grade;}
	
}
