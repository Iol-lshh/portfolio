package main.java.com.view;

public class recomendDTO {

	
	private int post_id;
	private String regdate;
	private int empathize_cnt;
	private int view_cnt;
	
	public recomendDTO(int post_id, String regdate, int empathize_cnt, int view_cnt) {
		super();
		this.post_id = post_id;
		this.regdate = regdate;
		this.empathize_cnt = empathize_cnt;
		this.view_cnt = view_cnt;
	}
	
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getEmpathize_cnt() {
		return empathize_cnt;
	}
	public void setEmpathize_cnt(int empathize_cnt) {
		this.empathize_cnt = empathize_cnt;
	}
	public int getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
	
	
	
	
}
