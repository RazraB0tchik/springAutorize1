package com.makarproject.lessonsletscod.controller;

import com.makarproject.lessonsletscod.entity.Role;
import com.makarproject.lessonsletscod.entity.User;
import com.makarproject.lessonsletscod.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

   @Autowired
   PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
        public String registration(User user){
            return "registration";
        }
    @PostMapping(value = "/registration")
        public String addUser(@Valid User user, BindingResult bindingResult, Model model){
        User userfromDb = userRepo.findByUsername(user.getUsername());
        if(userfromDb != null){
            return "registration";
        }
        if(bindingResult.hasErrors()==true){ //Binding result хранит все ошибки класса uSer
//            model.addAttribute("Errore", bindingResult.hasErrors());
            return "registration";
        }
        if(bindingResult.hasErrors()==false) {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            user.setPassword(passwordEncoder.encode(user.getPassword())); //шифруем наши пароли на этапе создания пользователя
            userRepo.save(user);
            return "login";
        }
        return "redirect:/login";
    }
}
