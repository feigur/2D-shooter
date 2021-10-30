package com.Hopur26.D.shooter.Persistance.Implementation;

import com.Hopur26.D.shooter.storage.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Hopur26.D.shooter.Persistance.UserService;
import com.Hopur26.D.shooter.storage.Repository.UserRepository;

import java.util.ArrayList;

@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User login(User user) {
        User doesExist = findByUsername(user.getUsername());
        if(doesExist != null){
            if(doesExist.getPassword().equals(user.getPassword())){
                return doesExist;
            }
        }
        return null;
    }

    @Override
    public void setKeys(User user,ArrayList<Integer> newKeys){
        user.setKeys(newKeys);

    }

    public ArrayList<Integer> getKeys(User user){
        return user.getKeys();
    }

    public void addGame(User user, String lastGame){
        user.addGame(lastGame);
    }

    public ArrayList<String> getLast5Games(User user){
        return user.getLast5Games();
    }

}