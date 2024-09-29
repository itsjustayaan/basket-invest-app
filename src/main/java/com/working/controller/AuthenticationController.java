package com.working.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {

   
    @RequestMapping("/login")
    public String login(Principal principal, Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("errorMsg", "Your username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("msg", "You have been logged out successfully.");
        }
        
//        System.out.println(principal.getName());
        
        return "login"; 
    }


    @GetMapping("/logoutPage")
    public String logoutPage(Authentication authentication, Model model) {
        String username = "";
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        model.addAttribute("username", username);
        return "logout";  
    }
    
    
}