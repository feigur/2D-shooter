package com.Hopur26.D.shooter.Persistance.Implementation;

import com.Hopur26.D.shooter.Persistance.MainService;
import com.Hopur26.D.shooter.storage.Entities.User;
import com.Hopur26.D.shooter.storage.Repository.MainRepository;
import com.Hopur26.D.shooter.storage.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;

@Service
public class MainServiceImplementation implements MainService {

    @Autowired
    public MainServiceImplementation(){
    }



}
