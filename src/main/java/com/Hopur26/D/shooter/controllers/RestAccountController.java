package com.Hopur26.D.shooter.controllers;


import com.Hopur26.D.shooter.Services.AccountService;
import com.Hopur26.D.shooter.Services.MainService;
import com.Hopur26.D.shooter.Services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import com.Hopur26.D.shooter.Persistence.Entities.KeyBinds;
import com.Hopur26.D.shooter.Persistence.Entities.Last5Games;import com.Hopur26.D.shooter.Persistence.Entities.Account;
import com.Hopur26.D.shooter.Persistence.Entities.Chat;
import com.Hopur26.D.shooter.Persistence.Entities.News;

import javax.servlet.http.HttpSession;

@RestController
public class RestAccountController {
    private AccountService accountService;
    private ArrayList<String> ussser;
    private ArrayList<String> chat;

    @Autowired
    public RestAccountController(AccountService accountService){ this.accountService = accountService;}

    @RequestMapping("/account/test/test")
    public ArrayList<String> login2(){
        ussser.add("tilraun1");
        return ussser;
    }

    @RequestMapping("/account/create")
    public Account create(@RequestParam(value="username", defaultValue = "") String username,@RequestParam(value="password", defaultValue = "") String password){
        Account account = new Account(username, password);

        //check if user exist.
        Account exist = accountService.login(account);
        if(exist != null) {
            return null;
        }
        Account newAccount = accountService.create(account);

        return newAccount;
    }

    @RequestMapping("/account/login")
    public Account login(@RequestParam(value="username", defaultValue = "") String username,@RequestParam(value="password", defaultValue = "") String password){
        Account user = new Account(username, password);

        Account exist = accountService.login(user);
        if(exist != null){
            return exist;
        }
        return null;
    }

    @RequestMapping("/account/setadmin")
    public Account admin(@RequestParam(value="username", defaultValue = "") String username){
        Account exist = accountService.findByUsername(username);
        if(exist != null){
            exist.setAdmin(true);
            accountService.save(exist);
            return exist;
        }
        return null;
    }

    @RequestMapping("/account/setmuted")
    public Account muted(@RequestParam(value="username", defaultValue = "") String username,
                      @RequestParam(value="mute", defaultValue = "") String mute){
        Account exist = accountService.findByUsername(username);
        if(exist != null){
            boolean admin = exist.getAdmin();
            if(admin){
                Account exist2 = accountService.findByUsername(mute);
                if(exist2 != null){
                    exist2.setMuted(true);
                    accountService.save(exist2);
                    return exist2;
                }
            }
            return null;
        }
        return null;
    }

    @RequestMapping("/account/findAll")
    public List<Account> findAll(){
        List<Account> exist = accountService.findAll();
        if(exist != null){
            return exist;
        }
        return null;

    }

    @RequestMapping("/account/deleteaccount")
    public Boolean delete(@RequestParam(value="admin", defaultValue = "") String admin,
                          @RequestParam(value="account", defaultValue = "") String account){
        Account exist = accountService.findByUsername(admin);
        if(exist != null){
            boolean isAdmin = exist.getAdmin();
            if(isAdmin){
                Account exist2 = accountService.findByUsername(account);
                if(exist2 != null){
                    accountService.delete(exist2);
                    return true;
                }
            }
            return null;
        }
        return null;
    }
}