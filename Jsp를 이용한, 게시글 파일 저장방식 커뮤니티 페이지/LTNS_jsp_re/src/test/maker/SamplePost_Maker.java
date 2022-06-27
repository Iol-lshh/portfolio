package test.maker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.java.com.model.DTO;
import main.java.com.model.mainpage.File_DTO;
import main.java.com.model.mainpage.Post_DTO;
import main.java.com.model.membermanage.MM_DTO;

public class SamplePost_Maker {
	
	String[] imgTagArr= {
			"/LTNS_jsp_re/images/너의이름은_도시_밤.png",
			"/LTNS_jsp_re/images/너의이름은_도시_새벽.png",
			"/LTNS_jsp_re/images/너의이름은_사당_황혼.png",
			"/LTNS_jsp_re/images/너의이름은_사당_황혼의시간.png",
			"/LTNS_jsp_re/images/너의이름은_신사_황혼.png",
			"/LTNS_jsp_re/images/너의이름은_신사.png",
			"/LTNS_jsp_re/images/너의이름은_혜성.png",
			"/LTNS_jsp_re/images/너의이름은_황혼.png",
			"/LTNS_jsp_re/images/너의이름은.png",
			"/LTNS_jsp_re/images/부엉이.gif",
			"/LTNS_jsp_re/images/요리.gif"
			};
	
	String mkRandomString() {
		String result="";
		int whilelimit=(int)(Math.random()*8)+4;
		
		for(int i=0;i<whilelimit;i++) {
			char[] asciiiii= {'a','A'};
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
	String mkRandomStringHangle() {
		return mkRandomStringHangle(1);
	}
	String mkRandomStringHangle(int num) {
		String[] noun= {"나","너","고양이","개","사랑","우리","죽음","존경","게시글","영화","게임","운동","현준","성언","휘진"};
		String[] postition= {"은(는) ","이(가) ","을(를) "};
		String[] verb= {"갖다. ","가지다. ","가졌다. ","가졌었다. ","달리다. ","달렸다. ","달렸었다. ","날다. ","날았다. ","날았었다. ","죽다. ","죽었다. ","죽었었다. ","살다. ","살았다. ","살았었다. ","날뛰다. ","날뛰었다. ","날뛰었었다. ","미치다. ","미쳤다. ","미쳤었다. ","갈다. ","갈았다. ","갈았었다. ","뛰다. ","뛰었다. ","뛰었었다. ","기어온다. ","기어왔다. ","기었었다. ","타다. ","탔다. ","탔었다. "};

		String result="";
		//1형식 명사+조사 명사+이다. 
		//2형식 명사+조사 동사
		//3형식 명사+조사 명사+조사 동사
		int[] form= {1,2,3};
		for(int i=0;i<num;i++) {
			String _result="";
			switch(form[(int)(Math.random()*3)]) {
			case 1:
				_result=noun[(int)(Math.random()*(noun.length-1))]+postition[(int)(Math.random()*(postition.length-1))]+noun[(int)(Math.random()*(noun.length-1))]+"이다. ";
				break;
			case 2:
				_result=noun[(int)(Math.random()*(noun.length-1))]+postition[(int)(Math.random()*(postition.length-1))]+verb[(int)(Math.random()*(verb.length-1))];
				break;
			case 3:
				_result=noun[(int)(Math.random()*(noun.length-1))]+postition[(int)(Math.random()*(postition.length-1))]+noun[(int)(Math.random()*(noun.length-1))]+postition[(int)(Math.random()*(postition.length-1))]+verb[(int)(Math.random()*(verb.length-1))];
				break;
			}
			result+=_result;
		}
		
		return result;
	}
	
	String returnImgTag() {

		String str="<img src=\""+imgTagArr[(int)(Math.random()*11)]+"\" title=\"11.PNG\" style=\"clear:both;\">";
		return str;
	}
	
	String mkStringOrTagOrImg(int i) {
		String result;
		switch(i) {
		case 1:
			result="&lt";
			break;
		case 2:
			result=returnImgTag();
			break;
		case 3:
			result="&nbsp";
			break;
		case 4:
			result="<"+mkRandomString()+">"+mkRandomStringHangle((int)(Math.random()*4)+4)+"</"+mkRandomString()+">";
			break;
		case 5:
			result="&gt";
			break;
		default:
			result=mkRandomStringHangle((int)(Math.random()*4)+4);
			break;
		}
		
		return result;
	}
	
	String mkLongString() {
		String longlongstr="";
		longlongstr+="<img src=\""+imgTagArr[(int)(Math.random()*11)]+"\" title=\"11.PNG\" style=\"clear:both;\">";
		for(int i=0;i<(int)(Math.random()*100);i++) {
			longlongstr+=mkStringOrTagOrImg((int)(Math.random()*7));
		}
		return longlongstr;
	}
	
	//1. 물리 파일 생성
	DTO mkRealFile() {
		String filePath="";
		String title=mkRandomStringHangle();
		String content=mkLongString();
		
//		Date d = new Date();
//		SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddHHmmss");
//
//		String fName = sm.format(d) + "_sample.txt";

		
		long time = System.currentTimeMillis();
		String fName = time + "_sample.txt";
		
		PrintWriter pw=null;
		//
		String saveDirectory ="WebContent\\data";
		File file = new File(saveDirectory);
		try {
			if (!file.exists()) { // 디렉토리가 없으면 만들어줌
				file.mkdirs();
			}
			filePath = saveDirectory + File.separator + fName; // 파일경로

			// 내용저장객체 생성
			pw = new PrintWriter(filePath);
			pw.println("title" + title);
			pw.println(content);
			System.out.println(filePath);
			System.out.println("저장성공!\nfilePath : "+file.getAbsolutePath());
		} catch (Exception e) {
			
			System.out.println("저장 실패 : 파일에 데이터를 쓸 수 없습니다.");
		} finally {
			try {
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		DTO dto=new File_DTO(0, "POST", fName, file.getAbsolutePath()+"\\"+fName);
		((File_DTO)dto).setTitle(title);
		return dto;

		
//		return new File_DTO(0, "POST", title, file.getAbsolutePath()+"\\"+fName);

//		DTO dto=new File_DTO(0, "POST", fName, file.getAbsolutePath()+"\\"+fName);
//		((File_DTO)dto).setTitle(title);
//		return dto;
	}
	
	//2. 파일 테이블 삽입
	int insertFileToDB(DTO dto) {
		int file_id=0;
		try {
			file_id=new File_DAO_tester().insertBySQL_withDTO(File_DAO_tester.INSERT_NEW_FILE, dto);
		} catch (SQLException e) {
			System.out.println("파일 테이블에 집어넣지 못함!");
			e.printStackTrace();
		}
		if(file_id==0)
			System.out.println("뭔가 잘못됐어..!!");
		return file_id;
	}
	
	//3. 포스트 테이블 삽입
	void insertPostToDB(DTO[] dtos) throws SQLException {
		new Post_DAO_tester().insertBySQL_withDTO(Post_DAO_tester.INSERT_NEW_POST, dtos);
	}

	public void mkPosts(int memCnt) {
		SamplePost_Maker spm=new SamplePost_Maker();
		ArrayList<DTO> dtoList=new ArrayList<DTO>();
		//만들 샘플 포스트 갯수
		
		
		try {
			for(int i=0;i<memCnt;i++) {
				DTO dto=spm.mkRealFile();
				String title=((File_DTO)dto).getTitle();
				Thread.sleep(10); //파일 생성 기다려주기
				
				int file_id=spm.insertFileToDB(dto);
				
				Thread.sleep(10); //DB 기다려주기
				
				//회원 dto array를 이용해서 mm_id를 랜덤하게 뿌려줌 dtoarr[(int)(Math.random()*(dtoarr.length))]
				DTO[] mmarr=new MM_DAO_tester().selectBySQL(MM_DAO_tester.SELECT_ALL_MM_ID);
				String[] categoryArr= {"NOTICE","MOVIE","BOOK","SPORTS","GAME"};
				
//				dto=new Post_DTO("몰랑파일열어서확인해랑이건샘플이당", ((MM_DTO)mmarr[(int)(Math.random()*(mmarr.length))]).getMm_id(), categoryArr[(int)(Math.random()*5)], file_id);

				// dto=new Post_DTO(title, 1, categoryArr[(int)(Math.random()*5)], file_id);

				int mm_id = (int)(Math.random() * 105) + 1;
				String category = categoryArr[(int)(Math.random()*5)];
				if(category.equals("NOTICE")) mm_id = 1;
				
				dto=new Post_DTO(title, mm_id, category, file_id);


				dtoList.add(dto);
				
				Thread.sleep(10); //DB 기다려주기
				System.out.println("post"+(i+1)+"번째 생성 완료!");
				Thread.sleep(50); //1초 채우기 해야돼???????????...현주놔........초다뉘라뉘..
			}
			DTO[] dtoArr=new Post_DTO[memCnt];
			dtoList.toArray(dtoArr);
			spm.insertPostToDB(dtoArr);

		} catch (InterruptedException e) {
			System.out.println("쓰레드 문제");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("sql 문제");			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		new SamplePost_Maker().mkPosts(1000);	

		System.out.println("전체 작업 완료!");
	}

}
