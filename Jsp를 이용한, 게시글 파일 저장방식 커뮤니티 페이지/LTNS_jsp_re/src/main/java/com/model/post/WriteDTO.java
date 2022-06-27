package main.java.com.model.post;

import main.java.com.model.DTO;

public class WriteDTO implements DTO{

/*		//회원
		public int mm_id ;	//회원아이디
		public String nickname ;	//회원 닉네임 
	*/
	
		// 게시글 
		private int post_id ; // 게시글 고유번호 
		private String title ; //제목 
		private int writer;
		private String category ; // 카테고리 
		private String regdate ; // 날짜
		private int post_contents;
		private int viewCnt ; // 조회수 
		
	
		
		
		
		
		
		public WriteDTO() {
		}
		
		

		public WriteDTO(int post_id, String title, int writer, String category, int post_contents,
				int viewCnt) {
			super();
			this.post_id = post_id;
			this.title = title;
			this.writer = writer;
			this.category = category;
			this.post_contents = post_contents;
			this.viewCnt = viewCnt;
		}







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


		public int getWriter() {
			return writer;
		}


		public void setWriter(int writer) {
			this.writer = writer;
		}


		public String getCategory() {
			return category;
		}


		public void setCategory(String category) {
			this.category = category;
		}


		public int getPost_contents() {
			return post_contents;
		}


		public void setPost_contents(int post_contents) {
			this.post_contents = post_contents;
		}


		public int getViewCnt() {
			return viewCnt;
		}


		public void setViewCnt(int viewCnt) {
			this.viewCnt = viewCnt;
		}


		public String getRegDate() {
			//System.out.println("getRegdate() 호출");
			return regdate;
		}

		public void setRegDate(String regdate) {
			this.regdate = regdate;
		}


		@Override
		public String toString() {
			return "WriteDTO [post_id=" + post_id + ", title=" + title + ", writer="  + ", category=" + category
					+ ", regdate=" + regdate + ", post_contents=" + post_contents + ", viewCnt=" + viewCnt + "]";
		}




	




		
		
		
		
		
		
		
	
}
