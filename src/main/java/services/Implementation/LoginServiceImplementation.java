package services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.LoginService;
import storage.Entities.Account;
import storage.Repository.AccountRepository;

import java.util.List;

@Service
public class LoginServiceImplementation implements LoginService {
    private AccountRepository accountRepository;

    @Autowired
    public LoginServiceImplementation(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }

    @Override
    public Account get(String accountName) {return accountRepository.get(accountName);}

    @Override
    public Account findByAccountname(String accountname) {
        return accountRepository.findByAccountname(accountname);
    }

    @Override
    public Account login(Account account) {
        Account doesExist = findByAccountname(account.getAccountname());
        if(doesExist != null){
            if(doesExist.getPassword().equals(account.getPassword())){
                return doesExist;
            }
        }
        return null;
    }

}
