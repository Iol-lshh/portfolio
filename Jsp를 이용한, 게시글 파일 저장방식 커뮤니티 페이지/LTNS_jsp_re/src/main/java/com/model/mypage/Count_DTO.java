package main.java.com.model.mypage;

import main.java.com.model.DTO;

public class Count_DTO implements DTO {
	private int count=0;

	public Count_DTO(int count) {
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
