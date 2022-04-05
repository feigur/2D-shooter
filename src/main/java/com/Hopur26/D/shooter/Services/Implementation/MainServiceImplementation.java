package com.Hopur26.D.shooter.Services.Implementation;

import com.Hopur26.D.shooter.Persistence.Entities.User;
import com.Hopur26.D.shooter.Persistence.Repository.MainRepository;
import com.Hopur26.D.shooter.Persistence.Repository.UserRepository;
import com.Hopur26.D.shooter.Persistence.Entities.Chat;
import com.Hopur26.D.shooter.Services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// This was suppose to have some functionality but since there was too much functionality outside of the javascript code then this class would have been just for show
@Service
public class MainServiceImplementation implements MainService {
    private MainRepository mainRepository;

    @Autowired
    public MainServiceImplementation(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }

    @Override
    public Chat save(Chat chat) {
        return mainRepository.save(chat);
    }

    public Chat create(){
        Chat chat = new Chat();
        return  mainRepository.save(chat);
    }

    @Override
    public Chat findByID(long ID) {
        return mainRepository.findByID(ID);
    }



}
