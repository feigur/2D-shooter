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
import java.util.concurrent.atomic.AtomicLong;
import com.Hopur26.D.shooter.Persistence.Entities.KeyBinds;
import com.Hopur26.D.shooter.Persistence.Entities.Last5Games;
import com.Hopur26.D.shooter.Persistence.Entities.User;
import com.Hopur26.D.shooter.Persistence.Entities.Chat;

import javax.servlet.http.HttpSession;

@RestController
public class RestMainController {
    private MainService mainService;
    private UserService userService;

    @Autowired
    public RestMainController(MainService mainService, UserService userService){ this.userService = userService; this.mainService = mainService;}

    @RequestMapping("/chat/create")
    public Chat create(){
        Chat chat = mainService.create();
        chat.setUsername("adalChat3");
        return chat;
    }

    @RequestMapping("/chat/get")
    public Chat create(@RequestParam(value="username", defaultValue = "") String username){
        User exist = userService.findByUsername(username);
        System.out.println(exist);
        if(exist != null){
            boolean muted = exist.getMuted();
            if(muted == false){
                Chat chat = mainService.findByID(1);
                if(chat != null){
                    return chat;
                }
            }
        }
        return null;
    }

    @RequestMapping("/chat/post")
    public Chat create(@RequestParam(value="username", defaultValue = "") String username,
                       @RequestParam(value="msg", defaultValue = "") String msg){
        User exist = userService.findByUsername(username);
        if(exist != null){
            boolean muted = exist.getMuted();
            if((muted == false) && (msg != "")){
                Chat chat = mainService.findByID(1);
                if(chat != null){
                    String merge = username + ": " + msg;
                    chat.addChat(merge);
                    mainService.save(chat);
                    return chat;
                }
            }
        }
        return null;
    }
}
