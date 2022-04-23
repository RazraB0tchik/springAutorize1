package com.makarproject.lessonsletscod.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    @GetMapping("/allUser")
    public String getAll(Authentication authentication){
        return ("index");
    }

    @GetMapping("/logout")
    public String logout(){
        return ("login");
    }

}
