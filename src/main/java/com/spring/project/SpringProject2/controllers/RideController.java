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

    @GetMapping("/createRide/{id}")
    public String createRide(@ModelAttribute("ride") Ride ride,
                             @PathVariable("id") int id,
                             Model model){

        model.addAttribute("id", id);
        return "/rides/createRide";
    }

    @PostMapping("/confirm/{id}")
    public String create(@ModelAttribute("ride") @Valid Ride ride,
                         BindingResult bindingResult, Model model,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()){
            model.addAttribute("id", id);
            return "rides/createRide";
        }

        Person person = peopleService.findOne(id);
        ride.setOwner(person);

        rideService.save(ride);
        return "redirect:/people";
    }
}
