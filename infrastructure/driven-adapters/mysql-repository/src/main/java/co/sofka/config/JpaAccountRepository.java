package co.sofka.config;

import co.sofka.data.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByNumberAndPin(String accountNumber, String pin);

    AccountEntity findByNumber(String accountNumber);


}
