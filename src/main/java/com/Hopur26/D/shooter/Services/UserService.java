package com.Hopur26.D.shooter.Services;

import com.Hopur26.D.shooter.Persistence.Entities.KeyBinds;
import com.Hopur26.D.shooter.Persistence.Entities.Last5Games;
import com.Hopur26.D.shooter.Persistence.Entities.User;

import java.util.ArrayList;

public interface UserService {
    User save(User user);
    User create(User user, KeyBinds keyBinds, Last5Games last5Games);
    void delete(User user);
    User findByUsername(String username);
    User login(User user);
    void setName(User user, String name);

    ArrayList<String> getLast5Games(User user);
    ArrayList<Integer> getKeys(User user);
    void setKeys(User user,ArrayList<Integer> newKeys);
    void addGame(User user, String lastGame);
}
