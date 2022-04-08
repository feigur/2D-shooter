package com.Hopur26.D.shooter.Services.Implementation;

import com.Hopur26.D.shooter.Persistence.Entities.User;
import com.Hopur26.D.shooter.Persistence.Repository.MainRepository;
import com.Hopur26.D.shooter.Persistence.Repository.UserRepository;
import com.Hopur26.D.shooter.Persistence.Repository.NewsRepository;
import com.Hopur26.D.shooter.Persistence.Entities.Chat;
import com.Hopur26.D.shooter.Persistence.Entities.News;
import com.Hopur26.D.shooter.Services.MainService;
import com.Hopur26.D.shooter.Services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// This was suppose to have some functionality but since there was too much functionality outside of the javascript code then this class would have been just for show
@Service
public class NewsServiceImplementation implements NewsService {
    private NewsRepository newsRepository;

    @Autowired
    public NewsServiceImplementation(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }


    public News create(){
        News news = new News();
        return  newsRepository.save(news);
    }


    @Override
    public News findByID(long ID) {
        return newsRepository.findByID(ID);
    }


}
