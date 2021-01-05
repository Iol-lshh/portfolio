package com.ltns.rest_area.domain.admin;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FIleDatas {

	String text;
	String directory;
	
	public FIleDatas(String text, String directory , String date) {
		
		this.text = text;
		this.directory = directory;
		String filepath ="";
		


		PrintWriter pw = null;
		try {
			File f = new File(directory);
			
			if(!f.exists()) {
				f.mkdirs();
			}
			filepath = directory + date + ".txt";
			pw = new PrintWriter(filepath);
			pw.println(text);
			
		}catch (Exception e) {
			System.out.println("저장실패 ");
		}finally {
			pw.close();
		}
	}
	
}
