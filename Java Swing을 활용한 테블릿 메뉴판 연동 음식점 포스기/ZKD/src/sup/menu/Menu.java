package sup.menu;

import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;

public class Menu {
	
	

	String  menu_name, menu_catag;
	int menu_price;
	String imageF;
//	public Menu(String , String menu_name, String menu_catag, int menu_price, File imageF) {
//		super();
//		this. = ;
//		this.menu_name = menu_name;
//		this.menu_catag = menu_catag;
//		this.menu_price = menu_price;
//		this.imageF = imageF;
//	}
	public Menu() {
		// TODO Auto-generated constructor stub
	}
	public Menu( String menu_name, String menu_catag, int menu_price) {
		super();
		
		this.menu_name = menu_name;
		this.menu_catag = menu_catag;
		this.menu_price = menu_price;
	}
	public Menu( String menu_name, String menu_catag, int menu_price, String imageF) {
		super();
		
		this.menu_name = menu_name;
		this.menu_catag = menu_catag;
		this.menu_price = menu_price;
		this.imageF = imageF;
	}
	
	public String getImageF() {
		return imageF;
	}


	public void setImageF(String imageF) {
		this.imageF = imageF;
	}


	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_catag() {
		return menu_catag;
	}
	public void setMenu_catag(String menu_catag) {
		this.menu_catag = menu_catag;
	}
	public int getMenu_price() {
		return menu_price;
	}
	public void setMenu_price(int menu_price) {
		this.menu_price = menu_price;
	}
	
	
}
