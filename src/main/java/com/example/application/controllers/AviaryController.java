package com.example.application.controllers;

import com.example.application.entities.Aviary;
import com.example.application.repositories.AviaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AviaryController {
    @Autowired
    private AviaryRepository aviaryRepository;


    @GetMapping("/aviaries")
    public String searchAviary(@RequestParam(name = "type", required = false, defaultValue = "")String type,
                               @RequestParam(name = "location", required = false, defaultValue = "") String location,
                               Model model)
    {
        Iterable<Aviary> aviaries;

        if (type.isEmpty()){
            if (location.isEmpty()){
                aviaries = aviaryRepository.findAll();
            }else{
                aviaries = aviaryRepository.findByLocation(location);
            }
        }else{
            if (location.isEmpty()){
                aviaries = aviaryRepository.findByType(type);
            }else{
                aviaries = aviaryRepository.findByTypeAndLocation(type, location);
            }
        }

        model.addAttribute("aviaries", aviaries);
        return "aviaries";
    }

    @PostMapping("/aviaries")
    public String addAviary(@RequestParam(name = "type") String add_type,
                            @RequestParam(name = "location") String add_location,
                            Model model){
        Aviary aviary = new Aviary(add_type,add_location);
        aviaryRepository.save(aviary);
        return "redirect:/aviaries";
    }

    @PostMapping("/aviaries/update/{id}")
    public String editAviary(@PathVariable(value = "id") long id,
                           @RequestParam(name = "type") String type,
                           @RequestParam(name = "location") String location,  Model model){
        Aviary aviary = aviaryRepository.findById(id).orElseThrow();
        aviary.setLocation(location);
        aviary.setType(type);
        aviaryRepository.save(aviary);
        return "redirect:/aviaries";
    }

    @PostMapping("/aviaries/delete/{id}")
    public String deleteAviary(@PathVariable(value = "id") long id, Model model){
        aviaryRepository.deleteById(id);
        return "redirect:/aviaries";
    }


}
