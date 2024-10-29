package co.sofka.gateway;


import co.sofka.Bank;

import java.util.List;

public interface IBankRepository {

    Bank update(Bank item);
    Bank save(Bank item);
    Bank delete(Bank item);
    Bank findById(Long id);
    Long deleteByElementId(Long id);
    List<Bank> getAll();
}
