package co.sofka.command.query;

import co.sofka.Account;
import co.sofka.crypto.Utils;
import co.sofka.usecase.IGetAccountByNumberService;
import co.sofka.usecase.IGetAllAccountService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindAccountByNumberHandler {

    private static final Logger logger = LoggerFactory.getLogger(FindAccountByNumberHandler.class);

    private final IGetAccountByNumberService service;

   private final Utils utils;

    public Account findByNumber(String number) {

        logger.info("Buscando Account por numero");
        String decode = utils.decode(number);
        logger.info("Buscando Account por numero: "+decode);

        Account account = service.findByNumber(decode);

        account.setNumber(utils.encode(account.getNumber()));

        return account;
    }
}
