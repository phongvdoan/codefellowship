package com.phongvdoan.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome(Principal p, Model m) {
        if (p != null) {
            m.addAttribute("principle",p.getName());
        } else if(p == null){
            m.addAttribute("principle","User");
        }
        return "home";
    }

    @GetMapping("/error")
    public String getError(){
        return "error";
    }
}
