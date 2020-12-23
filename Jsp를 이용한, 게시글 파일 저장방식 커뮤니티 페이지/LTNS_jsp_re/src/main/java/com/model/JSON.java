package main.java.com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JSON {
	private int count;		//데이터 개수
	private String status;	//처리 결과
	
	@JsonIgnore
	private String memo;	//제외할 데이터
	
	@JsonProperty("data")
	private List<DTO> list; //데이터

	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public List<DTO> getList() {
		return list;
	}
	public void setList(List<DTO> list) {
		this.list = list;
	}
	
	
}
