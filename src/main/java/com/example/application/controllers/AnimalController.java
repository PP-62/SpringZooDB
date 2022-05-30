package com.example.application.controllers;

import com.example.application.entities.Animal;
import com.example.application.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class AnimalController {
    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private RationRepository rationRepository;
    @Autowired
    private SpecieRepository speciesRepository;
    @Autowired
    private AviaryRepository aviaryRepository;
    @Autowired
    private KeeperRepository keeperRepository;

    @GetMapping("/animals")
    public String searchAnimal(@RequestParam(name = "name", required = false, defaultValue = "")String name,
                               @RequestParam(name = "gender", required = false, defaultValue = "n") Character gender,
                               @RequestParam(name = "appeared_date", required = false, defaultValue = " ") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date appearedDate,
                               @RequestParam(name = "specie_id", required = false, defaultValue = "") Long specieId,
                               @RequestParam(name = "keeper_id", required = false, defaultValue = "") Long keeperId,
                               @RequestParam(name = "aviary_id", required = false, defaultValue = "") Long aviaryId,
                               @RequestParam(name = "ration_id", required = false, defaultValue = "") Long rationId,
                               Model model)
    {
        if(appearedDate==null){
        }
        Iterable<Animal> animals;
        animals = animalRepository.findAllByAll(name,gender,appearedDate,specieId,keeperId,aviaryId,rationId);
        Integer count = 0;
        for(Animal i: animals){
            count++;
        }
        model.addAttribute("count", count);
        model.addAttribute("animals", animals);
        model.addAttribute("aviaries", aviaryRepository.findAll());
        model.addAttribute("keepers", keeperRepository.findAll());
        model.addAttribute("species", speciesRepository.findAll());
        model.addAttribute("rations", rationRepository.findAll());
        System.out.println(animals);
        System.out.println(specieId);
        return "animals";
    }

    @PostMapping("/animals")
    public String addAnimal(@RequestParam(name = "name", required = false, defaultValue = "")String name,
                            @RequestParam(name = "gender", required = false, defaultValue = "n") Character gender,
                            @RequestParam(name = "appeared_date", required = false, defaultValue = " ") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date appearedDate,
                            @RequestParam(name = "specie_id", required = false, defaultValue = "") Long specieId,
                            @RequestParam(name = "keeper_id", required = false, defaultValue = "") Long keeperId,
                            @RequestParam(name = "aviary_id", required = false, defaultValue = "") Long aviaryId,
                            @RequestParam(name = "ration_id", required = false, defaultValue = "") Long rationId,
                            Model model){
        if(appearedDate==null){
            appearedDate = new Date();
        }
        Animal animal = new Animal(name,
            gender,
                appearedDate,
            aviaryRepository.findById(aviaryId).orElseThrow(),
            speciesRepository.findById(specieId).orElseThrow(),
            keeperRepository.findById(keeperId).orElseThrow(),
            rationRepository.findById(rationId).orElseThrow()
        );
        animalRepository.save(animal);
        return "redirect:/animals";
    }
    @PostMapping("/animals/update/{id}")
    public String editAnimal(@PathVariable(value = "id") long id,
                             @RequestParam(name = "name", required = false, defaultValue = "")String name,
                             @RequestParam(name = "gender", required = false, defaultValue = "n") Character gender,
                             @RequestParam(name = "appeared_date", required = false, defaultValue = " ") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date appearedDate,
                             @RequestParam(name = "specie_id", required = false, defaultValue = "") Long specieId,
                             @RequestParam(name = "keeper_id", required = false, defaultValue = "") Long keeperId,
                             @RequestParam(name = "aviary_id", required = false, defaultValue = "") Long aviaryId,
                             @RequestParam(name = "ration_id", required = false, defaultValue = "") Long rationId,
                             Model model){
        Animal animal = animalRepository.findById(id).orElseThrow();
        animal.setName(name);
        animal.setGender(gender);
        animal.setAppearedDate(appearedDate);
        animal.setSpecieId(specieId);
        animal.setSpecie(speciesRepository.findById(specieId).orElseThrow());
        animal.setKeeperId(keeperId);
        animal.setKeeper(keeperRepository.findById(keeperId).orElseThrow());
        animal.setAviaryId(aviaryId);
        animal.setAviary(aviaryRepository.findById(aviaryId).orElseThrow());
        animal.setRationId(rationId);
        animal.setRation(rationRepository.findById(rationId).orElseThrow());
        animalRepository.save(animal);
        return "redirect:/animals";
    }
    @PostMapping("/animals/delete/{id}")
    public String deleteAnimal(@PathVariable(value = "id") long id, Model model){
        animalRepository.deleteById(id);
        return "redirect:/animals";
    }
}

