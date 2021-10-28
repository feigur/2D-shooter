package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.LoginService;
import storage.Entities.Account;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }


    @RequestMapping("/")
    public String homePage(Model model){
        return "select";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(Account account){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(Account account, BindingResult result, Model model, HttpSession session){
        model.addAttribute("account", account);
        if(result.hasErrors()){
            return "login";
        }
        Account exists = loginService.login(account);
        if(exists != null){
            session.setAttribute("LoggedInAccount", exists);
            model.addAttribute("LoggedInAccount", exists);
            return "redirect:/main";
        }
        return "login";
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
    public String createAccountGET(Account account){
        return "login";
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public String createAccountPOST(Account account, BindingResult result, Model model){
        if(result.hasErrors()){
            return "redirect:/createAccount";
        }
        Account exists = loginService.findByAccountname(account.getAccountname());
        if(exists == null){
            loginService.save(account);
            model.addAttribute("LoggedInAccount", account);
        }
        return "redirect:/main";
    }
}
