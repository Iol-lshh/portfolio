package main.java.com.model.mainpage;


import java.sql.SQLException;

import main.java.com.model.DTO;
import main.java.com.model.Post_Contents;

public class Post_DTO implements DTO {
	private int post_id; //게시글 테이블 고유 넘버(기본키)
	private String title; //제목
	private int mm_id; //작성자 mm_id(외래키)
	private String category;  //카테고리
	private String regdate;  //작성일
	private int file_id; //문서 파일 아이디(외래키)

	private Post_Contents post_contents; //실제 내용 객체
	
	private String nickname; //writer 로 가져온 회원 id(mm_id가 아니다!)
	private String id;
	
	private int viewCnt;  //조회수

	private int empathCnt; //공감수 - empathize view에서 가져온다
	
	
	
	public Post_DTO(int post_id, String title, int mm_id, String category, String regdate,
			Post_Contents post_contents, String nickname, String id, int viewCnt, int empathCnt) {
		super();
		this.post_id = post_id;
		this.title = title;
		this.mm_id = mm_id;
		this.category = category;
		this.regdate = regdate;
		this.post_contents = post_contents;
		this.nickname = nickname;
		this.id=id;
		this.viewCnt = viewCnt;
		this.empathCnt = empathCnt;
	}

	

	public Post_DTO(String title, int mm_id, String category, int file_id) {
		this.title = title;
		this.mm_id = mm_id;
		this.category = category;
		this.file_id = file_id;
		this.viewCnt=0;
	}



	public int getFile_id() {
		return file_id;
	}



	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}



	/*getter setter*/
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMm_id() {
		return mm_id;
	}
	public void setMm_id(int mm_id) {
		this.mm_id = mm_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public Post_Contents getPost_contents() {
		return post_contents;
	}
	public void setPost_contents(Post_Contents post_contents) {
		this.post_contents = post_contents;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getEmpathCnt() {
		return empathCnt;
	}
	public void setEmpathCnt(int empathCnt) {
		this.empathCnt = empathCnt;
	}


	
	
	
//	//★☆★☆이걸로 추가해야 한다!!!
//	public static void add_writerNcontents(DTO[] dtos) {
//		for(DTO dto:dtos) {
//			Post_DTO _dto=(Post_DTO)dto;
//			_dto.add_writerNcontents(_dto.getMm_id(), _dto.getPost_contents_file_id());
//		}
//	}
//	
//	
//	//작성자 닉네임과 Post_Contents 객체를 반환해줌
//	public void add_writerNcontents(int mm_id, int post_contents_file_id) {
//		try {
//			this.writer=return_writer(mm_id);
//			this.post_contents=return_post_contents(post_contents_file_id);
//		} catch (SQLException e) {
//			System.out.println("post_contents의 file_id에 정보가 없습니다!!! 혹은.. 없는 회원??");
//			e.printStackTrace();
//		}
//	}//end - add_writerNcontents()
//	
//	//MM을 DB에서 불러와 writer를 반환
//	public String return_writer(int mm_id) throws SQLException {
//		DTO mmDTO=new MM_DAO().selectBySQL(MM_DAO.SELECT_MM_BY_MM_ID, mm_id)[0];
//		String writer=((MM_DTO)mmDTO).getNickname();
//		return writer;
//	}
//	
//	//Post_Contents를 DB에서 불러와 생성 및 반환
//	public Post_Contents return_post_contents(int post_contents_file_id) throws SQLException {
//		
//		DTO fileDTO=new File_DAO().selectBySQL(File_DAO.SELECT_POSTFILE_BY_FILE_ID, post_contents_file_id)[0];
//		String file_path=((File_DTO)fileDTO).getReal_filename();
//		
//		return new Post_Contents(file_path);
//	}


	
	
	
	
}
