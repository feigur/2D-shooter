package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import storage.Entities.Account;

@Controller
public class MainMenuContoller {
    private Account account;

    @Autowired
    public HomeController(Account account){
        this.account = account;
    }
}

//doesthiswork -abj34
