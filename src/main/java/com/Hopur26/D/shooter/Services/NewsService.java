package com.Hopur26.D.shooter.Services;

import com.Hopur26.D.shooter.Persistence.Entities.User;
import com.Hopur26.D.shooter.Persistence.Entities.Chat;
import com.Hopur26.D.shooter.Persistence.Entities.News;

import com.Hopur26.D.shooter.Persistence.Entities.Chat;

public interface NewsService {
    News save(News news);
    News create();
    News findByID(long ID);
}
