package storage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import storage.Entities.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account save(Account account);
    Account get(String accountName);
}
