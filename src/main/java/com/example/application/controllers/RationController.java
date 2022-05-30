package com.example.application.controllers;

import com.example.application.entities.Ration;
import com.example.application.repositories.RationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RationController {
    @Autowired
    private RationRepository animalRationRepository;


    @GetMapping("/rations")
    public String searchAnimalRations(@RequestParam(name = "type", required = false, defaultValue = " ")String type,
                               @RequestParam(name = "feedingCount", required = false, defaultValue = "0") Short feedingCount,
                               Model model)
    {
        Iterable<Ration> rations;
        rations = animalRationRepository.findAllByAll(type, feedingCount);
        model.addAttribute("rations", rations);
        return "rations";
    }

    @PostMapping("/rations")
    public String addAnimalRations(@RequestParam(name = "type") String add_type,
                            @RequestParam(name = "feedingCount") Short feedingCount,
                            Model model){
        Ration ration = new Ration(add_type,feedingCount);
        animalRationRepository.save(ration);
        return "redirect:/rations";
    }

    @PostMapping("/rations/update/{id}")
    public String editAnimalRations(@PathVariable(value = "id") long id,
                             @RequestParam(name = "type") String type,
                             @RequestParam(name = "feedingCount") Short feedingCount,
                             Model model){
        Ration ration = animalRationRepository.findById(id).orElseThrow();
        ration.setFeedingCount(feedingCount);
        ration.setType(type);
        animalRationRepository.save(ration);
        return "redirect:/rations";
    }

    @PostMapping("/rations/delete/{id}")
    public String deleteAnimalRations(@PathVariable(value = "id") long id, Model model){
        animalRationRepository.deleteById(id);
        return "redirect:/rations";
    }


}
