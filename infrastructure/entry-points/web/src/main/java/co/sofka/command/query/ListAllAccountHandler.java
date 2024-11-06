package co.sofka.command.query;

import co.sofka.Account;
import co.sofka.command.dto.CustomerDTO;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.response.DinError;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.crypto.Utils;
import co.sofka.usecase.IGetAllAccountService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class ListAllAccountHandler {

    private static final Logger logger = LoggerFactory.getLogger(ListAllAccountHandler.class);

    private final IGetAllAccountService service;

   private final Utils utils;

    public ResponseMs<List<Account>> getAll(RequestMs<Void> request) {

        ResponseMs<List<Account>> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(request.getDinHeader());
        DinError error = new DinError();

        try {
        List<Account> all = service.getAll();

        all=all.stream().map(account -> {
            account.setNumber(utils.encode(account.getNumber()));
            return account;
        }).toList();
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
