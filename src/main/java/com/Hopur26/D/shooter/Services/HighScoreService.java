package com.Hopur26.D.shooter.Services;

import com.Hopur26.D.shooter.Persistence.Entities.HighScore;
import com.Hopur26.D.shooter.Persistence.Entities.News;

public interface HighScoreService {
    HighScore save(HighScore highScore);
    HighScore create(Integer gameID, String gamename);
    HighScore findByID(Integer ID);
}
