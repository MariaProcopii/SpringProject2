package com.spring.project.SpringProject2.controllers;

import com.spring.project.SpringProject2.models.Person;
import com.spring.project.SpringProject2.models.Ride;
import com.spring.project.SpringProject2.services.PeopleService;
import com.spring.project.SpringProject2.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/rides")
public class RideController {

    private final RideService rideService;
    private final PeopleService peopleService;

    @Autowired
    public RideController(RideService rideService, PeopleService peopleService) {
        this.rideService = rideService;
        this.peopleService = peopleService;
    }

    @GetMapping("/{id}")
    public String home(@PathVariable("id") int id, Model model){

        Person person = peopleService.findOne(id);
        model.addAttribute("name", person.getName());
        model.addAttribute("id", id);
        model.addAttribute("rides", rideService.findNotBooked(person));

        return "rides/home";
    }

    @GetMapping("/createRide/{id_v}")
    public String createRide(@ModelAttribute("ride") Ride ride,
                             @PathVariable("id_v") int id,
                             Model model){

        model.addAttribute("id_v", id);
        return "/rides/createRide";
    }

    @GetMapping("/currentRide/{id}")
    public String currentRide(@PathVariable("id") int id,
                             Model model){

        model.addAttribute("person", peopleService.findOne(id));
        return "/rides/currentRide";
    }

    @PostMapping("/confirm/{id_v}")
    public String create(@ModelAttribute("ride") @Valid Ride ride,
                         BindingResult bindingResult, Model model,
                         @PathVariable("id_v") int id) {

        if (bindingResult.hasErrors()){
            model.addAttribute("id_v", id);
            return "rides/createRide";
        }

        Person person = peopleService.findOne(id);
        rideService.createRide(person, ride);
        
        return "redirect:/rides/" + id;
    }

    @PostMapping("/book")
    public String bookRide(@RequestParam("id_person") int id_person,
                           @RequestParam("id_ride") int id_ride){

        Person person = peopleService.findOne(id_person);
        Ride ride = rideService.findOne(id_ride);
        rideService.bookRide(person, ride);

        return "redirect:/rides/" + id_person;
    }
}
