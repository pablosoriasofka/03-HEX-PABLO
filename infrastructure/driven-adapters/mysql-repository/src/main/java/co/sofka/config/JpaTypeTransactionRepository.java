package co.sofka.config;

import co.sofka.data.entity.TypeTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTypeTransactionRepository extends JpaRepository<TypeTransactionEntity, Long> {
}
