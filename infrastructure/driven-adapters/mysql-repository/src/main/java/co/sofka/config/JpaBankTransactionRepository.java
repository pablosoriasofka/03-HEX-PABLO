package co.sofka.config;

import co.sofka.data.entity.BankTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBankTransactionRepository extends JpaRepository<BankTransactionEntity, Long> {
}
