package com.university.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.university.app.model.Student;
import com.university.app.model.University;
import com.university.app.repository.StudentRepository;
import com.university.app.repository.UniversityRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ikasleak")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private UniversityRepository uniRepo;

	@GetMapping({"/", "", "/ikasle_zerrenda"})
	public String ikasleak(Model model) {
		List<Student> students = studentRepo.findAll();
		model.addAttribute("ikasleak", students);
		return "ikasleak/ikasleak";
	}
	
	@GetMapping("/ikaslea/{ikasle_id}")
	public String ikaslea(@PathVariable int ikasle_id, Model model) {
		if (studentRepo.existsById(ikasle_id)) {
			Student student = studentRepo.getReferenceById(ikasle_id);
			model.addAttribute("ikaslea", student);
		}
		return "ikasleak/ikaslea";
	}
	
	@GetMapping("/ikaslea/new")
	public String ikaslea_new(Model model) {
		Student ikaslea = new Student();
		model.addAttribute("unibertsitateak", uniRepo.findAll());
		model.addAttribute("ikaslea", ikaslea);
		return "ikasleak/ikasleaForm";
	}
	
	@PostMapping("/ikaslea/new/submit")
	public String ikaslea_submit(@ModelAttribute Student ikaslea) {
		studentRepo.save(ikaslea);
		return "redirect:/ikasleak";
	}
	
	@GetMapping("/ikaslea/edit/{id}")
	public String ikaslea_edit(@PathVariable int id, Model model) {
		model.addAttribute("unibertsitateak", uniRepo.findAll());
		model.addAttribute("ikaslea", studentRepo.findById(id));
		return "ikasleak/ikasleaForm";
	}
	
	@GetMapping("/ikaslea/delete/{id}")
	public String ikaslea_delete(@PathVariable int id) {
		studentRepo.deleteById(id);
		return "redirect:/ikasleak";
	}
	
	@GetMapping("/ikaslea/desmatrikulatu/{id}")
	public String ikaslea_desmatrikulatu(@PathVariable int id) {
		Student student = studentRepo.getReferenceById(id);
		student.desmatrikulatu();
		studentRepo.save(student);
		return "redirect:/unibertsitateak";
	}
	

}
