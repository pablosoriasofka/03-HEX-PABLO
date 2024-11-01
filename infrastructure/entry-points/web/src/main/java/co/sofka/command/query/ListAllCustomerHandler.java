package co.sofka.command.query;

import co.sofka.Account;
import co.sofka.Customer;
import co.sofka.command.dto.AccountDTO;
import co.sofka.command.dto.CustomerDTO;
import co.sofka.crypto.Utils;
import co.sofka.usecase.IGetAllCustomerService;
import co.sofka.usecase.IGetAllCustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ListAllCustomerHandler {

    private final IGetAllCustomerService service;

   private final Utils utils;

    public List<CustomerDTO> getAll() {

        List<Customer> all = service.getAll();

        List<CustomerDTO> response=new ArrayList<>();

        response=all.stream().map(account -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setUsername(account.getUsername());

            List<AccountDTO> list = account.getAccounts().stream().map(account1 -> {
                AccountDTO accountDTO = new AccountDTO();
                accountDTO.setNumber(utils.encode(account1.getNumber()));
                accountDTO.setAmount(account1.getAmount());
                return accountDTO;
            }).toList();
            customerDTO.setAccounts(list);
            return customerDTO;
        }).toList();

        return response;
    }
}
