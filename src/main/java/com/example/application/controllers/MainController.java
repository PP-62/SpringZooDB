package com.example.application.controllers;

import com.example.application.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private RationRepository animalRationRepository;
	@Autowired
	private SpecieRepository animalSpeciesRepository;
	@Autowired
	private AviaryRepository aviaryRepository;
	@Autowired
	private KeeperRepository keeperRepository;

	@GetMapping("/enter")
	public String enter(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "enter";
	}

}
