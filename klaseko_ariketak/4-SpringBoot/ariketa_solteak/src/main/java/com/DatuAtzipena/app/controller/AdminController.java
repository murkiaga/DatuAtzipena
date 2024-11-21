package com.DatuAtzipena.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/{erabiltzailea}")
	public String hasiera(@PathVariable int erabiltzailea, Model model) {
		model.addAttribute("erabiltzailea", erabiltzailea);
		return "erabiltzaile_profila";
	}
	
	@GetMapping({"", "/"})
	public String hasiera2() {
		return "redirect:/admin/1";
	}
}
