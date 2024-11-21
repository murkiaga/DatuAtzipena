package com.DatuAtzipena.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bitcoin")
public class BitcoinController {
	
	
	//localhost?id=id_balioa
	@GetMapping({"", "/"})
	public String NAHIDOTENA(@RequestParam(name = "id", required = false, defaultValue = "1") int id, Model model) {
	    model.addAttribute("funtzioa", "NAHIDOTENA");
	    model.addAttribute("id", id); 
	    return "bitcoin/hasiera";
	}
	
	
	
	//localhost/id_balioa
	@GetMapping({"/{idea}", "/{idea}/"})
	public String NAHIDOTENA4(@PathVariable int idea, Model model) {
	    model.addAttribute("funtzioa", "NAHIDOTENA4");
	    model.addAttribute("id", idea); 
	    return "bitcoin/hasiera";
	}
	
	@GetMapping({"/admin"})
	public String NAHIDOTENA2(Model model) {
		model.addAttribute("funtzioa", "NAHIDOTENA2");
		return "bitcoin/hasiera";
	}
	
	@GetMapping({"/enserio"})
	public String NAHIDOTENA3(Model model) {
		model.addAttribute("funtzioa", "NAHIDOTENA3");
		return "bitcoin/hasiera";
	}
}
