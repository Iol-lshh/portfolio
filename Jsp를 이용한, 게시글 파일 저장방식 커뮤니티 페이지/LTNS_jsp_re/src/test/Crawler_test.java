package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;

public class Crawler_test {
	//도서 url : 교보문고 베스트셀러
	final static String bookUrl="http://www.kyobobook.co.kr/bestSellerNew/bestseller.laf";
	final static String cssSelector = "div.title strong";
	final static String cssSelector1 = "ul.list_type01 li";

	public Book_test[] crawl(String url) throws IOException {
		ArrayList<Book_test> list=new ArrayList<Book_test>();

		String book_title="";
		String book_author="";
		String book_link="";
		String book_img_src="";
		try {
			Document doc= Jsoup.connect(url).get();
			int i=0;
			for(Element el: doc.select(cssSelector1)) {
				String tmp=el.select("div.title strong").text();
				if(el.hasText() && !(tmp.equals(""))) {
//					System.out.println(el.html());
					

						book_title=el.select("div.title strong").text();

					List<TextNode> node =  el.select("div.author").textNodes();
					for (TextNode node1 : node) {
							book_author=node1.toString().trim();
							break;
					}
					book_link= el.select("div.cover a").attr("href");
					book_img_src=el.select("div.cover a img").attr("src");
					System.out.println(i++ + book_title);
					list.add(new Book_test(book_title, book_author, book_img_src, book_link));
				}
			}
//		String sample=doc.getElementsByClass("list_type01").html();
//		System.out.println(sample);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("크롤링 실패");
		}
		Book_test[] arr=new Book_test[list.size()];
		list.toArray(arr);
		return arr;
	}
	
	public static void main(String[] args) {
		Crawler_test crawler=new Crawler_test();
		try {
			Book_test[] arr=crawler.crawl(bookUrl);
			System.out.println(arr.length);
			for(Book_test b: arr) {
				System.out.println(b.getBook_title());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
