package test.maker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import main.java.com.model.membermanage.MM_DAO;
import main.java.com.model.membermanage.MM_DTO;

public class SampleMember_Maker {
	
	//id를 만들어준다
	String mkId() {
		String result="";
		int whilelimit=(int)(Math.random()*8)+4;
		
		for(int i=0;i<whilelimit;i++) {
			char[] asciiiii= {'a','0'};
			//1: 아스키코드 이용 a~z
			//2: 0~9
			char randomchar=asciiiii[(int)(Math.random()*2)];
			if(randomchar>61)
				randomchar+=(int)(Math.random()*26);
			else
				randomchar+=(int)(Math.random()*10);
			result+=randomchar;
		}
		System.out.println(result);
		return result;
	}
	
	//원하는 갯수만큼 중복되지 않게 아이디를 만들어준다
	HashSet<String> mkIds(int i){
		HashSet<String> idSet=new HashSet<String>();
		while(idSet.size()<i) {
			idSet.add(mkId());
		}
		return idSet;
	}
	
	HashSet<String> mkEmails(int i){
		HashSet<String> emailSet=new HashSet<String>();
		String [] emaildotcom= {"@gmail.com","@naver.com","@daum.com"};
		while(emailSet.size()<i) {
			emailSet.add(mkId()+emaildotcom[(int)(Math.random()*3)]);
			System.out.println(mkId()+emaildotcom[(int)(Math.random()*3)]);
		}
		return emailSet;
	}
	
	String[] mkPassword(int i) {
		String[] passwordArr=new String[i];
		for(int j=0;j<i;j++) {
			passwordArr[j]=mkId();
		}
		
		return passwordArr;
	}
	
	public void mkMembers(int memCnt) {
		HashSet<String> idSet;
		HashSet<String> nicknameSet;
		HashSet<String> emailSet;
		String[] passwordArr;

		SampleMember_Maker m = new SampleMember_Maker();
		
		idSet=m.mkIds(memCnt);
		nicknameSet=m.mkIds(memCnt);
		emailSet=m.mkEmails(memCnt);
		passwordArr=m.mkPassword(memCnt);
		
		String[] idArr=new String[memCnt];
		new ArrayList<String>(idSet).toArray(idArr);
		
		String[] nicknameArr=new String[memCnt];
		new ArrayList<String>(nicknameSet).toArray(nicknameArr);
		
		String[] emailArr=new String[memCnt];
		new ArrayList<String>(emailSet).toArray(emailArr);
		


		for(int i=0;i<memCnt;i++) {
			//dao로 샘플멤버 집어넣기 해야함!
			try {
				new MM_DAO_tester().insertBySQL_withDTO(MM_DAO_tester.INSERT_MM_BY_DTO, new MM_DTO(idArr[i],passwordArr[i],nicknameArr[i],emailArr[i],"MEMBER"));
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					System.out.println(i+"번째 오류");
					e.printStackTrace();
				}
			} catch (SQLException e) {
				System.out.println(i+"번째 오류");
				e.printStackTrace();
			}
		}
	}
	
	
	
	//메인문
	public static void main(String[] args) {
		HashSet<String> idSet;
		HashSet<String> nicknameSet;
		HashSet<String> emailSet;
		String[] passwordArr;
		
		//만들 샘플 멤버 갯수
		int memCnt=500;

		SampleMember_Maker m = new SampleMember_Maker();
		
		idSet=m.mkIds(memCnt);
		nicknameSet=m.mkIds(memCnt);
		emailSet=m.mkEmails(memCnt);
		passwordArr=m.mkPassword(memCnt);
		
		String[] idArr=new String[memCnt];
		new ArrayList<String>(idSet).toArray(idArr);
		
		String[] nicknameArr=new String[memCnt];
		new ArrayList<String>(nicknameSet).toArray(nicknameArr);
		
		String[] emailArr=new String[memCnt];
		new ArrayList<String>(emailSet).toArray(emailArr);
		
		for(int i=0;i<memCnt;i++) {
			//dao로 샘플멤버 집어넣기 해야함!
			try {
				new MM_DAO_tester().insertBySQL_withDTO(MM_DAO_tester.INSERT_MM_BY_DTO, new MM_DTO(idArr[i],passwordArr[i],nicknameArr[i],emailArr[i],"MEMBER"));
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				System.out.println(i+"번째 오류");
				e.printStackTrace();
			}
		}
		
	}
}
