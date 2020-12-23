package main.java.com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface By_AJAX {
	void responseJSON(HttpServletRequest request, HttpServletResponse response);
	void responseXML(HttpServletRequest request, HttpServletResponse response);
}
