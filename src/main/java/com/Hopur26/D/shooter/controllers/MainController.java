package com.Hopur26.D.shooter.controllers;

import com.Hopur26.D.shooter.Services.MainService;
import com.Hopur26.D.shooter.Persistence.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    // This controller is only used for get requests
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
    public String mainGet(HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            //Here we set the user as a "global" variable for the session
            model.addAttribute("LoggedInUser", sessionUser);
            return "main";
        }
        return "main";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settingsGet(User user, HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            // Here we add the username and keys to the html model
            model.addAttribute("LoggedInUser", sessionUser);
            model.addAttribute("keys",sessionUser.getKeys());
            return "settings";
        }
        return "redirect:/Main";
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String historyGet(User user, HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            // Here we add the username and keys to the html model
            model.addAttribute("LoggedInUser", sessionUser);
            model.addAttribute("last5Games",sessionUser.getLast5Games());
            return "history";
        }
        return "redirect:/Main";
    }

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String gameGet(User user, HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null) {
            // Here we add the username and keys to the html model
            model.addAttribute("LoggedInUser", sessionUser);
            model.addAttribute("keys",sessionUser.getKeys());
            return "game";
        }
        return "redirect:/Main";
    }


}
