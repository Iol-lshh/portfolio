package ji.lock_mw;

import java.util.Vector;

public class Lock_MW_DTO extends Vector<Lock_MW_DTO>{
	String TT_name;
	Integer TT_man, TT_woman;
	public Lock_MW_DTO() {
		
		String TT_name=null;
		Integer TT_man=null, TT_woman=null;
	}
	
	public Lock_MW_DTO(String tT_name, Integer tT_man, Integer tT_woman) {
		TT_name = tT_name;
		TT_man = tT_man;
		TT_woman = tT_woman;
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
