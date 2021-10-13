package services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.AccountService;
import storage.Entities.Account;
import storage.Repository.AccountRepository;

@Service
public class AccountServiceImplementation implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImplementation(AccountRepository accountRepository) { this.accountRepository = accountRepository; }
    @Override
    public Account save(Account account) { return accountRepository.save(account);}
    public Account get(String accountName) { return accountRepository.get(accountName);}

}
