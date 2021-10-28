package services;

import storage.Entities.Account;

public interface LoginService {
    Account save(Account account);
    void delete(Account account);
    Account get(String accountName);
    Account findByAccountname(String username);
    Account login(Account user);
}
