package ji.tablet;

import java.util.Vector;

public class TabletDTO extends Vector<TabletDTO>{
	
	String TT_nickname, TT_concept, TT_do, TT_name;
	Integer TT_man, TT_woman;

	public TabletDTO() {
		String TT_name=null, TT_nickname=null, TT_concept=null, TT_do=null;
		Integer TT_man=null, TT_woman=null;
	}
	public TabletDTO(String tT_name, String tT_nickname, String tT_concept, Integer tT_man, Integer tT_woman,
			String tT_do) {
		TT_name = tT_name;
		TT_nickname = tT_nickname;
		TT_concept = tT_concept;
		TT_man = tT_man;
		TT_woman = tT_woman;
		TT_do = tT_do;
	}

	public String getTT_nickname() {
		return TT_nickname;
	}

	public String getTT_concept() {
		return TT_concept;
	}

	public String getTT_do() {
		return TT_do;
	}

	public String getTT_name() {
		return TT_name;
	}

	public Integer getTT_man() {
		return TT_man;
	}

	public Integer getTT_woman() {
		return TT_woman;
	}

	public void setTT_nickname(String tT_nickname) {
		TT_nickname = tT_nickname;
	}

	public void setTT_concept(String tT_concept) {
		TT_concept = tT_concept;
	}

	public void setTT_do(String tT_do) {
		TT_do = tT_do;
	}

	public void setTT_name(String tT_name) {
		TT_name = tT_name;
	}

	public void setTT_man(Integer tT_man) {
		TT_man = tT_man;
	}

	public void setTT_woman(Integer tT_woman) {
		TT_woman = tT_woman;
	}
	
	
	
	
}
