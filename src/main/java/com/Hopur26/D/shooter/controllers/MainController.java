package com.Hopur26.D.shooter.controllers;

import com.Hopur26.D.shooter.Persistance.MainService;
import com.Hopur26.D.shooter.storage.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    private MainService mainService;

    @Autowired
    public MainController(MainService mainService){
        this.mainService = mainService;
    }

    @RequestMapping("/")
    public String homePage(){
        return "select";
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
    public String createAccountGET(User user){
        return "createaccount";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user){
        return "login";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainGet(User user){
        return "main";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settingsGet(HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            model.addAttribute("LoggedInUser", sessionUser);
            return "settings";
        }
        return "redirect:/Main";
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String historyGet(User user){
        return "history";
    }



}
