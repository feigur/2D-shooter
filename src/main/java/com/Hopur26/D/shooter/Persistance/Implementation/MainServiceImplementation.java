package com.Hopur26.D.shooter.Persistance.Implementation;

import com.Hopur26.D.shooter.Persistance.MainService;
import com.Hopur26.D.shooter.storage.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImplementation implements MainService {
    private MainService mainService;

    @Autowired
    public MainServiceImplementation(MainService mainService){
        this.mainService = mainService;
    }


}
