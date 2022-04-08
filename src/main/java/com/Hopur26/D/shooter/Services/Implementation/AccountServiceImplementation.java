package com.Hopur26.D.shooter.Services.Implementation;

import com.Hopur26.D.shooter.Persistence.Entities.KeyBinds;
import com.Hopur26.D.shooter.Persistence.Entities.Last5Games;
import com.Hopur26.D.shooter.Persistence.Entities.User;
import com.Hopur26.D.shooter.Persistence.Entities.Account;
import com.Hopur26.D.shooter.Persistence.Repository.AccountRepository;
import com.Hopur26.D.shooter.Persistence.Repository.UserRepository;
import com.Hopur26.D.shooter.Services.AccountService;
import com.Hopur26.D.shooter.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class AccountServiceImplementation implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImplementation(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Account create(Account account) {
        return accountRepository.save(account);
    }


    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Account login(Account account) {
        Account doesExist = findByUsername(account.getUsername());
        if(doesExist != null){
            if(doesExist.getPassword().equals(account.getPassword())){
                return doesExist;
            }
        }
        return null;
    }

    public void setName(Account account, String name){
        account.setUsername(name);
    }
}
