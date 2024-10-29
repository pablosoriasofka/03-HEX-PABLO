package co.sofka.config;

import co.sofka.data.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBankRepository extends JpaRepository<BankEntity, Long> {
}
