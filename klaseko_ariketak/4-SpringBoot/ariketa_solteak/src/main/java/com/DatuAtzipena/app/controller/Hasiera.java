package com.DatuAtzipena.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Hasiera {

	@GetMapping({"/hasiera"})
	public String hasiera() {
		return "hasiera";
	}
	
	@GetMapping({"", "/"})
	public String erabiltzailea(@RequestParam int erabiltzailea, Model model) {
		model.addAttribute("erabiltzailea", erabiltzailea); 
		return "erabiltzaile_profila.html";
	}
	
	@GetMapping("/enserio")
	public String hasiera2() {
		return "redirect:/bitcoin/enserio";
		//return "forward:/bitcoin/enserio";
	}
	
	
}
