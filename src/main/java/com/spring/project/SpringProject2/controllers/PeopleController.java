package com.spring.project.SpringProject2.controllers;

import com.spring.project.SpringProject2.models.Person;
import com.spring.project.SpringProject2.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/signup")
    public String signup(@ModelAttribute("person") Person person){
        return "people/signup";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("person") Person person){
        return "people/login";
    }

    @GetMapping("/{id}")
    public String profile(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/profile";
    }

//    @GetMapping("/login/confirm")
//    public String check(@ModelAttribute("person") Person person) {
//
//        Person person1 = peopleService.findOne(person.getEmail());
//        if(person1 == null){
//            person.setEmail("");
//            return "people/login";
//        }
//        if(!person1.getPassword().equals(person.getPassword())){
//            person.setPassword("");
//            return "people/login";
//        }
//        return "redirect:/rides/" + person1.getId();
//    }

    @PostMapping("/signup/confirm")
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "people/signup";

        peopleService.save(person);
//        return "redirect:/rides/" + person.getId();
        return "redirect:/login";

    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "people/profile";

        peopleService.update(id, person);
        return "redirect:/people/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people/signup";
    }
}
