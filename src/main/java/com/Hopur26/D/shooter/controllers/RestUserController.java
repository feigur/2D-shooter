package com.Hopur26.D.shooter.controllers;


import com.Hopur26.D.shooter.Services.UserService;
import com.Hopur26.D.shooter.Services.MainService;
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
import com.Hopur26.D.shooter.Persistence.Entities.Last5Games;
import com.Hopur26.D.shooter.Persistence.Entities.User;
import com.Hopur26.D.shooter.Persistence.Entities.Chat;

import javax.servlet.http.HttpSession;

@RestController
public class RestUserController {
    private UserService userService;
    private ArrayList<String> ussser;
    private ArrayList<String> chat;
    @Autowired
    public RestUserController(UserService userService){ this.userService = userService;}

    @RequestMapping("/user/test/test")
    public ArrayList<String> login2(){
        ussser.add("tilraun1");
        return ussser;
    }

    @RequestMapping("/user/create")
    public User create(@RequestParam(value="username", defaultValue = "") String username,@RequestParam(value="password", defaultValue = "") String password){
        User user = new User(username, password);

        //check if user exist.
        User exist = userService.login(user);
        if(exist != null) {
            return null;
        }
        KeyBinds keys = new KeyBinds();
        Last5Games games = new Last5Games();
        User newUser = userService.create(user, keys, games);

        return newUser;
    }

    @RequestMapping("/user/login")
    public User login(@RequestParam(value="username", defaultValue = "") String username,@RequestParam(value="password", defaultValue = "") String password){
        User user = new User(username, password);

        User exist = userService.login(user);
        if(exist != null){
            return exist;
        }
        return null;
    }

    @RequestMapping("/user/setadmin")
    public User admin(@RequestParam(value="username", defaultValue = "") String username){
        User exist = userService.findByUsername(username);
        if(exist != null){
            exist.setAdmin(true);
            userService.save(exist);
            return exist;
        }
        return null;
    }

    @RequestMapping("/user/setmuted")
    public User admin(@RequestParam(value="username", defaultValue = "") String username,
                      @RequestParam(value="mute", defaultValue = "") String mute){
        User exist = userService.findByUsername(username);
        if(exist != null){
            boolean admin = exist.getAdmin();
            if(admin){
                User exist2 = userService.findByUsername(mute);
                if(exist2 != null){
                    exist2.setMuted(true);
                    userService.save(exist2);
                    return exist2;
                }
            }
            return null;
        }
        return null;
    }
}