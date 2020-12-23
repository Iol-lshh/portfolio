package test;

public class Book_test {
	private String book_title;
	private String book_author;
	private String book_img_src;
	private String book_link;
	

	public Book_test(String book_title, String book_author, String book_img_src, String book_link) {
		super();
		this.book_title = book_title;
		this.book_author = book_author;
		this.book_img_src = book_img_src;
		this.book_link = book_link;
	}
	
	public String getBook_link() {
		return book_link;
	}
	public void setBook_link(String book_link) {
		this.book_link = book_link;
	}
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public String getBook_img_src() {
		return book_img_src;
	}
	public void setBook_img_src(String book_img_src) {
		this.book_img_src = book_img_src;
	}
	
	
}
