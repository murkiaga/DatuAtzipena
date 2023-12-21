package com.university.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.university.app.model.Student;
import com.university.app.repository.StudentRepository;
import com.university.app.repository.UniversityRepository;

@Controller
@RequestMapping("/bilatu")
public class BilaketaController {

	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private UniversityRepository uniRepo;
	
	@GetMapping({"/",""})
	public String bilatu(@RequestParam(value="q", defaultValue="0") String bilaketa, Model model) {
		List<Student> ikasleak = new ArrayList<>();
		ikasleak = studentRepo.findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCase(bilaketa, bilaketa);
		model.addAttribute("ikasleak", ikasleak);
		return "ikasleak/ikasleak";
	}
	
}
