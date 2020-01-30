package com.phongvdoan.codefellowship.controllers;

import com.phongvdoan.codefellowship.models.ApplicationUser;
import com.phongvdoan.codefellowship.models.ApplicationUserRepository;
import com.phongvdoan.codefellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

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

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/users/{id}")
    public String getHome(@PathVariable long id, Principal p, Model m) {
        if (p != null) {
            m.addAttribute("principle",p.getName());
        } else if(p == null){
            m.addAttribute("principle","User");
        }
        ApplicationUser applicationUser = applicationUserRepository.findById(id).get();
        m.addAttribute("username", applicationUser.getUsername());
        m.addAttribute("firstName", applicationUser.getFirstName());
        m.addAttribute("lastName", applicationUser.getLastName());
        m.addAttribute("dateOfBirth", applicationUser.getDateofBirth());
        m.addAttribute("bio", applicationUser.getBio());

        return "users";
    }

    @GetMapping("/myprofile")
    public String getProfile(Principal p, Model m) {
        if (p != null) {
            m.addAttribute("principle",p.getName());
        } else if(p == null){
            m.addAttribute("principle","User");
        }
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("username", applicationUser.getUsername());
        m.addAttribute("firstName", applicationUser.getFirstName());
        m.addAttribute("lastName", applicationUser.getLastName());
        m.addAttribute("dateOfBirth", applicationUser.getDateofBirth());
        m.addAttribute("bio", applicationUser.getBio());
        m.addAttribute("id", applicationUser.id);
        m.addAttribute("user", applicationUser);
        return "profile";
    }
}
