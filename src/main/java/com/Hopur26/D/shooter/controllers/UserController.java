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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUserPOST(@RequestParam(value="myArray[]") Integer[] test, User user, Model model, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            return "redirect:/updateUser";
        }
        System.out.println(test);
        //userService.setName(user,test);
        userService.save(user);
        return "redirect:/main";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadPOST(@RequestParam(value="score") String score, User user, Model model, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            //return "redirect:/updateUser";
        }
        System.out.println(score);
        //userService.addGame(user,score);
        //userService.setName(user,test);
        //userService.save(user);
        //return "redirect:/";
        return "redirect:/game";
    }



}
