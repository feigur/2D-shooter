package com.Hopur26.D.shooter.controllers;


import com.Hopur26.D.shooter.Services.AccountService;
import com.Hopur26.D.shooter.Services.MainService;
import com.Hopur26.D.shooter.Services.NewsService;
import com.Hopur26.D.shooter.Services.HighScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.desktop.ScreenSleepEvent;
import java.security.Key;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import com.Hopur26.D.shooter.Persistence.Entities.KeyBinds;
import com.Hopur26.D.shooter.Persistence.Entities.Last5Games;
import com.Hopur26.D.shooter.Persistence.Entities.Account;
import com.Hopur26.D.shooter.Persistence.Entities.Chat;
import com.Hopur26.D.shooter.Persistence.Entities.News;
import com.Hopur26.D.shooter.Persistence.Entities.HighScore;


import javax.servlet.http.HttpSession;

@RestController
public class RestMainController {
    private MainService mainService;
    private AccountService accountService;
    private NewsService newsService;
    private HighScoreService highScoreService;

    @Autowired
    public RestMainController(MainService mainService, AccountService accountService, NewsService newsService, HighScoreService highScoreService){
        this.accountService = accountService;
        this.mainService = mainService;
        this.newsService = newsService;
        this.highScoreService = highScoreService;
    }

    @RequestMapping("/chat/create")
    public Chat create(){
        Chat chat = mainService.create();
        chat.setUsername("adalChat3");
        return chat;
    }

    @RequestMapping("/highscore/create")
    public HighScore createHighScore(@RequestParam(value="gameId", defaultValue = "0") String gameId,
                                     @RequestParam(value="gamename", defaultValue = "") String gamename){
        Integer thisGameId = Integer.parseInt(gameId);
        HighScore highScore = highScoreService.create(thisGameId,gamename);
        return highScore;
    }

    @RequestMapping("/news/create")
    public News createNews(){
        News news = newsService.create();
        news.setUsername("adalNews3");
        return news;
    }

    @RequestMapping("/chat/saekja")
    public Chat create(@RequestParam(value="username", defaultValue = "") String username){
        Account exist = accountService.findByUsername(username);
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

    @RequestMapping("/news/saekja")
    public News saekjaNews(){
        News news = newsService.findByID(2);
            if(news != null){
                return news;
            }
        return null;
    }

    @RequestMapping("/highscore/saekja")
    public HighScore saekjaHighScore(@RequestParam(value = "gameId", defaultValue = "") String gameId){
        System.out.println(gameId);
        Integer thisGameId = Integer.parseInt(gameId);
        System.out.println(thisGameId);
        HighScore highScore = highScoreService.findByID(thisGameId);
        if(highScore != null){
            return highScore;
        }
        return null;
    }

    @RequestMapping("/chat/add")
    public Chat create(@RequestParam(value="username", defaultValue = "") String username,
                       @RequestParam(value="msg", defaultValue = "") String msg){
        Account exist = accountService.findByUsername(username);
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

    @RequestMapping("/highscore/add")
    public HighScore createHighScore(@RequestParam(value="username", defaultValue = "") String username,
                                     @RequestParam(value="score", defaultValue = "0") String score,
                                     @RequestParam(value="gameId", defaultValue = "0") String gameId){
        Account exist = accountService.findByUsername(username);
        System.out.println(username + score);
        if(exist != null){
            if(score != ""){
                Integer thisScore = Integer.parseInt(score);
                Integer thisGameId = Integer.parseInt(gameId);
                HighScore highScore = highScoreService.findByID(thisGameId);
                if(highScore != null){
                    highScore.addHighSchore(username, thisScore);
                    highScoreService.save(highScore);
                    return highScore;
                }
            }
        }
        return null;
    }

    @RequestMapping("/news/add")
    public News createNews(@RequestParam(value="username", defaultValue = "") String username,
                            @RequestParam(value="title", defaultValue = "") String title,
                            @RequestParam(value="story", defaultValue = "") String story){
        Account exist = accountService.findByUsername(username);
        if(exist != null){
            boolean admin = exist.getAdmin();
            if((admin == true) && (title != "") && (story != "")){
                News news = newsService.findByID(1);
                if(news != null){
                    String merge = title + ": " + story;
                    news.addNews(merge);
                    newsService.save(news);
                    return news;
                }
            }
        }
        return null;
    }



}
