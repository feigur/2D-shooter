package com.Hopur26.D.shooter.controllers;

import com.Hopur26.D.shooter.Persistance.LoginService;
import com.Hopur26.D.shooter.Persistance.MainService;
import com.Hopur26.D.shooter.storage.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainGet(User user){
        return "main";
    }



    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settingsGet(User user){
        return "settings";
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String historyGet(User user){
        return "history";
    }

}
