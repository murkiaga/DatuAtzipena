package com.university.app.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.university.app.FileUploadUtil;
import com.university.app.model.Student;
import com.university.app.model.University;
import com.university.app.repository.StudentRepository;
import com.university.app.repository.UniversityRepository;

@Controller
@RequestMapping("/unibertsitateak")
public class UniversityController {
	
	@Autowired
	private UniversityRepository uniRepository;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@GetMapping({"/", "", "/unibertsitate_zerrenda"})
	public String unibertsitateak(Model model) {
		List<University> universities = uniRepository.findAll();
		model.addAttribute("unibertsitateak", universities);
		return "unibertsitateak/unibertsitateak";
	}
	
	@GetMapping("/unibertsitatea/new")
	public String unibertsitatea_new(Model model) {
		University uni = new University();
		model.addAttribute("unibertsitatea", uni);
		return "unibertsitateak/unibertsitateaForm";
	}
	
	@PostMapping("/unibertsitatea/new/submit")
	public String unibertsitatea_submit(@ModelAttribute University unibertsitatea, @RequestParam("image") MultipartFile imageFile) {
		String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
		unibertsitatea.setIrudia(fileName);
		uniRepository.save(unibertsitatea);
		
		try {
			String uploadDir = "university-photos/" + unibertsitatea.getId();
			FileUploadUtil.saveFile(uploadDir, fileName, imageFile);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
		
		return "redirect:/unibertsitateak";
	}
	
	@GetMapping("/unibertsitatea/edit/{id}")
	public String unibertsitatea_edit(@PathVariable int id, Model model) {
		model.addAttribute("ikasleak", studentRepo.findAll());
		model.addAttribute("unibertsitatea", uniRepository.getReferenceById(id));
		return "unibertsitateak/unibertsitateaForm";
	}
	
	@GetMapping("/unibertsitatea/delete/{id}")
	public String unibertsitatea_delete(@PathVariable int id) {
		uniRepository.deleteById(id);
		return "redirect:/unibertsitateak";
	}
	
	

}
