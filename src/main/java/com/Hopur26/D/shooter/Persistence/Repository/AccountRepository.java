package com.Hopur26.D.shooter.Persistence.Repository;

import com.Hopur26.D.shooter.Persistence.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account save(Account account);
    void delete(Account account);
    Account findByUsername(String username);

}
