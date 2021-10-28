package com.Hopur26.D.shooter.Persistance;

import com.Hopur26.D.shooter.storage.Entities.User;

public interface LoginService {
    User save(User user);
    void delete(User user);
    User findByUsername(String username);
    User login(User user);
}
