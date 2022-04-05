package com.Hopur26.D.shooter.Persistence.Repository;
import com.Hopur26.D.shooter.Persistence.Entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

// This was suppose to have some functionality but since there was too much functionality outside of the javascript code then this class would have been just for show
public interface MainRepository extends JpaRepository<Chat, Long> {
    Chat save(Chat chat);
    Chat findByID(long ID);
}
