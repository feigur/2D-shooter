package com.Hopur26.D.shooter.Services;

import com.Hopur26.D.shooter.Persistence.Entities.HighScore;
import com.Hopur26.D.shooter.Persistence.Entities.News;

public interface HighScoreService {
    HighScore save(HighScore highScore);
    HighScore create();
    HighScore findByID(long ID);
}
