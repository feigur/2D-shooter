package com.Hopur26.D.shooter.storage.Repository;

import com.Hopur26.D.shooter.storage.Entities.Last5Games;
import com.Hopur26.D.shooter.storage.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    void delete(User user);
    User findByUsername(String username);
}
