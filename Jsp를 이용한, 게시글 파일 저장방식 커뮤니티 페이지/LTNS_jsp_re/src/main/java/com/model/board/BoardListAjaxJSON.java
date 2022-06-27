package main.java.com.model.board;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoardListAjaxJSON {
	private long count;
	private String status;

	@JsonProperty("data")
	private List<BoardListJSON> list;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BoardListJSON> getList() {
		return list;
	}

	public void setList(List<BoardListJSON> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "AjaxBoardListJSON [count=" + count + ", status=" + status + ", list=" + list + "]";
	}

}