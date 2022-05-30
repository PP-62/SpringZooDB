package com.example.application.controllers;

import com.example.application.entities.Aviary;
import com.example.application.entities.Keeper;
import com.example.application.repositories.AviaryRepository;
import com.example.application.repositories.KeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class KeeperController {
    @Autowired
    private KeeperRepository keeperRepository;


    @GetMapping("/keepers")
    public String searchKeeper(@RequestParam(name = "name", required = false, defaultValue = "")String name,
                               @RequestParam(name = "rank", required = false, defaultValue = "") String rank,
                               Model model)
    {
        Iterable<Keeper> keepers;

        if (name.isEmpty()){
            if (rank.isEmpty()){
                keepers = keeperRepository.findAll();
            }else{
                keepers = keeperRepository.findByRank(rank);
            }
        }else{
            if (rank.isEmpty()){
                keepers = keeperRepository.findByName(name);
            }else{
                keepers = keeperRepository.findByNameAndRank(name, rank);
            }
        }

        model.addAttribute("keepers", keepers);
        return "keepers";
    }

    @PostMapping("/keepers")
    public String addKeeper(@RequestParam(name = "name") String name,
                            @RequestParam(name = "rank") String rank,
                            @RequestParam(name = "birthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthday,
                            Model model){
        Keeper keeper = new Keeper(name,rank,birthday);
        keeperRepository.save(keeper);
        return "redirect:/keepers";
    }

    @PostMapping("/keepers/update/{id}")
    public String editKeeper(@PathVariable(value = "id") long id,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "rank") String rank,
                             @RequestParam(name = "birthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthday,
                             Model model){
        Keeper keeper = keeperRepository.findById(id).orElseThrow();
        keeper.setName(name);
        keeper.setRank(rank);
        keeper.setBirthday(birthday);
        keeperRepository.save(keeper);
        return "redirect:/keepers";
    }

    @PostMapping("/keepers/delete/{id}")
    public String deleteKeeper(@PathVariable(value = "id") long id, Model model){
        keeperRepository.deleteById(id);
        return "redirect:/keepers";
    }


}
