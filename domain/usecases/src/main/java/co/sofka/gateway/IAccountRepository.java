package co.sofka.gateway;


import co.sofka.Account;

import java.util.List;

public interface IAccountRepository {

    Account findByNumber(String accountNumber);
    Account findByNumberAndPing(String accountNumber,String pin);

    Account update(Account item);
    Account save(Account item);
    Account delete(Account item);
    Account findById(String id);
    Long deleteByElementId(Long id);
    List<Account> getAll();
}
