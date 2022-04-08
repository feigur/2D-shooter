package com.Hopur26.D.shooter.Services;

import com.Hopur26.D.shooter.Persistence.Entities.User;
import com.Hopur26.D.shooter.Persistence.Entities.Chat;
import com.Hopur26.D.shooter.Persistence.Entities.News;

// This was suppose to have some functionality but since there was too much functionality outside of the javascript code then this class would have been just for show
public interface MainService {
    Chat save(Chat chat);
    Chat create();
    Chat findByID(long ID);
}
