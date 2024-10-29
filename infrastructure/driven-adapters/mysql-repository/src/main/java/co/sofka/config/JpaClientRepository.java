package co.sofka.config;

import co.sofka.data.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClientRepository extends JpaRepository<ClientEntity, Long> {
}
