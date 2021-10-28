package com.Hopur26.D.shooter.controllers;


import com.Hopur26.D.shooter.storage.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.Hopur26.D.shooter.Persistance.LoginService;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }


    @RequestMapping("/")
    public String homePage(){
        return "select";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "login";
        }
        User exists = loginService.login(user);
        if(exists != null){
            session.setAttribute("LoggedInAccount", exists);
            model.addAttribute("LoggedInAccount", exists);
            return "redirect:/main";
        }
        return "login";
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
    public String createAccountGET(User user){
        return "login";
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public String createAccountPOST(User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "redirect:/createAccount";
        }
        User exists = loginService.findByUsername(user.getUsername());
        if(exists == null){
            loginService.save(user);
            model.addAttribute("LoggedInAccount", user);
        }
        return "redirect:/main";
    }

}
