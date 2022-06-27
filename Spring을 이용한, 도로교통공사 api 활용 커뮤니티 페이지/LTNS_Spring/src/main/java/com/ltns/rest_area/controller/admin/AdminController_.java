package com.ltns.rest_area.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ltns.rest_area.domain.AjaxList;
import com.ltns.rest_area.domain.AjaxResult;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.admin.ScheduleDTO;
import com.ltns.rest_area.service.admin.ScheduleService;

@RequestMapping(value="/admin")
@Controller
public class AdminController_ {

	
	
	@Autowired
	ScheduleService service;
	

	@RequestMapping("/dashboard")
	public void dashboard(Model m, HttpServletRequest request) {
		String id =  (String)request.getSession().getAttribute("user");
		m.addAttribute("id", id);
	}
	
	
	
	@RequestMapping("/schedule")
	public void schedule(Model m, HttpServletRequest request) throws Exception
	{
		m.addAttribute("showSchedule", service.showSchedule());
		String id =  (String)request.getSession().getAttribute("user");
		m.addAttribute("id", id);
	}
	
	

	
	@RequestMapping("/memberInfo")
	public void memberInfo(Model m, HttpServletRequest request)	{
		String id =  (String)request.getSession().getAttribute("user");
		m.addAttribute("id", id);
	}
	
	@RequestMapping("/postInfo")
	public void postInfo(Model m, HttpServletRequest request)	{
		String id =  (String)request.getSession().getAttribute("user");
		m.addAttribute("id", id);
	}
	
	@RequestMapping("/areaInfo")
	public void areaInfo(Model m, HttpServletRequest request)	{
		String id =  (String)request.getSession().getAttribute("user");
		m.addAttribute("id", id);
	}
	
	
	
	@RequestMapping("/notice")
	public void notice(Model m, HttpServletRequest request)	{
		String id =  (String)request.getSession().getAttribute("user");
		m.addAttribute("id", id);
	}
	
	
	//팝업 
	@RequestMapping("/schdule/schedulePopup")
	public void Popup()	{}
	
	@RequestMapping("/schdule/deletePopup")
	public void deletePopup()	{}
	
	@RequestMapping("/schdule/updatePopup")
	public void updatePopup()	{}
	
	@RequestMapping("/repostPopup")
	public void repostPopup()	{}
	

	@RequestMapping("/notice/writer")
	public void writer(Model m, HttpServletRequest request){
		String id =  (String)request.getSession().getAttribute("user");
		m.addAttribute("id", id);
	}
	
	@RequestMapping("/notice/view")
	public void View(Model m, HttpServletRequest request){
		String id =  (String)request.getSession().getAttribute("user");
		m.addAttribute("id", id);
	}
	
	@RequestMapping("/notice/update")
	public void update(){}
	
	@RequestMapping("/notice/noticeModal")
	public void noticeModal(){}
	
	
	@RequestMapping("/areaInfoPopUp/areaInfoUpdate")
	public void areaInfoUpdate(){}
	
	
	@RequestMapping("/nav/navs")
	public void navs(Model m, HttpServletRequest request){
		String id =  (String)request.getSession().getAttribute("user");
		m.addAttribute("id", id);
	}
	
	
	@RequestMapping("/Report")
	public void report(){
	}
	
	
	
}
