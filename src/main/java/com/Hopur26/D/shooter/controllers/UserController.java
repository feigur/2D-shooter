package com.Hopur26.D.shooter.controllers;


import com.Hopur26.D.shooter.storage.Entities.KeyBinds;
import com.Hopur26.D.shooter.storage.Entities.Last5Games;
import com.Hopur26.D.shooter.storage.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.Hopur26.D.shooter.Persistance.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "redirect:/login";
        }
        User exists = userService.login(user);
        if(exists != null){
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            return "redirect:/main";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public String createAccountPOST(User user, KeyBinds keyBinds, Last5Games last5Games, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "redirect:/createAccount";
        }
        User exists = userService.findByUsername(user.getUsername());
        if(exists == null){
            userService.create(user,keyBinds,last5Games);
            return "redirect:/login";
        }
        return "redirect:/createAccount";
    }

    /*@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUserPOST(BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "redirect:/updateUser";
        }
        User user = model.getAttribute("LoggedInUser");
        model.getAttribute("keys");
        return "redirect:/main";
    }*/



}
