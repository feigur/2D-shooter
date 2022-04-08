package com.Hopur26.D.shooter.Persistence.Repository;
import com.Hopur26.D.shooter.Persistence.Entities.News;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsRepository extends JpaRepository<News, Long> {
    News save(News news);
    News findByID(long ID);
}

