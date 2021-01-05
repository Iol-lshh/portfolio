package com.ltns.rest_area.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping("/auth/user/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		if (request.getSession().getAttribute("user") != null) {
			response.sendRedirect(request.getContextPath() + "/member/user/info");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/auth/user/loginmain.jsp");
			dispatcher.forward(request, response);
		}
	}

	@RequestMapping("/auth/user/lookupid")
	public void lookupid(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		if (request.getSession().getAttribute("user") != null) {
			response.sendRedirect(request.getContextPath() + "/member/user/info");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/auth/user/lookupid.jsp");
			dispatcher.forward(request, response);
		}
	}

	@RequestMapping("/auth/user/lookuppw")
	public void lookuppw(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (request.getSession().getAttribute("user") != null) {
			response.sendRedirect(request.getContextPath() + "/member/user/info");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/auth/user/lookuppw.jsp");
			dispatcher.forward(request, response);
		}
	}

	@RequestMapping("/auth/user/join")
	public void join(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (request.getSession().getAttribute("user") != null) {
			response.sendRedirect(request.getContextPath() + "/member/user/info");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/auth/user/sign-up.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@RequestMapping("/member/user/info")
	public void info() {
	}
	
	@RequestMapping("/member/user/mypage")
	public void mypage() {
	}
	
	@RequestMapping("auth/autherror")
	public void error() {
	}
	
	@RequestMapping("auth/report")
	public void report() {
	}
	
	@RequestMapping("/member/user/foods")
	public void foods() {
	}
	@RequestMapping("/member/user/gasStations")
	public void gasStations() {
	}
	@RequestMapping("/member/user/restAreas")
	public void restAreas() {
	}
	@RequestMapping("/member/user/likePosts")
	public void likePosts() {
	}
	@RequestMapping("/member/user/posts")
	public void posts() {
	}
	@RequestMapping("/member/user/comments")
	public void comments() {
	}
	
}
