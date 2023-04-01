package com.spring.project.SpringProject2.controllers;

import com.spring.project.SpringProject2.models.Person;
import com.spring.project.SpringProject2.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

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

    @GetMapping("/home")
    public String home(){
        return "people/home";
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
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }

    @GetMapping("/login/confirm")
    public String check(@ModelAttribute("person") Person person) {

        Person person1 = peopleService.findOne(person.getEmail());
        if(person1 == null){
            person.setEmail("");
            return "people/login";
        }
        if(!person1.getPassword().equals(person.getPassword())){
            person.setPassword("");
            return "people/login";
        }
        return "redirect:/people/" + person1.getId();
    }

    @PostMapping("/signup/confirm")
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "people/signup";

        peopleService.save(person);
        return "redirect:/people/" + person.getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
