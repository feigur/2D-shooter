package com.Hopur26.D.shooter.Persistance.Implementation;

import com.Hopur26.D.shooter.storage.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Hopur26.D.shooter.Persistance.LoginService;
import com.Hopur26.D.shooter.storage.Repository.UserRepository;

@Service
public class LoginServiceImplementation implements LoginService {
    private UserRepository userRepository;

    @Autowired
    public LoginServiceImplementation(UserRepository userRepository){
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

}