package com.example.application.controllers;

import com.example.application.entities.Specie;
import com.example.application.repositories.SpecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SpecieController {
    @Autowired
    private SpecieRepository specieRepository;


    @GetMapping("/species")
    public String searchAnimalSpecies(@RequestParam(name = "type", required = false, defaultValue = "")String type,
                                      @RequestParam(name = "group", required = false, defaultValue = "")String group,
                                      @RequestParam(name = "lifeStyle", required = false, defaultValue = "")String lifeStyle,
                                      @RequestParam(name = "status", required = false, defaultValue = "") String status,
                                      Model model)
    {
        Iterable<Specie> species;
        species = specieRepository.findAllByAll(type, group, lifeStyle, status);

        model.addAttribute("species", species);
        return "species";
    }

    @PostMapping("/species")
    public String addAnimalSpecies(@RequestParam(name = "type", required = false, defaultValue = "")String type,
                                   @RequestParam(name = "group", required = false, defaultValue = "")String group,
                                   @RequestParam(name = "lifeStyle", required = false, defaultValue = "")String lifeStyle,
                                   @RequestParam(name = "status", required = false, defaultValue = "") String status,
                                   Model model){
        Specie specie = new Specie(type,group,lifeStyle,status);
        specieRepository.save(specie);
        return "redirect:/species";
    }

    @PostMapping("/species/update/{id}")
    public String editAnimalSpecies(@PathVariable(value = "id") long id,
                                    @RequestParam(name = "type", required = false, defaultValue = "")String type,
                                    @RequestParam(name = "group", required = false, defaultValue = "")String group,
                                    @RequestParam(name = "lifeStyle", required = false, defaultValue = "")String lifeStyle,
                                    @RequestParam(name = "status", required = false, defaultValue = "") String status,
                                    Model model){
        Specie specie = specieRepository.findById(id).orElseThrow();
        specie.setType(type);
        specie.setGroup(group);
        specie.setLifeStyle(lifeStyle);
        specie.setStatus(status);
        specieRepository.save(specie);
        return "redirect:/species";
    }

    @PostMapping("/species/delete/{id}")
    public String deleteAnimalSpecies(@PathVariable(value = "id") long id, Model model){
        specieRepository.deleteById(id);
        return "redirect:/species";
    }


}
