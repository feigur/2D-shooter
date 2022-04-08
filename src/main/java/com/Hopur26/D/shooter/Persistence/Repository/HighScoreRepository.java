package com.Hopur26.D.shooter.Persistence.Repository;
import com.Hopur26.D.shooter.Persistence.Entities.HighScore;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HighScoreRepository extends JpaRepository<HighScore, Long> {
    HighScore save(HighScore highScore);
    HighScore findByID(long ID);
}

