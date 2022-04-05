package com.Hopur26.D.shooter.controllers;


import com.Hopur26.D.shooter.Persistence.Entities.KeyBinds;
import com.Hopur26.D.shooter.Persistence.Entities.Last5Games;
import com.Hopur26.D.shooter.Persistence.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.Hopur26.D.shooter.Services.UserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicLong;


@Controller
public class UserController {
    // This controller is only for the user updates
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(User user, BindingResult result, Model model, HttpSession session){
        System.out.println("TARARAA");
        if(result.hasErrors()){
            return "redirect:/login";
        }
        // We send in the user and verify that the password is correct in the service, then we get the user back
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
        // Here we check if the username exists, if not then the account gets created
        User exists = userService.findByUsername(user.getUsername());
        if(exists == null){
            userService.create(user,keyBinds,last5Games);
            return "redirect:/login";
        }
        return "redirect:/createAccount";
    }

    //The @RequestParam variables in the function call get variables from the HTML
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUserPOST(@RequestParam(value="newName") String newName,
                                 @RequestParam(value="b0") int b0,
                                 @RequestParam(value="b1") int b1,
                                 @RequestParam(value="b2") int b2,
                                 @RequestParam(value="b3") int b3,
                                 @RequestParam(value="b4") int b4,
                                 User user, Model model, BindingResult result, HttpSession session){
        user = (User) session.getAttribute("LoggedInUser");
        userService.setKeys(user, b0, b1, b2, b3, b4);
        userService.setName(user,newName);
        userService.save(user);
        session.setAttribute("LoggedInUser",user);
        return "redirect:/main";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadPOST(@RequestParam(value="score") String score, User user, Model model, BindingResult result, HttpSession session){
        user = (User) session.getAttribute("LoggedInUser");
        if(result.hasErrors()){
            return "redirect:/game";
        }
        userService.addGame(user,score);
        System.out.println(score);
        System.out.println(user.getLast5Games());
        userService.save(user);
        session.setAttribute("LoggedInUser",user);
        return "redirect:/game";
    }
}
