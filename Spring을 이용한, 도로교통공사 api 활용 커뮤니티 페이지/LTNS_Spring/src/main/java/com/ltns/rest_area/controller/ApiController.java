package com.ltns.rest_area.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.restarea.RestAreaDTO;
import com.ltns.rest_area.service.ApiService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private ApiService apiService;
	
	@RequestMapping("/allapi")
	public String requestAllApi() {
		try {
			apiService.refreshApiData();
		} catch (Exception e) {
			System.out.println("에러 : /allapi");
			e.printStackTrace();
			return ".";
		}
		return ".";
	}
}
