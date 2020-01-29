package com.phongvdoan.codefellowship.controllers;

import com.phongvdoan.codefellowship.models.ApplicationUser;
import com.phongvdoan.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String getSignUp(){
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView createNewApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio){

        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);

        applicationUserRepository.save(newUser);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/users/{id}")
    public String getHome(@PathVariable long id, Principal p, Model m) {
        if (p != null) {
            m.addAttribute("principle", p.getName());
        }
        ApplicationUser applicationUser = applicationUserRepository.findById(id).get();
        m.addAttribute("username", applicationUser.getUsername());
        m.addAttribute("firstName", applicationUser.getFirstName());
        m.addAttribute("lastName", applicationUser.getLastName());
        m.addAttribute("dateOfBirth", applicationUser.getDateofBirth());
        m.addAttribute("bio", applicationUser.getBio());
        return "users";
    }
}
