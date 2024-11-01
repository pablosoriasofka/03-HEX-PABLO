package co.sofka.command.query;

import co.sofka.Account;
import co.sofka.Customer;
import co.sofka.crypto.Utils;
import co.sofka.usecase.IGetAllCustomerService;
import co.sofka.usecase.IGetAllCustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ListAllCustomerHandler {

    private final IGetAllCustomerService service;

   private final Utils utils;

    public List<Customer> getAll() {

        List<Customer> all = service.getAll();

        all=all.stream().map(account -> {
            List<Account> list = account.getAccounts().stream().map(account1 -> {
                account1.setNumber(utils.encode(account1.getNumber()));
                return account1;
            }).toList();
            account.setAccounts(list);
            return account;
        }).toList();

        return all;
    }
}
