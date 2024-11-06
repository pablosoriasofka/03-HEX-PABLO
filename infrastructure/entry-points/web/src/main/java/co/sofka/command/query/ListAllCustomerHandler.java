package co.sofka.command.query;

import co.sofka.Account;
import co.sofka.Customer;
import co.sofka.command.dto.AccountDTO;
import co.sofka.command.dto.CustomerDTO;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.response.DinError;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.crypto.Utils;
import co.sofka.usecase.IGetAllCustomerService;
import co.sofka.usecase.IGetAllCustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ListAllCustomerHandler {

    private static final Logger logger = LoggerFactory.getLogger(ListAllCustomerHandler.class);

    private final IGetAllCustomerService service;

   private final Utils utils;

    public ResponseMs<List<CustomerDTO>> getAll(RequestMs<Void> request) {

        ResponseMs<List<CustomerDTO>> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(request.getDinHeader());
        DinError error = new DinError();

        try {

            List<Customer> all = service.getAll();

            List<CustomerDTO> response=new ArrayList<>();

            response=all.stream().map(account -> {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setUsername(account.getUsername());

                if(account.getAccounts()!=null && !account.getAccounts().isEmpty()){
                    List<AccountDTO> list = account.getAccounts().stream().map(account1 -> {
                        AccountDTO accountDTO = new AccountDTO();
                        logger.info("Account number: "+account1.getNumber());
                        accountDTO.setNumber(utils.encode(account1.getNumber()));
                        accountDTO.setAmount(account1.getAmount());
                        return accountDTO;
                    }).toList();
                    customerDTO.setAccounts(list);
                }

                return customerDTO;
            }).toList();



            responseMs.setDinBody(response);



        } catch (Exception e) {
            logger.error("Error al buscar todos los Customer", e);
            error.setMensaje(e.getMessage());
            error.setCodigo(String.valueOf(e.hashCode()));
            error.setFecha(LocalDateTime.now().toString());
            error.setTipo("ERROR");
            responseMs.setDinError(error);
            return responseMs;
        }

        responseMs.setDinError(error);


        return responseMs;
    }
}
