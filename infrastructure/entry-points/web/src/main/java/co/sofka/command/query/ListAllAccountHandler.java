package co.sofka.command.query;

import co.sofka.Account;
import co.sofka.crypto.Utils;
import co.sofka.usecase.IGetAllAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ListAllAccountHandler {

    private final IGetAllAccountService service;

   private final Utils utils;

    public List<Account> getAll() {

        List<Account> all = service.getAll();

        all=all.stream().map(account -> {
            account.setNumber(utils.encode(account.getNumber()));
            return account;
        }).toList();

        return all;
    }
}
