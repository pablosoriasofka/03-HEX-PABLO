package co.sofka.command.query;

import co.sofka.Account;
import co.sofka.command.dto.request.AccountFindRequest;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.response.DinError;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.crypto.Utils;
import co.sofka.middleware.AccountNotExistException;
import co.sofka.middleware.ErrorDecryptingDataException;
import co.sofka.usecase.IGetAccountByNumberService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindAccountByNumberHandler {

    private static final Logger logger = LoggerFactory.getLogger(FindAccountByNumberHandler.class);

    private final IGetAccountByNumberService service;

   private final Utils utils;

    public ResponseMs<Account> findByNumber(RequestMs<AccountFindRequest> request) {

        ResponseMs<Account> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(request.getDinHeader());
        DinError error = new DinError();


        logger.info("Buscando Account por numero");
            String decode = "";
        try{
            decode = utils.decode(request.getDinBody().getNumber());
        } catch (Exception e) {
            throw new ErrorDecryptingDataException("Error al desencriptar el numero de cuenta.", request.getDinHeader(),1001);
        }

        logger.info("Buscando Account por numero: "+decode);

        Account account = service.findByNumber(decode);
        if (account == null) {
           throw new AccountNotExistException("Account no encontrado.", request.getDinHeader(),1002);
        }

        account.setNumber(utils.encode(account.getNumber()));



        responseMs.setDinError(error);


        return responseMs;
    }
}
