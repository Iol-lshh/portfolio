package test.maker;

import java.sql.SQLException;

import main.java.com.model.membermanage.MM_DTO;

public class Main_tester {

	public static void main(String[] args) {
		
		/*db 초기화*/
		try {
			Master_DAO_tester m=new Master_DAO_tester();
			m.refreshAll();
//			m.deleteAll();
			m.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			new MM_DAO_tester().insertBySQL_withDTO(MM_DAO_tester.INSERT_MM_BY_DTO, new MM_DTO("admin","0000","운영자","admin@LTNS.com","admin"));
			new MM_DAO_tester().insertBySQL_withDTO(MM_DAO_tester.INSERT_MM_BY_DTO, new MM_DTO("hong","0000","홍성혁","hong@LTNS.com","MEMBER"));
			new MM_DAO_tester().insertBySQL_withDTO(MM_DAO_tester.INSERT_MM_BY_DTO, new MM_DTO("hyun","0000","김현준","hyun@LTNS.com","MEMBER"));
			new MM_DAO_tester().insertBySQL_withDTO(MM_DAO_tester.INSERT_MM_BY_DTO, new MM_DTO("hwi","0000","김휘진","hwi@LTNS.com","MEMBER"));
			new MM_DAO_tester().insertBySQL_withDTO(MM_DAO_tester.INSERT_MM_BY_DTO, new MM_DTO("park","0000","박성언","park@LTNS.com","MEMBER"));
			System.out.println("admin 생성 완료!");
		} catch (SQLException e1) {
			System.out.println("admin 생성 오류");
			e1.printStackTrace();
		}
		
		/*샘플 정회원 100명 생성*/
		new SampleMember_Maker().mkMembers(100);

		System.out.println("회원생성 작업 완료!");
		
		
		
		/*샘플 포스트 1000개 생성*/
//		new SamplePost_Maker().mkPosts(200);
		
//		System.out.println("파일생성 작업 완료!");
	}

}
