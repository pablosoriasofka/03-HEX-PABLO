package co.sofka.gateway;


import co.sofka.BankTransaction;

import java.util.List;

public interface IBankTransactionRepository {

    BankTransaction update(BankTransaction item);
    BankTransaction save(BankTransaction item);
    BankTransaction delete(BankTransaction item);
    BankTransaction findById(Long id);
    Long deleteByElementId(Long id);
    List<BankTransaction> getAll();
}
