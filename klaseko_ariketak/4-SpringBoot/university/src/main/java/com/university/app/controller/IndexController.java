package com.university.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.university.app.model.service.MyService;

@Controller
public class IndexController {
	
	@Autowired
	private MyService my_service;
	
	@GetMapping({"/", ""})
	public String index(){
		return "index";
	}
}
