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

        String name = peopleService.findOne(id).getName();
        model.addAttribute("name", name);
        model.addAttribute("id", id);
        model.addAttribute("rides", rideService.findAll());
        return "rides/home";
    }

    @GetMapping("/createRide/{id_v}")
    public String createRide(@ModelAttribute("ride") Ride ride,
                             @PathVariable("id_v") int id,
                             Model model){

        model.addAttribute("id_v", id);
        return "/rides/createRide";
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
        ride.setOwner(person);
        rideService.save(ride);
        return "redirect:/rides/" + id;
    }

    @PostMapping("/book")
    public String bookRide(@RequestParam("id_person") int id_person,
                           @RequestParam("id_ride") int id_ride){
        System.out.println(id_ride + " " + id_person + "llllllllll");
        Person person = peopleService.findOne(id_person);
        Ride ride = rideService.findOne(id_ride);

        person.getBookedRides().add(ride);
        ride.getPassengers().add(person);

        rideService.save(ride);
        return "redirect:/people";
    }
}
