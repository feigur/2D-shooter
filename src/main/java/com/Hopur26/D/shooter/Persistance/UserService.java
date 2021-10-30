package com.Hopur26.D.shooter.Persistance;

import com.Hopur26.D.shooter.storage.Entities.User;

import java.util.ArrayList;

public interface UserService {
    User save(User user);
    void delete(User user);
    User findByUsername(String username);
    User login(User user);

    ArrayList<String> getLast5Games(User user);
    ArrayList<Integer> getKeys(User user);
    void setKeys(User user,ArrayList<Integer> newKeys);
    void addGame(User user, String lastGame);
}
