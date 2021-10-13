package services;

import storage.Entities.Account;

public interface AccountService {
    Account save(Account account);
    Account get(String accountName);
}
