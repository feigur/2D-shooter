package com.Hopur26.D.shooter.Services.Implementation;

import com.Hopur26.D.shooter.Persistence.Entities.User;
import com.Hopur26.D.shooter.Persistence.Repository.MainRepository;
import com.Hopur26.D.shooter.Persistence.Repository.UserRepository;
import com.Hopur26.D.shooter.Persistence.Repository.HighScoreRepository;
import com.Hopur26.D.shooter.Persistence.Entities.Chat;
import com.Hopur26.D.shooter.Persistence.Entities.HighScore;
import com.Hopur26.D.shooter.Services.MainService;
import com.Hopur26.D.shooter.Services.HighScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// This was suppose to have some functionality but since there was too much functionality outside of the javascript code then this class would have been just for show
@Service
public class HighScoreServiceImplementation implements HighScoreService {
    private HighScoreRepository highScoreRepository;

    @Autowired
    public HighScoreServiceImplementation(HighScoreRepository highscoreRepository){
        this.highScoreRepository = highscoreRepository;
    }

    @Override
    public HighScore save(HighScore highScore) {
        return highScoreRepository.save(highScore);
    }


    public HighScore create(Integer gameID, String gamename){
        HighScore highScore = new HighScore(gameID, gamename);
        return  highScoreRepository.save(highScore);
    }


    @Override
    public HighScore findByID(long ID) {
        return highScoreRepository.findByID(ID);
    }


}
