package com.ltns.rest_area.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.service.SearchService;

@Controller
public class RestAreaController_ {
	
	@Autowired
	SearchService searchService; 
	
	@GetMapping("/rest_area/list")
	public String _listPage() {return "/restarea/list";}
	
	
	@GetMapping("/rest_area/list/{listSort}/{routeName}/{destination}/{orderBy}")
	public String listPage(@PathVariable String listSort, @PathVariable String routeName, @PathVariable String destination, @PathVariable String orderBy, Model model) {
		List<DTO> dtos;
		switch(listSort) {
		case "ra":
			dtos= searchService.selectSomeRaDTOs(routeName, destination, orderBy, 0, 10);
			model.addAttribute("dtos",dtos);
			model.addAttribute("listSortKorean","휴게소");
			break;
		case "gs":
			dtos=searchService.selectSomeGsDTOs(routeName, destination, orderBy, 0, 10);
			model.addAttribute("dtos",dtos);
			model.addAttribute("listSortKorean","주유소");
			break;
		case "fm":
			dtos=searchService.selectSomeFmDTOs(routeName, destination, orderBy, 0, 10);
			model.addAttribute("dtos",dtos);			
			model.addAttribute("listSortKorean","음식메뉴");
			break;
		}
		model.addAttribute("listSort",listSort);
		model.addAttribute("routeName",routeName);
		model.addAttribute("destination",destination);
		model.addAttribute("orderBy",orderBy);
		return "/restarea/list";
	}
	
	@GetMapping("/rest_area/front")
	public String frontPage() {return "/front/front";}
}
