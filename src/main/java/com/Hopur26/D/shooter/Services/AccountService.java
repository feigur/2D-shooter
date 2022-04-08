package com.Hopur26.D.shooter.Services;

import com.Hopur26.D.shooter.Persistence.Entities.KeyBinds;
import com.Hopur26.D.shooter.Persistence.Entities.Last5Games;
import com.Hopur26.D.shooter.Persistence.Entities.Account;
import com.Hopur26.D.shooter.Persistence.Entities.User;

import java.util.ArrayList;

public interface AccountService {
    Account save(Account account);
    Account create(Account account);
    void delete(Account account);
    Account findByUsername(String username);
    Account login(Account account);
    void setName(Account account, String name);
}
